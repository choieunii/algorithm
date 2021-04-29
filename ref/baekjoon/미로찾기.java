package ref.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;
    int count;

    Point(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

public class 미로찾기 {
    static int[][] maze;
    static int visited[][][];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int M, N, Hx, Hy, Ex, Ey;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Hx = Integer.parseInt(st.nextToken());
        Hy = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Ex = Integer.parseInt(st.nextToken());
        Ey = Integer.parseInt(st.nextToken());

        maze = new int[N + 1][M + 1];
        visited = new int[N + 1][M + 1][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs(Hx, Hy, Ex, Ey);
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    static void bfs(int Hx, int Hy, int Ex, int Ey) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(Hx - 1,Hy - 1,  0));

        while (!queue.isEmpty()) {
            Point tmp = queue.poll();

            if (tmp.x == Ex - 1 && tmp.y == Ey - 1) {
                int time = visited[tmp.x][tmp.y][tmp.count];
                ans = time + 1;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if (!isRange(nx, ny)) continue;

                if (maze[nx][ny] == 1 && tmp.count == 0 && visited[nx][ny][1] == 0) {
                    queue.offer(new Point(nx, ny, 1));
                    visited[nx][ny][1] = visited[tmp.x][tmp.y][1] + 1;
                    System.out.println("벽부숨 : " + nx + " " + ny);
                } else if (visited[nx][ny][tmp.count] == 0) {
                    queue.offer(new Point(nx, ny, tmp.count));
                    visited[nx][ny][tmp.count] = visited[tmp.x][tmp.y][tmp.count] + 1;
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
