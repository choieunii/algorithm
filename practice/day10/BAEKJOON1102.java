package practice.day10;

import java.io.*;
import java.util.*;

// 1102 발전소
public class BAEKJOON1102 {

    static int N, P;
    static int[][] map;
    static int[][] dp;
    static String onOff;

    private static int INF = 987654321;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int MAX = 1 << N; // 비트 마스킹을 위한 최대치
        dp = new int[N][MAX];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            for (int j = 0; j < MAX; j++) {
                // 초기값 세팅
                dp[i][j] = INF;
            }
        }

        onOff = br.readLine();
        P = Integer.parseInt(br.readLine());

        int cnt = 0;
        int now = 0;
        for (int i = 0; i < N; i++) {
            if (onOff.charAt(i) == 'Y') {
                now = now | (1 << i);
                cnt++;
            }
        }

        int ans = dfs(cnt, now);
        if (ans == INF) {
            ans = -1;
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    static int dfs(int cnt, int visited) {
        // 1. 발전소 수리가 끝난 경우
        if (cnt >= P) {
            return 0;
        }

        // 2. 이미 계산한 경우
        if (dp[cnt][visited] != INF) {
            return dp[cnt][visited];
        }

        // 3. 반복문 돌면서 재귀탐색
        for (int i = 0; i < N; i++) {
            // 3-1. 발전소가 켜져 있는 경우
            if ((visited & (1 << i)) != 0) {
                for (int j = 0; j < N; j++) {
                    // 3-1-1. 같은 번호의 발전소인 경우 contitnue
                    if (i == j) continue;
                    // 3-1-2. j도 켜져있는 경우 continue
                    if ((visited & (1 << j)) != 0) continue;
                    // 최소값 구하기
                    dp[cnt][visited] =
                            Math.min(
                                    dp[cnt][visited],
                                    dfs(cnt + 1, visited | (1 << j)) + map[i][j]
                            );
                }
            }
        }
        return dp[cnt][visited];
    }

}