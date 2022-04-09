package code;

/**
 * @Author SunnyJ
 * @Date 2022/2/25 9:57 上午
 */
public class Singleton {
    private static Singleton singleton;
    private Singleton(){}
    public synchronized static Singleton getSingleton() {
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
}
