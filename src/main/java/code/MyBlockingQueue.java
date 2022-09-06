package code;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author SunnyJ
 * @Date 2022/8/22 22:33
 */
public class MyBlockingQueue<E> {
    private int size;
    private LinkedList<E> list = new LinkedList<>();
    MyBlockingQueue(int size) {
        this.size = size;
    }
    private ReentrantLock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();

    public void enqueue(E e) throws InterruptedException{
        lock.lock();
        try {
            while (list.size() == size) {
                full.await();
            }
            System.out.println("入队====="+e);
            list.add(e);
            empty.signal();
        }finally {
            lock.unlock();
        }
    }

    public E dequeue() throws InterruptedException{
        E e;
        lock.lock();
        try {
            while (list.size() == 0) {
                empty.await();
            }
            e = list.removeFirst();
            System.out.println("出队====="+e);
            full.signal();
            return e;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final MyBlockingQueue<Integer> blockingQueue = new MyBlockingQueue<Integer>(3);
        for (int i=0;i<5;i++) {
            final int data = i;
            new Thread(()->{
                try {
                    blockingQueue.enqueue(data);
                }catch (Exception e){
                    System.out.println(e);
                }
            }).start();
        }
        for (int i=0;i<5;i++) {
            new Thread(()->{
                try {
                    blockingQueue.dequeue();
                }catch (Exception e){
                    System.out.println(e);
                }
            }).start();
        }
    }
}
