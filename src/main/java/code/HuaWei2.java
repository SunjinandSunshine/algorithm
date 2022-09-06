package code;

import java.util.Scanner;

/**
 * @Author SunnyJ
 * @Date 2022/8/31 19:20
 */
public class HuaWei2 {
    private static int res = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int x = 0, y = 0;
        int[][] arr = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++){
                arr[i][j] = input.nextInt();
                if(arr[i][j] == 2) {
                    x = i; y = j;
                }
            }
        }
        dfs(arr,x,y,visited,0);
        System.out.println(res);
    }

        
    private static void dfs(int[][] arr, int x, int y, boolean[][] visited,int path) {
        if (x<0 || x>=arr.length || y<0 || y>=arr[0].length  || visited[x][y] || arr[x][y] == 1) return;
        if(arr[x][y] == 3) {
            path++;
            res = Math.min(res,path);
        }
        if(arr[x][y] == 4) {
            visited[x][y] = true;
            path+=3;
        }
        if(arr[x][y] == 6) {
            visited[x][y] = true;
            if (x - 1 >= 0) {
                arr[x - 1][y] = 0;
            }
            if (x + 1 < arr.length) {
                arr[x + 1][y] = 0;
            }
            if (y - 1 >= 0) {
                arr[x][y - 1] = 0;
            }
            if (y + 1 < arr[0].length) {
                arr[x][y + 1] = 0;
            }
            path++;
        }
        if(arr[x][y] == 0) path++;
        visited[x][y] = true;
        dfs(arr,x+1,y,visited,path);
        dfs(arr,x-1,y,visited,path);
        dfs(arr,x,y+1,visited,path);
        dfs(arr,x,y-1,visited,path);
        visited[x][y] = false;
    }
}
