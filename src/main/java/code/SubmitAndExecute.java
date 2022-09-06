package code;

import java.util.concurrent.*;

/**
 * @Author SunnyJ
 * @Date 2022/8/30 15:07
 */

/**
 * execute 无返回值 只能执行 Runnable 任务 会抛出异常
 */
public class SubmitAndExecute {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newScheduledThreadPool(2);
//        executorService.execute(new Task());
//        executorService.submit(new Task());
//        executorService.shutdown();
        Future<Integer> future = executorService.submit(new ResultTask());
        try {
            Integer res = future.get();
            System.out.println(res);
        }catch (Exception e) {
            System.out.println(e);
        }
        finally {
            executorService.shutdown();
        }
    }

    public static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
    public static class ResultTask implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return 34;
        }
    }
}
