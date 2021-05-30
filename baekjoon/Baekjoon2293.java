package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon2293 {
    static int[] dp;
    static int[] value;
    static int n;
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        value = new int[n+1];
        dp = new int[k+1];

        dp[0]=1;
        for(int i = 1 ; i <= n; i++) {
            value[i] = Integer.parseInt(br.readLine());
            for (int j = value[i]; j <= k; j++)
                dp[j] += dp[j - value[i]];
        }

        System.out.println(dp[k]);
    }
}
