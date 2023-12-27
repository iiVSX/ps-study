import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[501];
            int[] dp = new int[101];
            for(int i=0; i<N; i++){
                String[] param = br.readLine().split(" ");
                arr[Integer.parseInt(param[0])] = Integer.parseInt(param[1]);
            }

            int answer = 0;
            int total = 0;
            for(int i=1; i<501; i++){
                if(arr[i] == 0){
                    continue;
                }

                total++;
                answer = find(dp, 0, answer, arr[i], answer);
            }

            System.out.println(total - answer);
        }catch(IOException | NumberFormatException e){
            e.printStackTrace();
            return;
        }
    }

    public static int find(int[] arr, int left, int right, int num, int N){
        if(left >= right){
            if(right == N && arr[right] < num){
                arr[right+1] = num;
                return right+1;
            }

            if(arr[right] > num){
                arr[right] = num;
            }

            return N;
        }

        int mid = (left+right)/2;
        if(arr[mid] < num){
            return find(arr, mid+1, right, num, N);
        }else{
            return find(arr, left, mid, num, N);
        }
    }
}