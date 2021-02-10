import javax.print.attribute.IntegerSyntax;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON4179 {
    static int M = 0;
    static int N = 0;
    static char maze[][];
    static int dx[] = {1, -1, 0, 0};
    static int dy[] = {0, 0, 1, -1};
    static int count = 0;

    public static class location {
        int x;
        int y;

        public location(int curx, int cury) {
            this.x = curx;
            this.y = cury;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        maze = new char[M + 2][N + 2];
        for (int i = 0; i < M; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < N; j++) {
                maze[i][j] = tmp.charAt(j); //미로 입력
            }
        }
        bfs();
    }

    public static void bfs() {
        Queue<location> queue = new LinkedList<location>();
        Queue<location> fire = new LinkedList<location>();

        for (int i = 0; i < M; i++) { // 지훈이 찾기
            for (int j = 0; j < N; j++) {
                if (maze[i][j] == 'J') // 지훈이를 찾으면 큐에 넣는다.
                {
                    queue.offer(new location(i, j));
                }
                if (maze[i][j] == 'F') { // 불의 위치도 찾아 큐에 넣는다.
                    fire.offer(new location(i, j));
                }
            }
        }

        while (!queue.isEmpty()) {
            int size = fire.size();
            for (int i = 0; i < size; i++) {
                location fireTmp = fire.poll(); // 불의 위치
                for (int j = 0; j < 4; j++) {
                    int row = fireTmp.x + dx[j];
                    int col = fireTmp.y + dy[j];
                    if (row < 0 || row >= M || col < 0 || col >= N || maze[row][col] == '#' || maze[row][col] == 'F') {
                        continue;
                    }// 범위 확인, 벽안됨, 방문해도 안됨.
                    maze[row][col] = 'F'; // 불의 상하좌우를 F로 변경
                    fire.offer(new location(row, col));
                }
            }

            size = queue.size();
            for (int i = 0; i < size; i++) {
                location tmp = queue.poll(); // 지훈이위치
                for (int j = 0; j < 4; j++) {
                    int row = tmp.x + dx[j];
                    int col = tmp.y + dy[j];
                    if (row < 0 || row >= M || col < 0 || col >= N) {
                        System.out.println(count + 1);
                        return;
                    }//범위확인, 탈출

                    if (maze[row][col] == '#' || maze[row][col] == 'F' || maze[row][col] == 'J') {
                        continue;
                    }//벽안됨, 불 안됨, 지훈이가 방문해도 안됨.

                    maze[row][col] = 'J';
                    queue.offer(new location(row, col));
                }
            }
            count++;
        }
        System.out.println("IMPOSSIBLE");
    }

}
