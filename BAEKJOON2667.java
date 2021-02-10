import javax.print.attribute.IntegerSyntax;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M;

    static int map[][];
    static boolean visited[][];
    static int houseCnt[];
    static int count = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static class location {
        int x;
        int y;

        public location(int dx, int dy) {
            this.x = dx;
            this.y = dy;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());

        map = new int[M + 1][M + 1];
        visited = new boolean[M + 1][M + 1];
        houseCnt = new int[M *M];
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();
        
        System.out.println(count);//최종 count 출력
        Arrays.sort(houseCnt);//ㅂㄷㅂㄷ
        for (int i = 0; i < M*M; i++) {
            if(houseCnt[i] != 0) {
                System.out.println(houseCnt[i]); // 코드를 이쁘게 만드려면 ArrayList 사용이 더 나을듯
            }
        }
    }

    static void bfs() {
        Queue<location> queue = new LinkedList<location>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && visited[i][j] == false) { // for 문을 돌면서 1인경우를 찾는다.
                    location loc = new location(i, j);
                    visited[i][j] = true; // 찾으면 방문한거
                    queue.offer(loc);// 찾을 경우 큐에 넣는다.
                    int tmpCnt = 1;
                    while (!queue.isEmpty()) {
                        // 큐에서 poll 한뒤
                        location tmp = queue.poll();
                        for (int k = 0; k < 4; k++) { // 상하좌우를 보면서 주변의 단지를 찾는다.
                            int row = tmp.x + dx[k];
                            int col = tmp.y + dy[k];

                            if (row < 0 || row > M || col < 0 || col > M) {
                                continue;
                            }//범위 밖일경우 continue
                            if (map[row][col] == 1 && visited[row][col] == false) //지도내 1이면서 방문하지 않았을 경우에
                            {
                                visited[row][col] = true; // 방문했다고 바꿔주고
                                queue.offer(new location(row, col)); // 큐에 넣어준다.
                                tmpCnt++;// 집수 증가
                            }
                        }
                        houseCnt[count] = tmpCnt;// 집수 저장
                    }
                    count++;// 단지가 바뀔때마다 카운트 증가
                }
            }
        }

    }
}
