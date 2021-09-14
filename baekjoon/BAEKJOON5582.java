package baekjoon;

// 백준 5582번 공통 부분 문자열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BAEKJOON5582 {
    static String inputA, inputB;
    static int N, M;
    static int[][]dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        inputA = br.readLine();
        inputB = br.readLine();
        N = inputA.length();
        M = inputB.length();

        int ans = 0;
        int [][]dp = new int[N+1][M+1];

        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                if(inputA.charAt(i -1) == inputB.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] +1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }

        System.out.println(ans);
    }
}
