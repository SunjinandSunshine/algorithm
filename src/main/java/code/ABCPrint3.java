package code;

import sun.plugin.ClassLoaderInfo;

import javax.sound.sampled.FloatControl;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author SunnyJ
 * @Date 2022/5/28 22:55
 */
public class ABCPrint3 {
    private static int num = 1;
    private static Lock lock =  new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    private static Condition condition3 = lock.newCondition();
    public static void main(String[] args) {
        Thread threadA = startThreadA("A", 10);
        Thread threadB = startThreadA("B", 10);
        Thread threadC = startThreadA("C", 10);
        threadA.start();
        threadB.start();
        threadC.start();
    }
    public static Thread startThreadA(String threadName,int loop){
        return new Thread(()->{
            lock.lock();
            for (int i=0;i<loop;i++) {
                try {
                    if(num!=1) {
                        condition1.await();
                    }
                    System.out.println(Thread.currentThread().getName());
                    num = 2;
                    condition2.signal();
                }catch (Exception e){
                    System.out.println(e);
                }finally {
                    lock.unlock();
                }
            }
        },threadName);
    }
    public static Thread startThreadB(String threadName,int loop){
        return new Thread(()->{
            lock.lock();
            for (int i=0;i<loop;i++) {
                try {
                    if(num!=2) {
                        condition1.await();
                    }
                    System.out.println(Thread.currentThread().getName());
                    num = 3;
                    condition3.signal();
                }catch (Exception e){
                    System.out.println(e);
                }finally {
                    lock.unlock();
                }
            }
        },threadName);
    }
    public static Thread startThreadC(String threadName,int loop){
        return new Thread(()->{
            lock.lock();
            for (int i=0;i<loop;i++) {
                try {
                    if(num!=3) {
                        condition1.await();
                    }
                    System.out.println(Thread.currentThread().getName());
                    num = 1;
                    condition1.signal();
                }catch (Exception e){
                    System.out.println(e);
                }finally {
                    lock.unlock();
                }
            }
        },threadName);
    }
}
