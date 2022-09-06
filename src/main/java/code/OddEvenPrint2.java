package code;

import java.util.concurrent.Semaphore;

/**
 * @Author SunnyJ
 * @Date 2022/8/31 18:15
 */
public class OddEvenPrint2 {
    private static final Semaphore A = new Semaphore(1);
    private static final Semaphore B = new Semaphore(0);
    private static int index = 1;
    public static void main(String[] args) {
        for(int i=1;i<=50;i++) {
            new Thread(()->{
                try {
                    A.acquire();
                    System.out.println(Thread.currentThread().getName()+" print "+index++);
                    B.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
            },"A").start();
            new Thread(()->{
                try {
                    B.acquire();
                    System.out.println(Thread.currentThread().getName()+" print "+index++);
                    A.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
            },"B").start();
        }
    }
}
