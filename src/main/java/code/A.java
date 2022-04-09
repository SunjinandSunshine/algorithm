package code;

import java.util.Arrays;

/**
 * @Author SunnyJ
 * @Date 2022/2/28 2:35 下午
 */

public class A {
    public static void main(String []args) {
        int n = 4;
        int tot = n * 2 + 1;
        // pos[i]表示前缀和i的最小下标，因为前缀和的取值范围为(-n, n)，所以每个前缀和的值+n
        int[] pos = new int[tot];
        int[] a = new int[]{1,-1,1,1};
        int x;
        int cur = 0;
        // 初始化pos设置一个最大值
        Arrays.fill(pos, n);
        // 因为刚开始一个数字都没有的时候前缀和为0，所以定义pos[n]的下标为-1
        pos[0 + n] = -1;
        for (int i = 0; i < n; ++i) {
            cur += a[i];
            pos[cur + n] = Math.min(pos[cur + n], i); //初步更新前缀和cur的最小下标
        }
        for (int i = 1; i < tot; ++i) {
            pos[i] = Math.min(pos[i], pos[i - 1]); // 如果有一个前缀和x个前缀和y, x-y>0, 那么x-(y-1)肯定>0,所以可以用y-1的最小下标更新y的下标
        }
        int ans = 0;
        cur = 0;
        for (int i = 0; i < n; ++i) {
            cur += a[i];
            // 当前前缀和cur,那么任意小于cur的前缀和都可以和
            if (cur + n - 1 >= 0) ans = Math.max(ans, i - pos[cur + n - 1]);
        }
        System.out.println(ans);
    }
}