package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon9461DP {
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        dp = new long[1000];

        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;

        for (int i = 6; i < 1000; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }

        for(int i=1;i<=cnt;i++){
            int N = Integer.parseInt(br.readLine());
            System.out.println(dp[N]);
        }
    }
}
