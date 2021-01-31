import javax.print.attribute.IntegerSyntax;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON2206 {
    static int N;
    static int M;
    static int map[][];
    static boolean visited[][][];
    static int min = Integer.MAX_VALUE;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static class Location {
        int x;
        int y;
        int breakCnt;
        int loc;
        public Location(int ddx, int ddy, int dloc, int cnt) {
            this.x = ddx;
            this.y = ddy;
            this.loc = dloc;
            this.breakCnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        visited = new boolean[N+1][M+1][2];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        bfs();
        if(min == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(min);
        }
    }

    public static void bfs() {
        Queue<Location> q = new LinkedList<Location>();
        q.offer(new Location(0, 0, 0,0));
        while (!q.isEmpty()) {
            Location tmp = q.poll();

            if (tmp.x == N - 1 && tmp.y == M - 1) {
                min = tmp.loc+1;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (map[nx][ny] == 1) {
                        if (tmp.breakCnt == 0 && !visited[nx][ny][1]) {
                            q.offer(new Location(nx, ny, tmp.loc+1,1));
                            visited[nx][ny][1] = true;
                        }
                    } else {
                        if (!visited[nx][ny][tmp.breakCnt]) {
                            q.offer(new Location(nx, ny, tmp.loc+1,tmp.breakCnt));
                            visited[nx][ny][tmp.breakCnt] = true;
                        }
                    }
                }
            }

        }
    }
}
