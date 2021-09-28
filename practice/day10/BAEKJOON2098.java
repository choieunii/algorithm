package practice.day10;

import java.io.*;
import java.util.*;

// 2098 외판원 순회   TSP
public class BAEKJOON2098 {

    static int N;
    static int[][] map;
    static int[][] dp;

    private static int INF = 50000000;

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

        bw.write(String.valueOf(TSP(0, 1)));

        bw.flush();
        bw.close();
        br.close();
    }

    static int TSP(int id, int visited) {
        // 1. 모든 지점을 방문한 경우
        if(visited == (1<<N)-1) {
            if (map[id][0] == 0) return INF;
            return map[id][0];
        }

        // 2. 이미 계산한 경우
        if (dp[id][visited] != INF) return dp[id][visited];

        // 3. 반복문 돌면서 재귀탐색
        for (int i = 0; i<N; i++) {
            int next = visited | (1<<i);  // 다음 방문할 녀석
            // 3-1. 방문한 경우 continue
            if((visited & (1 << i)) != 0 ) continue;
            // 3-2. 길이 없는 경우 continue
            if(map[id][i]==0) continue;
            // 3-3. TSP 진행
            dp[id][visited] = Math.min(
                    dp[id][visited], TSP(i, next) + map[id][i]);
        }
        return dp[id][visited];
    }

}