package practice.day8;

import java.io.*;
import java.util.*;

// 백준 1932
public class BAEKJOON1932 {

    static int N, ans;
    static int [][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        dp = new int[N+1][N+1];

        // 1. 입력
        for (int i=1; i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=i; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2. 1 ~ (N-1) Row에서 뿌려주는 DP 실행
        dp[1][1] = map[1][1];
        for (int i=1; i<=N-1; i++){
            for (int j=1; j<=i; j++){
                // 가능한 최댓값으로 갱신
                dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);
                dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j]);
            }
            for (int j=1; j<=i+1; j++){
                dp[i+1][j] += map[i+1][j];
            }
        }

        // 3. 마지막 줄에서 가장 큰 값 찾아서 출력
        for (int j=1; j<= N; j++){
            ans = Math.max(ans, dp[N][j]);
        }
        System.out.println(ans);
        br.close();
    }

}