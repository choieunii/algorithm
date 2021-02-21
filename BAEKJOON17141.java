import javax.print.attribute.IntegerSyntax;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BAEKJOON17141 {
    static int N = 0;
    static int M = 0;
    static int lab[][];
    static boolean dfsVisit[];
    static boolean bfsVisit[][];
    static Queue<Point> queue = new LinkedList<Point>();
    static ArrayList<Point> virus = new ArrayList<Point>();
    static ArrayList<Point> actVirus = new ArrayList<Point>();
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};
    static int minTime = Integer.MAX_VALUE;
    static int flag = 0;
    static int count=0;
    public static class Point {
        int x;
        int y;
        int time;

        Point(int xx, int yy, int currentTime) {
            this.x = xx;
            this.y = yy;
            this.time = currentTime;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N + 1][N + 1];
        bfsVisit = new boolean[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 2) {
                    virus.add(new Point(i, j, 0)); //2일경우 virus에 저장
                }
            }
        }
        dfsVisit = new boolean[virus.size()];
        dfs(0, 0);
        if(minTime != Integer.MAX_VALUE) {
            System.out.println(minTime);
        }else{
            System.out.println(-1);
        }

    }

    public static int bfs() {
        int time = 0;

        for(int k=0;k<virus.size();k++){
        if(dfsVisit[k]==true){
            queue.offer(virus.get(k));
            bfsVisit[virus.get(k).x][virus.get(k).y] = true;
        }
        }

        while (!queue.isEmpty()) {
            Point tmp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int curx = tmp.x + dx[i];
                int cury = tmp.y + dy[i];
                time = tmp.time;
                if (curx < 0 || cury < 0 || curx >= N || cury >= N) {
                    continue;
                }//범위확인

                if (bfsVisit[curx][cury]) {
                    continue;
                }

                if (lab[curx][cury] != 1) {
                    queue.offer(new Point(curx, cury, tmp.time + 1));
                  //  lab[curx][cury] = tmp.time + 1;
                    bfsVisit[curx][cury] = true;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lab[i][j] == 0 && !bfsVisit[i][j]) {
                    return -1;
                }
            }
        }
        return time;

    }

    public static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(lab[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void dfs(int idx, int depth) {
        if (depth == M) {
            bfsVisit = new boolean[N + 1][N + 1];
            int tmpTime = bfs();
            if(tmpTime != -1) {
                minTime = Math.min(minTime, tmpTime);
            }
            return;
        } else {
            for (int i = idx; i < virus.size(); i++) {
                if (!dfsVisit[i]) {
                    dfsVisit[i] = true;
                    dfs(i, depth + 1);
                    dfsVisit[i] = false;
                }
            }
        }
    }

}
