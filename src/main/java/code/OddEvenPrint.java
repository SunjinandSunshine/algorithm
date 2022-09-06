package code;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author SunnyJ
 * @Date 2022/8/31 17:44
 */
public class OddEvenPrint {
    public static void main(String[] args) {
        OddEvenResource oddEvenResource = new OddEvenResource();
        for(int i=1;i<=50;i++) {
            new Thread(oddEvenResource::loopOdd,"A").start();
            new Thread(oddEvenResource::loopEven,"B").start();
        }
    }
}

class OddEvenResource {
    private int num = 1;
    private int index = 1;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    public void loopOdd() {
        lock.lock();
        try {
            while (num!=1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+" print "+index++);
            num = 2;
            condition2.signal();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
        }
    }
    public void loopEven() {
        lock.lock();
        try {
            while(num!=2) {
                condition2.await();
            }
            num = 1;
            condition1.signal();
            System.out.println(Thread.currentThread().getName()+" print "+index++);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
        }
    }
}