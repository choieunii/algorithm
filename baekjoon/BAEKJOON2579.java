package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//백준 2579번 계단오르기
public class BAEKJOON2579 {
    static int N,M;
    static int[] dp;
    static int[] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        score = new int[N+1];
        dp = new int[N+1];

        for(int i=1;i<=N;i++){
            score[i] = Integer.parseInt(br.readLine());
        }


    }
}
