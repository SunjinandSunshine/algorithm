package code;

/**
 * @Author SunnyJ
 * @Date 2022/2/25 10:01 上午
 */
public class DCLSingleton {
    /**
     * 1、双端检查机制，第一层检查是普通校验，实例为空直接返回；第二层是并发情况下的校验，当有多个线程拿到锁之后，其中一个线程完成了实例化后，其他阻塞的线程就返回。
     * 2、volatile的关键字是禁止指令重排
     */
    private volatile static DCLSingleton dclSingleton;
    private DCLSingleton(){}
    public static DCLSingleton getInstance(){
        if(dclSingleton == null){
            synchronized (DCLSingleton.class){
                if(dclSingleton == null){
                    dclSingleton = new DCLSingleton();
                }
            }
        }
        return dclSingleton;
    }
}
