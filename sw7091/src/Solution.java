import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

class Solution
{
    static final int MAX_N = 2000;
    static final int COLUMN_HASH_KEY = 4;
    static final int ROW_HASH_KEY = 5;
    static final long MOD = (1 << 30) - 1;

    static int[][] cHash = new int[MAX_N][MAX_N];
    static int[][] pHash = new int[MAX_N][MAX_N];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        try {
            T = Integer.parseInt(br.readLine());
            for (int test_case = 1; test_case <= T; test_case++) {
                String[] param = br.readLine().split(" ");

                int H = Integer.parseInt(param[0]);
                int W = Integer.parseInt(param[1]);
                int N = Integer.parseInt(param[2]);
                int M = Integer.parseInt(param[3]);

                int[][] child = new int[MAX_N][MAX_N];
                int[][] parent = new int[MAX_N][MAX_N];

                for(int i=0; i<H; i++){
                    String line = br.readLine();
                    for(int j=0 ;j<W; j++){
                        child[i][j] = line.charAt(j) == 'o' ? 1 : 0;
                    }
                }

                for(int i=0; i<N; i++){
                    String line = br.readLine();
                    for(int j=0 ;j<M; j++){
                        parent[i][j] = line.charAt(j) == 'o' ? 1 : 0;
                    }
                }

                System.out.println("#" + test_case + " " + calculate(child, parent, H, W, N, M));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static int calculate(int[][] child, int[][] parent, int H, int W, int N, int M){
        for(int i=0; i<H; i++){
            cHash[0][i] = hash(W, child[i], COLUMN_HASH_KEY);
        }

        int pattern = hash(H, cHash[0], ROW_HASH_KEY);

        int modC = mod(W, COLUMN_HASH_KEY);
        for(int i=0; i<N; i++){
            for(int j=0; j<M-W+1; j++){
                if(j==0){
                    cHash[0][i] = hash(W, parent[i], COLUMN_HASH_KEY);
                }else{
                    cHash[j][i] = proc(cHash[j-1][i], parent[i][j-1], parent[i][j+W-1], modC, COLUMN_HASH_KEY);
                }
            }
        }

        int modR = mod(H, ROW_HASH_KEY);
        for(int i=0; i<M-W+1; i++){
            for(int j=0; j<N-H+1; j++){
                if(j==0){
                    pHash[j][i] = hash(H, cHash[i], ROW_HASH_KEY);
                }else{
                    pHash[j][i] = proc(pHash[j-1][i], cHash[i][j-1], cHash[i][j+H-1], modR, ROW_HASH_KEY);
                }
            }
        }

        int result = 0;
        for(int i=0; i<N-H+1; i++){
            for(int j=0; j<M-W+1; j++){
                if(pattern == pHash[i][j]){
                    result++;
                }
            }
        }

        return result;
    }

    public static int hash(int size, int[] data, int key) {
        long hash = 0;
        for(int i=0; i<size; i++){
            hash = (hash << key) + hash + data[i];
        }

        return (int)(hash & MOD);
    }

    public static int proc(int cur, int prev, int next, int mod, int key) {
        long hash = cur - (long) prev * mod;
        hash = (hash << key) + hash + next;
        return (int)(hash & MOD);
    }

    public static int mod(int size, int key){
        long mod = 1;
        for(int i=1; i<size; i++){
            mod = (mod << key) + mod;
        }
        return (int)(mod & MOD);
    }
}
