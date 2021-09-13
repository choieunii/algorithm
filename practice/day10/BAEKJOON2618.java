package practice.day10;

import java.io.*;
import java.util.*;

// 2618 경찰차
public class BAEKJOON2618 {

    static class point {
        int x, y;

        public point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, W;
    static point[] event;
    static int[][] dp; // dp[i][j] = 1번 경찰차가 i번 사건을 처리했고, 2번 경찰차가 j번 사건을 처리했을때 최소비용

    static StringBuilder sb;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        event = new point[W + 1];

        StringTokenizer st;
        int y, x;
        for (int i = 1; i <= W; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            event[i] = new point(x, y);
        }

        dp = new int[W + 1][W + 1];
        for (int i = 0; i <= W; i++) {
            for (int j = 0; j <= W; j++) {
                dp[i][j] = -1;
            }
        }

        sb = new StringBuilder();
        sb.append(getTotDistance(0, 0) + "\n");

        getPath(0, 0);

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    static int getTotDistance(int police1, int police2) {
        // 1. 탈출조건
        if (police1 == W || police2 == W) {
            return 0;
        }

        // 2. 이미 계산한 dp면 가지치기
        if (dp[police1][police2] != -1) {
            return dp[police1][police2];
        }

        // 다음 사건 번호
        int next = Math.max(police1, police2) + 1;

        int dist1, dist2;
        // 경찰 1의 지금까지의 거리
        if (police1 == 0) {
            dist1 = getDist(1, 1, event[next].x, event[next].y);
        } else {
            dist1 = getDist(event[police1].x, event[police1].y, event[next].x, event[next].y);
        }

        // 경찰 2의 지금까지의 거리
        if (police2 == 0) {
            dist2 = getDist(N, N, event[next].x, event[next].y);
        } else {
            dist2 = getDist(event[police2].x, event[police2].y, event[next].x, event[next].y);
        }

        // result1 : 경찰1이 문제 해결한 경우
        int result1 = dist1 + getTotDistance(next, police2);
        // result2 : 경찰2가 문제 해결한 경우
        int result2 = dist2 + getTotDistance(police1, next);

        // 최솟값
        return dp[police1][police2] = Math.min(result1, result2);
    }

    static void getPath(int police1, int police2) {
        if (police1 == W || police2 == W)
            return;

        int next = Math.max(police1, police2) + 1;
        int dist1, dist2;

        if (police1 == 0) {
            dist1 = getDist(1, 1, event[next].x, event[next].y);
        } else {
            dist1 = getDist(event[police1].x, event[police1].y, event[next].x, event[next].y);
        }

        if (police2 == 0) {
            dist2 = getDist(N, N, event[next].x, event[next].y);
        } else {
            dist2 = getDist(event[police2].x, event[police2].y, event[next].x, event[next].y);
        }

        if (dp[next][police2] + dist1 < dp[police1][next] + dist2) {
            sb.append("1\n");
            getPath(next, police2);
        } else {
            sb.append("2\n");
            getPath(police1, next);
        }
    }

    static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

}