package code;

/**
 * @Author SunnyJ
 * @Date 2022/9/15 17:05
 */
public class HeapSort {
    public int[] sortArray(int[] nums) {
        for(int i=nums.length/2;i>=0;i--){
            adjustHeap(nums,i,nums.length);
        }
        for(int j=nums.length-1;j>0;j--){
            swap(nums,0,j);
            adjustHeap(nums,0,j);
        }
        return nums;
    }

    private void adjustHeap(int[] nums, int i, int length) {
        int temp = nums[i];
        for(int k=2*i+1;k<length;k=2*k+1) {
            if(k+1<length && nums[k]<nums[k+1]) k++;
            if(nums[k] > temp) {
                nums[i] = nums[k];
                i = k;
            }else{
                break;
            }
        }
        nums[i] = temp;
    }

    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
