package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class info {
    int row, column;
    public info(int row, int column){
        this.row = row;
        this.column = column;
    }
}
public class BAEKJOON11049 {
    static int N;
    static int[][] dp;
    static info[] input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][N+1];
        input = new info[N+1];

        int r,c;
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            input[i] = new info(r,c);
            for(int j=1;j<=N;j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        divideConquer(1,N);
        System.out.println(dp[1][N]);
    }

    static int divideConquer(int start, int end){
        if(start == end) return 0;
        if(dp[start][end] != Integer.MAX_VALUE){
            return dp[start][end];
        }
        int left, right;

        for(int i=start; i<end; i++){
            left = divideConquer(start,i);
            right = divideConquer(i+1, end);
            dp[start][end] = Math.min(dp[start][end], left+right+(input[start].row * input[i].column * input[end].column)); // 최소값 비교 후 업데이트
        }
        return dp[start][end];
    }
}
