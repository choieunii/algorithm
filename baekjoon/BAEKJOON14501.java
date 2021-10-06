package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 14501번 퇴사 - DP
public class BAEKJOON14501 {
    static int []t, p, dp;
    static int n, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        t = new int[n+2];
        p = new int[n+2];
        dp = new int[n+2];

        for(int i=1; i<=n; i++){
            String[] s = br.readLine().split(" ");
            t[i] = Integer.parseInt(s[0]);
            p[i] = Integer.parseInt(s[1]);
        }

        for(int i=1;i<=n;i++){
            if(i+t[i] <= n+1){
                dp[i+t[i]] = Math.max(dp[i+t[i]],dp[i] + p[i]);
                result = Math.max(result, dp[i+t[i]]);
            }
            dp[i+1] = Math.max(dp[i+1], dp[i]);
            result = Math.max(result, dp[i+1]);
        }
        System.out.println(result);
    }
}
