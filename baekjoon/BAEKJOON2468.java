package baekjoon;

import java.io.*;
import java.util.*;

public class BAEKJOON2468 {
    public static int N;
    public static int map[][];
    public static int dx[] = {0,1, 0, -1};
    public static int dy[] = {-1, 0, 1, 0};
    public static boolean visited[][];
    public static int cnt = 0;
    public static ArrayList<Node> list = new ArrayList<>();

    public static class Node {
        int x;
        int y;

        Node(int xx, int yy) {
            this.x = xx;
            this.y = yy;
        }
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int result = 1;
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]); //최소 높이와
                max = Math.max(max, map[i][j]); //최대 높이 구하기
            }
        }
        for (int t = min; t <= max; t++) {
            visited = new boolean[N+1][N+1];
            cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > t) { //만약에 방문 하지 않았고, map 이 현재 높이보다 크다면
                        bfs(i, j, t); //주위를 탐색한다.
                    }
                }
            }
            result = Math.max(result, cnt);
        }
        System.out.println(result);
    }

    public static void bfs(int i, int j, int depth) {
        Queue<Node> q = new LinkedList<>();
        visited[i][j] = true;
        q.offer(new Node(i, j));
        while (!q.isEmpty()) {
            Node tmp = q.poll();
            int cx = tmp.x;
            int cy = tmp.y;
            for (int k = 0; k < 4; k++) {
                int nx = cx + dx[k]; //상하좌우를 보면서
                int ny = cy + dy[k];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue; //범위 밖이면 패스
                if (visited[nx][ny] || map[nx][ny] <= depth) continue; //높이보다 작거나 방문 했어도 패스
                q.offer(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }
        cnt++;
    }
}
