package baekjoon;

// 백준 2342 댄스댄스레볼루션

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BAEKJOON2342 {
    static ArrayList<Integer> input;
    static int[][][]dp;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        input = new ArrayList<Integer>();
        for(;;) {
            int in = Integer.parseInt(st.nextToken());
            if(in==0) break;
            input.add(in);
        }
        N = input.size();
        dp = new int[N+1][5][5];

        int ans = DDR(0,0,0);
        System.out.println(ans);

    }

    static int DDR(int step, int left, int right){
        if(step == N) return 0;
        if(dp[step][left][right] !=0) return dp[step][left][right];
        int leftScore = score(left, input.get(step)) + DDR(step+1, input.get(step), right);
        int rightScore = score(right, input.get(step)) + DDR(step+1, left, input.get(step));
        return dp[step][left][right] = Math.min(leftScore, rightScore);
    }

    static int score(int from, int to){
        if(from == to) return 1;
        if(from == to) return 2;
        if(Math.abs(from-to) == 2) return 4;
        return 3;
    }
}
