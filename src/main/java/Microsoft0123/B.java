package Microsoft0123;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author SunnyJ
 * @Date 2022/1/28 12:01 下午
 */
public class B {
    private static int solution(int[] V, int[] A, int[] B) {
        int len1 = V.length, len2 = A.length;
        int[] d = new int[len1];
        ArrayList<Integer>[] adj = new ArrayList[len1];
        for(int i=0;i<len2;i++){
            d[B[i]]++;
            if(adj[A[i]]==null) adj[A[i]] = new ArrayList<Integer>();
            adj[A[i]].add(B[i]);
        }
        ArrayList<Integer> stack = new ArrayList();
        LinkedList<Integer> queue = new LinkedList();
        for(int i=0;i<len1;i++){
            if(d[i]==0){
                queue.add(i);
                stack.add(V[i]);
            }
        }
        int res = 0;
        if(stack.size()>=2){
            queue.sort((x, y)->x-y);
            res = stack.get(stack.size()-1);
            res+= stack.get(stack.size()-2);
        }else if(stack.size()>0){
            res = stack.get(stack.size()-1);
        }
        while (!queue.isEmpty()) {
            int x = queue.poll();
            res = Math.max(res, V[x]);
            if(adj[x] == null) continue;
            for (int i = 0; i < adj[x].size(); i++) {
                int nx = adj[x].get(i);
                if(d[nx] == 1){
                    res = Math.max(res, V[x] + V[nx]);
                }
            }
        }
        return res;
    }
}
