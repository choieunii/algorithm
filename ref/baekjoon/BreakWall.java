package ref.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWall {
    static int N;
    static int M;
    static int map[][];
    static int visited[][][];
    static int min = Integer.MAX_VALUE;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static class Location {
        int x;
        int y;
        int breakCnt;
        int loc;

        public Location(int ddx, int ddy, int cnt) {
            this.x = ddx;
            this.y = ddy;
            this.breakCnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        visited = new int[N + 1][M + 1][2];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        bfs();
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    public static void bfs() {
        Queue<Location> q = new LinkedList<>();
        q.offer(new Location(0, 0, 0));
        visited[0][0][0] = 1;
        while (!q.isEmpty()) {
            Location tmp = q.poll();

            if (tmp.x == N - 1 && tmp.y == M - 1) {
                int time = visited[tmp.x][tmp.y][tmp.breakCnt];
                min = time + 1;
                break;
            }

            for (int i = 0; i < 4; i++) {

                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if (!isRange(nx, ny)) continue;

                if (map[nx][ny] == 1 && tmp.breakCnt == 0 && visited[nx][ny][1] == 0) {
                    q.offer(new Location(nx, ny, 1));
                    visited[nx][ny][1] = visited[tmp.x][tmp.y][1] + 1;
                    System.out.println("벽깨짐");
                } else if (visited[nx][ny][tmp.breakCnt] == 0) {
                    q.offer(new Location(nx, ny, tmp.breakCnt));
                    visited[nx][ny][tmp.breakCnt] = visited[tmp.x][tmp.y][tmp.breakCnt] + 1;
                }
                print(nx, ny);
            }

        }
    }

    static boolean isRange(int nx, int ny) {
        if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
            return true;
        }
        return false;
    }

    static void print(int nx, int ny) {
        System.out.println("하이 헬로 x : " + nx + " y : " + ny);
        for (int k = 0; k < visited[0][0].length; k++) {
            System.out.println("몇번쨰?????????? : " + k);
            for (int i = 0; i < visited.length; i++) {
                for (int j = 0; j < visited[i].length; j++) {
                    System.out.print(visited[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
    }
}