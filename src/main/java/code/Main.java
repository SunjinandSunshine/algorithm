package code;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int len = scanner.nextInt();
//        int[] nums = new int[len];
//        for(int i=0;i<len;i++) {
//            nums[i] = scanner.nextInt();
//        }
//        int[] res = findMex(nums);
//        for(int i=0;i<res.length;i++){
//            if(i == res.length - 1){
//                System.out.println(res[i]);
//            }else System.out.print(res[i]+" ");
//        }
        System.out.println(Math.ceil(7/2));
    }
    private static int[] findMex(int[] nums) {
        int[] res = new int[nums.length];
        int[] sort = new int[nums.length - 1];
        int index = 0;
        for(int i=0;i<nums.length;i++) {
            sort = new int[nums.length - 1];
            index = 0;
            for(int j=0;j<nums.length;j++) {
                if(i!=j) {
                    sort[index++] = nums[j];
                }
            }
            res[i] = helper(sort);
        }
        return res;
    }
    public static int helper(int[] nums) {
        int min = Arrays.stream(nums).min().getAsInt();
        if(min == 1) return 0;
        for(int i=0;i<nums.length;i++){
            while(nums[i]>0 && nums[i]<=nums.length && nums[i]!=nums[nums[i]-1]) swap(nums,i,nums[i]-1);
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=i+1) return i+1;
        }
        return nums.length+1;
    }
    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
