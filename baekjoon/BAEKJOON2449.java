package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEKJOON2449 {
    static int N, K;
    static int[] input;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        input = new int[N];
        dp = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        divideConquer(0,N-1);

    }

    static int divideConquer(int start, int end){
        if(start == end) return 0;
        int ret = dp[start][end];
        if(ret !=0) return ret;
        ret = Integer.MAX_VALUE;
        for(int i=start; i<end; i++){
            int left = divideConquer(start, i);
            int right = divideConquer(i+1, end);

            if(input[start] == input[i+1]){
                ret = Math.min(ret, left + right);
            }else{
                ret = Math.min(ret, left + right +1);
            }
        }
        return dp[start][end] = ret;

    }
}
