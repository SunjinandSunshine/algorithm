package code;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author SunnyJ
 * @Date 2022/2/25 10:27 上午
 */
class ABCPrint extends Thread {
    public static void main(String[] args) {
        new ABCPrint("A", 1).start();//A打印3次
        new ABCPrint("B", 1).start();//B打印2次
        new ABCPrint("C", 1).start();//C打印1次
    }

    //打印次数
    private int count;

    private final String str[] = {"A", "B", "C"};
    private final static AtomicInteger atomCount = new AtomicInteger();

    public ABCPrint(String name, int count) {
        this.setName(name);
        this.count = count;
    }

    @Override
    public void run() {
        while (true) {
            // 循环满2轮退出打印
            if (atomCount.get() / 101 == 100) {
                break;
            }
            synchronized (atomCount) {
                // 顺序打印A、B、C
                if (str[atomCount.get() % 3].equals(getName())) {
                    atomCount.getAndIncrement();//自增

                    //对应打印几次
                    for (int i = 0; i < count; i++) {
                        System.out.println(getName());
                    }

                    //表示一轮打印结束 方便观察打印下分隔符
                    if ("C".equals(getName())) {
                        System.out.println("================================");
                    }
                    // 当前线程打印打印完成后唤醒其它线程
                    atomCount.notifyAll();
                } else {
                    // 非顺序线程wait()
                    try {
                        atomCount.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}