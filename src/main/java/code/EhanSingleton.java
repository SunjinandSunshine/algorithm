package code;

/**
 * @Author SunnyJ
 * @Date 2022/2/25 9:59 上午
 */
public class EhanSingleton {
    private static final EhanSingleton ehanSingleton = new EhanSingleton();
    private EhanSingleton(){}
    public static EhanSingleton getehanSingleton(){
        return ehanSingleton;
    }
}
