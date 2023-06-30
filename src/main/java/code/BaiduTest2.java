package code;

import java.util.*;

class BaiduTest2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        while (input.hasNextLine()) {
            String s = input.nextLine();
            if(s.length() == 0) break;
            String[] nums = s.split(" ");
            int a = Integer.parseInt(nums[0]);
            int b = Integer.parseInt(nums[1]);
            ArrayList<Integer> path = new ArrayList();
            path.add(a);path.add(b);
            list.add(path);
        }
        int[][] intervals = new int[list.size()][2];
        for(int i=0;i<list.size();i++) {
            intervals[i][0] = list.get(i).get(0);
            intervals[i][1] = list.get(i).get(1);
        }
        int[][] res = merge(intervals);
        int count = 0;
        for(int i=0;i<res.length;i++) {
            count+=res[i][1] - res[i][0];
        }
        System.out.println(count);
    }
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(o1, o2)->{
            return o1[0] - o2[0];
        });
        ArrayList<int[]> list = new ArrayList();
        for(int i=0;i<intervals.length;i++) {
            int[] cur = intervals[i];
            if(list.size() == 0) list.add(cur);
            else {
                int[] lastArray = list.get(list.size()-1);
                int left = cur[0], right = lastArray[1];
                if(left > right) {
                    list.add(cur);
                }else{
                    lastArray[1] = Math.max(cur[1],lastArray[1]);
                }
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}