import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] param = br.readLine().split(" ");
        int N = Integer.parseInt(param[0]);
        int M = Integer.parseInt(param[1]);

        String[] numArr = br.readLine().split(" ");
        int[] sum = new int[N+1];
        long[] mod = new long[M];
        long answer = 0;
        for(int i=1; i<=N; i++){
            sum[i] = (Integer.parseInt(numArr[i-1]) + sum[i-1]) % M;
            if(sum[i] == 0){
                answer++;
            }
            mod[sum[i]]++;
        }

        for(int i=0; i<M; i++){
            if(mod[i] >= 2){
                answer += (mod[i]*(mod[i]-1)/2);
            }
        }

        System.out.println(answer);
    }
}