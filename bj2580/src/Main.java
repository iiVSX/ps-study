import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            int N = 9;
            int[][] board = new int[N][N];
            Set<Integer> coords = new HashSet<>();
            for(int i=0; i<N; i++){
                String[] params = br.readLine().split(" ");
                for(int j=0; j<N; j++){
                    board[i][j] = Integer.parseInt(params[j]);
                    if(board[i][j] == 0){
                        coords.add(i*N + j);
                    }
                }
            }

            solve(board, N);
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    System.out.print(board[i][j]);
                    if(j != N-1){
                        System.out.print(" ");
                    }
                }
                if(i != N-1){
                    System.out.print("\n");
                }
            }

        }catch(IOException | NumberFormatException e){
            return;
        }
    }

    public static void solve(int[][] board, int N){
        List<Integer> coords = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(board[i][j] == 0){
                    coords.add(i*N+j);
                }
            }
        }

        dfs(board, 0, coords, N);
    }

    public static boolean dfs(int[][] board, int cur, List<Integer> coords, int N){
        if(cur == coords.size()){
            return true;
        }

        int coord = coords.get(cur);
        int x = coord / N;
        int y = coord % N;
        for(int num=1; num<=N; num++){
            if(isValid(board, N, x, y, num)){
                board[x][y] = num;
                if(dfs(board, cur+1, coords, N)){
                    return true;
                };
            }
        }
        board[x][y] = 0;

        return false;
    }

    public static boolean isValid(int[][] board, int N, int row, int col, int num) {
        for(int i=0; i<N; i++){
            if(i != col && board[row][i] == num){
                return false;
            }

            if(i != row && board[i][col] == num){
                return false;
            }
        }

        int sx = row/3;
        int sy = col/3;

        for(int i=sx*3; i<(sx+1)*3; i++){
            for(int j=sy*3; j<(sy+1)*3; j++){
                if(board[i][j] == num && !(row == i && col == j)){
                    return false;
                }
            }
        }

        return true;
    }
}