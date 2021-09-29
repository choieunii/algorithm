package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 9252번 LCS2
public class BAEKJOON9252 {
    static int N, M;
    static String inputA, inputB;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputA = br.readLine();
        inputB = br.readLine();

        N = inputA.length();
        M = inputB.length();

        int ans = getLCSLength();

        StringBuilder sb = new StringBuilder();
        while(N!=0 && M!=0){
            if(inputA.charAt(N-1) == inputB.charAt(M-1)){
                sb.insert(0, inputA.charAt(N-1));
                N--;
                M--;
            } else if(dp[N][M] == dp[N-1][M]) N--;
            else if(dp[N][M] == dp[N][M-1]) M--;
        }

        System.out.println(ans + "\n" + sb.toString());
    }

    static int getLCSLength(){
        dp = new int[N+1][M+1];

        for(int i=1; i<=N; i++){
            for(int j=1;j<=M;j++){
                if(inputA.charAt(i-1) == inputB.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[N][M];
    }
}
