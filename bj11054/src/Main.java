import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            int N = Integer.parseInt(br.readLine());
            String[] param = br.readLine().split(" ");

            int[][] A = new int[N][3];
            int[] seq = new int[N+1];
            int[] revSeq = new int[N+1];

            int len = 0, revLen = 0;
            for(int i=0; i<N; i++){
                A[i][0] = Integer.parseInt(param[i]);
            }

            for(int i=0; i<N; i++){
                int cur = quickSort(seq, 0, len, A[i][0]);
                if(seq[cur] < A[i][0]){
                    cur++;
                }

                if(cur > len){
                    len++;
                    seq[cur] = A[i][0];
                }
                else{
                    if(seq[cur] > A[i][0]){
                        seq[cur] = A[i][0];
                    }
                }

                A[i][1] = cur;

                cur = quickSort(revSeq, 0, revLen, A[N-1-i][0]);
                if(revSeq[cur] < A[N-1-i][0]){
                    cur++;
                }

                if(cur > revLen){
                    revLen++;
                    revSeq[cur] = A[N-1-i][0];
                }
                else{
                    if(revSeq[cur] > A[N-1-i][0]){
                        revSeq[cur] = A[N-1-i][0];
                    }
                }

                A[N-1-i][2] = cur;
            }

            int result = 0;
            for(int i=0; i<N; i++){
                result = Math.max(result, A[i][1]+A[i][2]-1);
            }

            System.out.println(result);
        }catch(IOException | NumberFormatException e){
            return;
        }
    }

    public static int quickSort(int[] arr, int left, int right, int num){
        if(left >= right){
            return right;
        }

        int mid = (left+right) / 2;
        if(arr[mid] > num){
            return quickSort(arr, left, mid-1, num);
        }else if(arr[mid] < num){
            return quickSort(arr, mid+1, right, num);
        }

        return mid;
    }
}