package code;

/**
 * @Author SunnyJ
 * @Date 2022/3/3 3:39 下午
 */
public class BigDigitalSub {
    public static void main(String[] args) {
        System.out.println(bigDigitalSub("1000","1"));
    }
    public static String bigDigitalSub(String a, String b) {
        //翻转字符串并转化成数组
        char[] aArray = new StringBuilder(a).reverse().toString().toCharArray();
        char[] bArray = new StringBuilder(b).reverse().toString().toCharArray();
        int aLength = aArray.length;
        int bLength = bArray.length;
        //找到最大的位数，两个整数的差的位数小于等于两个整数中的最大位数
        int maxLength = aLength > bLength ? aLength : bLength;
        int[] result = new int[maxLength];
        //判断结果符号
        char sign = '+';
        if (aLength < bLength) sign = '-';
        else if (aLength == bLength) {
            int i = maxLength - 1;
            while (i > 0 && aArray[i] == bArray[i])
                i--;
            if (aArray[i] < bArray[i])
                sign = '-';
        }
        //开始计算结果集
        for (int i = 0; i < maxLength; i++) {
            int aInt = i < aLength ? aArray[i] - '0' : 0;
            int bInt = i < bLength ? bArray[i] - '0' : 0;
            if (sign == '-') result[i] = bInt - aInt;
            else result[i] = aInt - bInt;
        }
        //处理结果集，如果结果集中的某一位小于0，则向高位借位，然后将本位加10
        for (int i = 0; i < maxLength - 1; i++) {
            if (result[i] < 0) {
                result[i + 1] -= 1;
                result[i] += 10;
            }
        }
        //处理结果集，转化成真正结果
        StringBuffer realResult = new StringBuffer();
        if (sign == '-') realResult.append('-');
        boolean isBeginning = true;
        for (int i = maxLength - 1; i >= 0; i--) {
            if (result[i] == 0 && isBeginning) continue;
            else isBeginning = false;
            realResult.append(result[i]);
        }
        if (realResult.toString().equals("")) realResult.append('0');
        return realResult.toString();
    }
}
