package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class BAEKJOON9095 {
    static int dp[];
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());
            dp = new int[tmp + 4];
            System.out.println(dfs(tmp));
        }
    }

    static int dfs(int count) {
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        if (dp[count] == 0) {
            for (int i = 4; i < count + 1; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }
        }
        return dp[count];
    }
}
