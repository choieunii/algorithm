
import java.io.*;
import java.util.*;

public class BAEKJOON15686 {
    static int N = 0;
    static int M = 0;
    static int map[][];
    static ArrayList<node> house = new ArrayList<>();
    static ArrayList<node> chicken = new ArrayList<>();
    static ArrayList<node> chickenList = new ArrayList<>();
    static boolean visited[];
    static int cityChickenDistance = Integer.MAX_VALUE;
    public static class node {
        int x;
        int y;
        int chickenDistance;

        public node(int xx, int yy, int distance) {
            this.x = xx;
            this.y = yy;
            this.chickenDistance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    house.add(new node(i, j, Integer.MAX_VALUE));
                } else if (map[i][j] == 2) {
                    chicken.add(new node(i, j, Integer.MAX_VALUE));
                }
            }
        }
        visited = new boolean[chicken.size()+1];
        dfs(0,0);
        System.out.println(cityChickenDistance);
    }

    public static void dfs(int count, int depth) {
        if (depth == M) {
            int tmpCityChickenDistance = 0;
            for(int i=0;i<house.size();i++) {
                int chickenDistance = Integer.MAX_VALUE;
                node tmpHouse = house.get(i);
                for(int j=0;j<chickenList.size();j++) {
                        node tmpChicken = chickenList.get(j);
                        int tmpChickenDistance = manhattanDistance(tmpHouse, tmpChicken);
                        chickenDistance = Math.min(chickenDistance, tmpChickenDistance);
                }
                tmpCityChickenDistance += chickenDistance;
            }
            cityChickenDistance = Math.min(cityChickenDistance, tmpCityChickenDistance);
            return;
        }

        for (int i = count; i < chicken.size(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                chickenList.add(chicken.get(i));
                dfs(i+1, depth + 1);
                visited[i] = false;
                chickenList.remove(chickenList.size()-1);
            }
        }

    }

    public static int manhattanDistance(node first, node second) {
        int x = Math.abs(first.x - second.x);
        int y = Math.abs(first.y - second.y);
        return x + y;
    }
}
