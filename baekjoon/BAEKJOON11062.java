package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEKJOON11062 {
    static int T, N;
    static int[] input;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            input = new int[N+1];
            for(int i=1; i<=N; i++){
                input[i] = Integer.parseInt(st.nextToken());
            }

            dp = new int[2][N+1][N+1];
            int ans = cardGame(1,N,0);

            System.out.println(ans);
        }
    }


    static int cardGame(int start, int end, int flag){
        if(start == end){
            if(flag == 0) {
                return dp[flag][start][end] = input[start];
            }else {
                return dp[flag][start][end] = 0;
            }
        }

        if(dp[flag][start][end] !=0) return dp[flag][start][end];

        if(flag == 0){
            dp[flag][start][end] = Math.max(cardGame(start +1 , end, 1) + input[start], cardGame(start, end -1, 1) + input[end]);
        } else {
            dp[flag][start][end] = Math.min(cardGame(start +1, end, 0) , cardGame(start, end -1,0));
        }
        return dp[flag][start][end];
    }
}
