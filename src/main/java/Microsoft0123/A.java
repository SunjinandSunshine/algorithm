package Microsoft0123;

/**
 * @Author SunnyJ
 * @Date 2022/1/28 12:01 下午
 */
public class A {
    public static void main(String[] args) {
        System.out.println(solution(2, new int[]{0, 1, 0, 1}, "RCRR"));
    }
    public static int solution(int N, int[] A, String S) {
        int res = 0;
        int count=0;
        char[] arr = S.toCharArray();
        int[][] array = new int[N][N];
        for(char ch:arr){
            count++;
            getIncre(A[count],array,ch);
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                res = Math.max(res,array[i][j]);
            }
        }
        return res;
    }

    private static void getIncre(int k, int[][] arr,char ch) {
        if (ch == 'R'){
            for(int i=0;i<arr.length;i++){
                arr[k][i]++;
            }
        }else{
            for(int j=0;j<arr.length;j++){
                arr[j][k]++;
            }
        }
    }

    private static void col(int k, int[][] arr) {
        for(int j=0;j<arr.length;j++){
            arr[k][j]++;
        }
    }

    private static void row(int k, int[][] arr) {
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(k == j){
                    arr[i][k]++;
                }
            }
        }
    }
}
