package code;

import java.util.Stack;

/**
 * @Author SunnyJ
 * @Date 2022/3/8 3:39 下午
 */
public class LC1685 {
    public static void main(String[] args) {
        maxSumMinProduct(new int[]{3,1,5,6,4,2});
    }

    public static int maxSumMinProduct(int[] arr) {
        int[] L = new int[arr.length];
        int[] R = new int[arr.length];
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) stack.pop();
            if (stack.empty()) L[i] = 0;
            else L[i] = stack.peek() + 1;
            stack.push(i);
        }
        while (!stack.empty()) stack.pop();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.empty() && arr[stack.peek()] >= arr[i]) stack.pop();
            if (stack.empty()) R[i] = arr.length - 1;
            else R[i] = stack.peek() - 1;
            stack.push(i);
        }
        long[] pre = new long[arr.length + 1];
        pre[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            pre[i + 1] = pre[i] + arr[i];
        }
        long ans = 0;
        for (int i = 0; i < arr.length; ++i) {
            int l = L[i];
            int r = R[i];
            ans = Math.max(ans, arr[i] * (pre[r + 1] - pre[l]));
        }
        return (int) (ans % (1000000007));
    }
}
