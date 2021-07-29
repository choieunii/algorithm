package practice.day8;

import java.io.*;
import java.util.*;

// 백준 11659
public class BAEKJOON11659 {

    static int N, M;
    static int start,end;
    static int [] num, dp;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N+1];
        dp = new int[N+1];

        // 1. 입력 받으면서 누적합 구하기
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N;i++){
            num[i] = Integer.parseInt(st.nextToken());
            dp[i] = dp[i-1] + num[i];
        }

        // 2. i ~ j 범위 구간합 구하기
        sb = new StringBuilder();
        for (int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            sb.append(dp[end]-dp[start-1]+"\n");

        }

        // 3. 출력
        System.out.println(sb);
    }
}
