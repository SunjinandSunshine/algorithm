package code;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author SunnyJ
 * @Date 2022/8/30 16:17
 */
public class MyThreadPool {
    public static void main(String[] args) throws InterruptedException {
        //阻塞队列大小设置为3
        BlockingQueue<Runnable> queue = new BlockingQueue<>(3);
        System.out.println("主线程号： " + Thread.currentThread().getId());

        /*
         * 核心线程数为2
         * 这里使用 同步运行 的拒绝策略。
         * */
        ThreadPool pool = new ThreadPool(2, queue, (q, task) -> task.run());
        for (int i = 0; i < 10; i++) {
            int j = i;
            pool.execute(() -> {
                System.out.println(j + "  执行此任务的线程号:" + Thread.currentThread().getId());
            });
        }
    }
    public static class BlockingQueue<T> {

        private ArrayDeque<T> queue;

        private ReentrantLock lock = new ReentrantLock();

        private Condition empty = lock.newCondition();

        private Condition full = lock.newCondition();

        private int maxSize;

        public BlockingQueue(int maxSize) {
            queue = new ArrayDeque<>();
            this.maxSize = maxSize;
        }

        /**
         * 无限期等待任务入队
         */
        public void put(T t) throws InterruptedException {
            lock.lock();
            try {
                while (queue.size() == maxSize) {
                    full.await();
                }
                queue.addLast(t);
                empty.signalAll();
            } finally {
                lock.unlock();
            }
        }

        /**
         * 无限期等待获取任务
         */
        public T get() {
            lock.lock();
            T res = null;
            try {
                while (queue.size() == 0) {
                    empty.await();
                }
                res = queue.pollFirst();
                full.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                return res;
            }
        }

        /**
         * 指定超时时间入队
         */
        public boolean put(T t, long waitTime, TimeUnit unit) {
            lock.lock();
            try {
                long time = unit.toNanos(waitTime);
                while (queue.size() == maxSize) {
                    if (time <= 0) {
                        System.out.println("任务 " + t + " 已经被丢弃");
                        return false;
                    }
                    /**
                     * @return 传入的等待时间 - 实际等待的时间
                     * @param 需要等待的时间，即使没有被singnal()唤醒只要到了指定的时间也会被唤醒
                     * */
                    try {
                        time = full.awaitNanos(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.addLast(t);
                empty.signalAll();
                return true;
            } finally {
                lock.unlock();
            }
        }

        /**
         * 指定超时时间获取数据
         */
        public T get(long waitTime, TimeUnit unit) throws InterruptedException {
            lock.lock();
            try {
                long time = unit.toNanos(waitTime);
                while (queue.size() == 0) {
                    if (time <= 0) {
                        return null;
                    }
                    time = empty.awaitNanos(time);
                }
                T t = queue.pollFirst();
                full.signalAll();
                return t;
            } finally {
                lock.unlock();
            }
        }

        /*
         * 尝试向队列添加元素
         * */
        public void tryPut(ThreadPool.RejectPolicy rejectPolicy, T task) {
            lock.lock();
            try {
                //队列满了，走拒绝策略
                if (queue.size() == maxSize) {
                    rejectPolicy.reject(this, task);
                    //没满，直接添加
                } else {
                    queue.addLast(task);
                    full.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }
    public static class ThreadPool {

        //Worker集合，就是线程池
        Set<Worker> workers;

        BlockingQueue<Runnable> queue;

        int coreSize;

        /**
         * 拒绝策略接口
         */
        @FunctionalInterface
        interface RejectPolicy<T> {
            void reject(BlockingQueue<T> queue, T task);
        }

        private RejectPolicy rejectPolicy;

        /**
         * Worker类，实现Runnable接口，当Worker被创建时，
         * 将这个worker对象扔进内部的Thread里去异步执行任务。
         * */
        public class Worker implements Runnable {

            //内部的核心属性，Thread类，当Worker创建
            public Thread thread;

            private Runnable task;


            public Worker(Runnable task) {
                this.task = task;
                //异步执行的任务就是当前Worker。
                thread = new Thread(this);
            }

            @Override
            public void run() {
                /**
                 *  线程复用原理： while()循环不断阻塞尝试获取任务
                 *  1.先执行自己的task
                 *  2.从阻塞队列中尝试获取任务，拿到任务继续执行
                 * */
                while (task != null || ((task = queue.get()) != null)) {
                    task.run();
                    //执行完任务，置为NULL
                    task = null;
                }
            }
        }

        public ThreadPool(int coreSize, BlockingQueue<Runnable> queue, RejectPolicy<Runnable> rejectPolicy) {
            workers = new HashSet<>();
            this.coreSize = coreSize;
            this.queue = queue;
            this.rejectPolicy = rejectPolicy;
        }

        public void execute(Runnable task) throws InterruptedException {
            if (task == null) {
                throw new NullPointerException();
            }
            //来任务，判断线程数是否小于核心线程数，小于直接开线程处理
            //源码使用的是 ctl属性，获取ctl的低29位，低29位表示线程池中的线程数
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                //将worker添加到wokers集合中
                workers.add(worker);
                //直接调用内部的start，异步执行worker内部的run()方法
                worker.thread.start();
                //线程数达到核心线程，尝试向队列中添加任务，(队列满了，就走拒绝策略)
            } else {
                queue.tryPut(rejectPolicy, task);
            }
        }
    }
}
