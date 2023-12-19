import java.io.*;
import java.util.*;

class Solution
{
    static int N = 0;
    static int M = 0;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        try {
            T = Integer.parseInt(br.readLine());
            for (int test_case = 1; test_case <= T; test_case++) {
                N = Integer.parseInt(br.readLine());
                int[] A = new int[N];
                for (int i = 0; i < N; i++) {
                    A[i] = Integer.parseInt(br.readLine());
                }

                M = Integer.parseInt(br.readLine());
                int[] B = new int[M];
                for (int i = 0; i < M; i++) {
                    B[i] = Integer.parseInt(br.readLine());
                }

                System.out.println("#" + test_case + " " + calculate(A, B));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static int calculate(int[] A, int[] B){
        int[][][][] dp = new int[A.length+1][B.length+1][B.length+1][2];
        boolean[][][][] visited = new boolean[A.length+1][B.length+1][B.length+1][2];

        B = Arrays.stream(B).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();
        return proc(dp, visited, A, B, 0, 0, 0, 0);
    }

    public static int proc(int[][][][] dp, boolean[][][][] visited, int[] A, int[] B, int i, int j, int k, int picked){
        if(visited[i][j][k][picked]){
            return dp[i][j][k][picked];
        }

        visited[i][j][k][picked] = true;

        int result = 0;
        if(picked == 1){
            if(i < N){
                result = Math.max(result, proc(dp, visited, A, B, i+1, j, k, 0));
            }
            if(j+k < M){
                result = Math.max(result, proc(dp, visited, A, B, i, j, k+1, 0));
            }
        }else{
            if(i < N){
                result = Math.max(result, proc(dp, visited, A, B, i+1, j, k, 1)+A[i]);
                result = Math.max(result, proc(dp, visited, A, B, i+1, j, k, 0));
            }

            if(j+k < M){
                result = Math.max(result, proc(dp, visited, A, B, i, j+1, k, 1)+B[j]);
                result = Math.max(result, proc(dp, visited, A, B, i, j, k+1, 0));
            }
        }

        dp[i][j][k][picked] = result;

        return result;
    }
}
