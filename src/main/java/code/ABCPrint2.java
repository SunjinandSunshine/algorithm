package code;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程 操作 资源类
 */
public class ABCPrint2 {
    public static void main(String[] args) {
        Resource ad = new Resource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1; i++) {
                    ad.loopA(2);
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1; i++) {
                    ad.loopB(2);
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1; i++) {
                    ad.loopC(2);
                }
            }
        },"C").start();
    }
}

class Resource{
    private int num = 1;//当前正在执行线程的标记
    private Lock lock =  new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    public void loopA(int loop){
        lock.lock();
        try {
            //1.判断
            if(num != 1){
                condition1.await();
            }
            //2.打印
            for (int i=0;i<loop;i++){
                System.out.println(Thread.currentThread().getName());
            }
            //3.唤醒
            num = 2;
            condition2.signal();
        } catch (Exception e) {
        }finally{
            lock.unlock();
        }
    }
    public void loopB(int loop){
        lock.lock();
        try {
            //1.判断
            if(num != 2){
                condition2.await();
            }
            for (int i=0;i<loop;i++){
                System.out.println(Thread.currentThread().getName());
            }
            //3.唤醒
            num = 3;
            condition3.signal();
        } catch (Exception e) {
        }finally{
            lock.unlock();
        }
    }
    public void loopC(int loop){
        lock.lock();
        try {
            //1.判断
            if(num != 3){
                condition3.await();
            }
            for (int i=0;i<loop;i++){
                System.out.println(Thread.currentThread().getName());
            }
            System.out.println();
            //3.唤醒
            num = 1;
            condition1.signal();
        } catch (Exception e) {
        }finally{
            lock.unlock();
        }
    }
}