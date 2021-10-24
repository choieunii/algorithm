package practice.day8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEKJOON2579 {
    static int N,M;
    static int [] dp;
    static int[] score;
    // 1. 계단은 1칸 또는 2칸씩 오를 수 있다.
    // 2. 연속 3계단 밟기 불가능 (2차원 dp 배열이 필요한 이유)
    // 3. 마지막 도착 계단은 반드시 밟기 (예외 조건 - 나중에 추가)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        score = new int[N+1];
        dp = new int[N+1];
        for(int i=1;i<=N;i++){
            score[i] = Integer.parseInt(br.readLine());
        }

        // 1. 처음 2칸 초기 세팅
        // 1) 1칸 - 밟은게 최댓값, N이 1이면 종료
        dp[1] = score[1];
        if(N ==1){
            System.out.println(dp[N]);
            return;
        }
        // 2) 2칸 까지는 연속 밟는게 최댓값
        dp[2] = dp[1] + score[2];

        // 2. 3~N칸 DP 진행
        for(int i=3;i<=N;i++){
            // 1) 2칸 점프로 온 경우
            // 2) 1칸 점프로 온 경우 (전전 계단은 못 밟음)
            dp[i] = Math.max(dp[i-2] + score[i], dp[i-3]+score[i-1]+score[i]);
        }
        // 3. 마지막 칸은 반드시 밟기
        System.out.println(dp[N]);
    }
}
