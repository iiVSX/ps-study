import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

class Solution
{
    static List<Integer> distances;
    static Map<Integer, Coords> customerMap;
    static Coords home, company;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        try {
            T = Integer.parseInt(br.readLine());
            for (int test_case = 1; test_case <= T; test_case++) {
                int N = Integer.parseInt(br.readLine());

                String[] param = br.readLine().split(" ");

                customerMap = new HashMap<>();
                for(int i=0; i<N+2; i++){
                    int x = Integer.parseInt(param[i*2]);
                    int y = Integer.parseInt(param[i*2+1]);

                    if(i==0){
                        company = new Coords(x, y);
                    }else if(i==1){
                        home = new Coords(x, y);
                    }else{
                        customerMap.put(i-2, new Coords(x, y));
                    }
                }

                distances = new LinkedList<>();
                dfs(new LinkedList<>(), N);

                int result = Integer.MAX_VALUE;
                for(int distance : distances){
                    result = Math.min(result, distance);
                }

                distances.clear();
                customerMap.clear();

                System.out.println("#"+ test_case + " " + result);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void dfs(List<Coords> path, int N){
        if(path.size() == N){
            distances.add(calculate(path));
            return;
        }

        for(int i=0; i<N; i++){
            Coords coords = customerMap.get(i);
            if(!path.contains(coords)){
                path.add(coords);
                dfs(path, N);
                path.remove(coords);
            }
        }
    }

    public static int calculate(List<Coords> path){
        int distance = 0;
        Coords cur = company;
        for(Coords next : path){
            distance += euclidean(cur, next);
            cur = next;
        }
        distance += euclidean(cur, home);
        return distance;
    }

    public static int euclidean(Coords c1, Coords c2){
        return Math.abs(c1.x-c2.x) + Math.abs(c1.y-c2.y);
    }

}

class Coords {
    public Coords(int x, int y){
        this.x = x;
        this.y = y;
    }

    int x;
    int y;
}
