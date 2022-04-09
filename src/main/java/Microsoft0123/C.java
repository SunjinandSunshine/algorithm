package Microsoft0123;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author SunnyJ
 * @Date 2022/1/29 9:52 下午
 */
public class C {
    public static int solution(int N, int[] A, int[] B, int[] H) {
        int l1 = A.length;
        int l2 = H.length;
        int[] d = new int[N];
        Arrays.fill(d, -1);
        ArrayList<Integer>[]adj = new ArrayList[N];
        for(int i=0;i<l1;i++){
            if(adj[A[i]] == null) adj[A[i]] = new ArrayList<>();
            if(adj[B[i]] == null) adj[B[i]] = new ArrayList<>();
            adj[A[i]].add(B[i]);
            adj[B[i]].add(A[i]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<l2;i++){
            d[H[i]] = 0;
            queue.add(i);
        }
        while (!queue.isEmpty()){
            int x = queue.poll();
            if(adj[x] == null) continue;
            for(int i=0;i<adj[x].size();i++){
                int nx = adj[x].get(i);
                if(d[nx]!=-1) continue;
                d[nx] = d[x]+1;
                queue.add(nx);
            }
        }
        int ans = 0;
        for(int i=0; i<N; ++i){
            if(d[i] == -1)
            {
                ans = -1;
                break;
            }
            ans = Math.max(ans, d[i]);
        }
        return ans;
    }
}
