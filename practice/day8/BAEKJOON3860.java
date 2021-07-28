package practice.day8;

import java.io.*;
import java.util.*;

// 3860 할로윈 묘지
public class BAEKJOON3860 {

    static class Edge {
        Point start, end;
        int cost;

        public Edge(Point start, Point end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int W, H, G, E; // W : 맵의 가로 H : 세로 G : 묘비의 개수 E : 귀신 구멍 수
    static int[][] map; // -1 : 묘비 1 : 귀신구멍
    static long[][] dist;
    static ArrayList<Edge> edgeList;
    static boolean infFlag;

    // 좌표이동용 변수
    static final int[] dr = { -1, 1, 0, 0 };
    static final int[] dc = { 0, 0, -1, 1 };

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder(); // 정답 출력용
        for (;;) {

            // 1. 입력 & 초기화
            StringTokenizer st;
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            // 0-0 이 입력되면 종료
            if (W == 0 && H == 0)
                break;

            map = new int[H][W];
            dist = new long[H][W];
            infFlag = false;

            // 묘비 입력
            G = Integer.parseInt(br.readLine());

            int x, y;
            for (int i = 1; i <= G; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                // 묘비는 못 가는 곳이므로 map에 -1 표시
                map[y][x] = -1;
            }

            // 귀신구멍 입력
            E = Integer.parseInt(br.readLine());

            edgeList = new ArrayList<Edge>();

            int x1, y1, x2, y2, t;
            for (int i = 1; i <= E; i++) {
                st = new StringTokenizer(br.readLine());
                x1 = Integer.parseInt(st.nextToken());
                y1 = Integer.parseInt(st.nextToken());
                x2 = Integer.parseInt(st.nextToken());
                y2 = Integer.parseInt(st.nextToken());
                t = Integer.parseInt(st.nextToken());

                // 귀신구멍에 1 표시
                map[y1][x1] = 1;
                // 간선리스트에 Edge 추가
                edgeList.add(new Edge(new Point(y1, x1), new Point(y2, x2), t));
            }

            // 2. BFS로 가능한 모든 경로를 edgeList에 추가하기
            makePath();

            // 3. 밸만 포드
            // ** dist는 출발지 1 제외하고 모두 무한대로
            BellmanFord();

            // 4. 출력
            // 4-1. 무한루프가 가능하면 -1 출력
            if (infFlag) {
                sb.append("Never\n");
            }
            // 4-2. 무한루프 없으면 모든 도시의 최솟값 출력
            else {
                if (dist[H - 1][W - 1] == INF) {
                    sb.append("Impossible\n");
                } else {
                    sb.append(dist[H - 1][W - 1] + "\n");
                }
            }

        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    static void makePath() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                // 묘비 or 귀신구멍이거나 도착지이면 continue
                if (map[i][j] != 0 || (i == H - 1 && j == W - 1)) continue;

                for (int k = 0; k<=3; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];

                    if ( nr >=0 && nr <H && nc >= 0 && nc < W && map[nr][nc] >=0 ) {
                        edgeList.add(new Edge(new Point(i,j), new Point(nr, nc), 1));
                    }
                }
            }
        }
    }

    static void BellmanFord() {

        // dist INF로 초기화
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                dist[i][j] = INF;
            }
        }
        // 출발점은 0으로
        dist[0][0] = 0;

        // 1. N - 1번 동안 간선 M을 모두 확인하기
        for (int i = 0; i < W * H; i++) {

            int len = edgeList.size();
            for (int j = 0; j < len; j++) {
                Edge now = edgeList.get(j);
                Point start = now.start;
                Point end = now.end;

                // 1-1. 귀신구멍 출발지가 현재 무한대이면 continue
                if (dist[start.y][start.x] == INF)
                    continue;

                // 1-2. 최솟값으로 값 갱신 가능하면 갱신
                dist[end.y][end.x] = Math.min(dist[end.y][end.x], dist[start.y][start.x] + now.cost);
            }
        }

        // 2. 마지막으로 간선 M을 모두 확인해서 갱신이 발생하면 무한루프
        int len = edgeList.size();
        for (int j = 0; j < len; j++) {
            Edge now = edgeList.get(j);
            Point start = now.start;
            Point end = now.end;

            // 2-1. 귀신구멍 출발지가 현재 무한대이면 continue
            if (dist[start.y][start.x] == INF)
                continue;

            // 갱신이 발생한다면 무한루프에 빠질 수 있음
            if (dist[start.y][start.x] + now.cost < dist[end.y][end.x]) {
                infFlag = true;
                return;
            }
        }
    }

}