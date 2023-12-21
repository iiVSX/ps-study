import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            int N = Integer.parseInt(br.readLine());
            int[] board = new int[N+1];
            System.out.println(dfs(board, 1, N));

        }catch(IOException e){
            return;
        }
    }

    public static int dfs(int[] board, int col, int N){
        if(col > N){
            return 1;
        }

        int result = 0;
        for(int row=1; row<=N; row++){
            board[col] = row;
            boolean isValid = true;
            for(int i=1; i<col; i++){
                if(board[i] == row || Math.abs(board[i]-row) == Math.abs(i-col)){
                    isValid = false;
                    break;
                }
            }
            if(isValid){
                result += dfs(board, col+1, N);
            }
        }

        return result;
    }
}