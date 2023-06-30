package code;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author SunnyJ
 * @Date 2022/9/9 16:09
 */
class MutiPrintNums {
    private int num;
    private Lock lock = new ReentrantLock();

    private void printNums(int targetNum) {
        for (int i = 0; i < 1000; ) {
            lock.lock();
            while (num % 10 == targetNum) {
                num++;
                i++;
                if(Thread.currentThread().getName().equals("9")) {
                    System.out.println(Thread.currentThread().getName());
                }else {
                    System.out.print(Thread.currentThread().getName()+",");
                }
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MutiPrintNums lock = new MutiPrintNums();
        new Thread(() -> {
            lock.printNums(0);
        }, "0").start();

        new Thread(() -> {
            lock.printNums(1);
        }, "1").start();

        new Thread(() -> {
            lock.printNums(2);
        }, "2").start();

        new Thread(() -> {
            lock.printNums(3);
        }, "3").start();

        new Thread(() -> {
            lock.printNums(4);
        }, "4").start();

        new Thread(() -> {
            lock.printNums(5);
        }, "5").start();

        new Thread(() -> {
            lock.printNums(6);
        }, "6").start();

        new Thread(() -> {
            lock.printNums(7);
        }, "7").start();

        new Thread(() -> {
            lock.printNums(8);
        }, "8").start();
        new Thread(() -> {
            lock.printNums(9);
        }, "9").start();
    }
}

