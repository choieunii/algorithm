package practice.day9;

import java.io.*;
import java.util.*;

public class BAEKJOON11049 {

    static class info {
        int row, column;

        public info(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    static int N;
    static int[][] dp;
    static info[] input;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력 받기
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N + 1];
        input = new info[N + 1];

        StringTokenizer st;
        int r, c;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            input[i] = new info(r, c);
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        divideConquer(1, N);

        bw.write(String.valueOf(dp[1][N]));

        bw.flush();
        bw.close();
        br.close();
    }

    static int divideConquer(int start, int end) {
        // 자기 자신일때는 0 리턴
        if (start == end)
            return 0;

        // 갱신된적 있으면 다시 하지 않음
        if (dp[start][end] != Integer.MAX_VALUE) {
            return dp[start][end];
        }
        int left, right;
        for (int i = start; i < end; i++) {
            left = divideConquer(start, i);
            right = divideConquer(i + 1, end);
            dp[start][end] = Math.min(dp[start][end],
                    left + right + (input[start].row * input[i].column * input[end].column));
        }
        return dp[start][end];
    }
}