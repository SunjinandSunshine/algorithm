package Microsoft0123;

import java.util.*;

/**
 * @Author SunnyJ
 * @Date 2022/1/30 2:02 下午
 */
public class Test {
    public static void main(String[] args) {
//        StringBuilder sb = new StringBuilder();
//        String a = "sss";
        //System.out.println(a.contains("ss"));
//        System.out.println(sb.toString().split(" ")[0].equals(""));
//        AtomicInteger atomicInteger = new AtomicInteger();
//        atomicInteger.getAndIncrement();
//        minWindow("ADOBECODEBANC","ABC");
//        HashMap<Character,Integer> map = new HashMap();
        //System.out.println("aaabb".split("a")[1]);
//        String[] res = "aaabb".split("b");
//        System.out.println(res.length);
//        for(int i=0;i<res.length;i++){
//            System.out.println(res[i]);
//        }
        //System.out.println((char) ('A'+1));
        //System.out.println("sss".contains('s'+""));
//
//        String word = "sunjin";
//        int index = 0;
//        for(int i=0;i<word.length();i++){
//            if(word.charAt(i) == 'j') index = i;
//            break;
//        }
//        System.out.println(index);
//        StringBuilder str1 = new StringBuilder(word.substring(0,3)).reverse();
//        //System.out.println(str1.toString());
//        findCircleNum(new int[][]{{1,1,0},{1,1,0,},{0,0,1}});
//        Semaphore semaphore = new Semaphore(0);
//        ReentrantLock reentrantLock = new ReentrantLock();
//        BlockingQueue blockingQueue;
          spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
          int num = 1;
          String str = new String(num+"");
          char[] arr = String.valueOf(num).toCharArray();
        System.out.println(arr[0]);
          //System.out.println(str.length());
        HashMap hashMap;
    }
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + dir[directionIndex][0], nextColumn = column + dir[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += dir[directionIndex][0];
            column += dir[directionIndex][1];
        }
        return order;
    }
    public static String minWindow(String s, String t) {
        if(s.length() == 0 || t.length() == 0) return "";
        int[] need = new int[128];
        for(int i=0;i<t.length();i++){
            need[t.charAt(i)]++;
        }
        int l = 0, r = 0, size = Integer.MAX_VALUE, count = t.length(), start = 0;
        while(r < s.length()){
            char c = s.charAt(r);
            if(need[c]>0){
                count--;
            }
            need[c]--;
            if(count == 0){
                while(l< r && need[s.charAt(l)]<0){
                    need[s.charAt(l)]++;
                    l++;
                }
                if(size>r-l+1){
                    start = l;
                    size =r-l+1;
                }
                need[s.charAt(l)]++;
                l++;
                count++;
            }
            r++;
        }
        return size == Integer.MAX_VALUE?"":s.substring(start,start+size);
    }
        public static  int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            int[] d = new int[n];
            for(int i=0;i<n;i++){
                for(int j=0;j<i;j++){
                    if(i!=j){
                        if(isConnected[i][j] == 1)d[j]++;
                    }
                }
            }
            int res = 0;
            Queue<Integer> queue = new LinkedList();
            for(int i=0;i<n;i++){
                if(d[i]==0) queue.add(i);
            }
            while(!queue.isEmpty()){
                res++;
                int x = queue.poll();
                for(int i=0;i<x;i++){
                    if(i!=x){
                        if(isConnected[x][i]==1) queue.add(i);
                    }
                }
            }
            return res;
        }
}
