package recall;

/**
 * @Author SunnyJ
 * @Date 2022/5/15 10:38
 */
public class StringStudy {
    public static void main(String[] args) {
        String str1 = "xyz";
        //str1.intern();
        String str2 = new String("xyz");
        System.out.println(str1 == str2);
    }
}
