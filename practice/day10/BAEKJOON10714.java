package practice.day10;

import java.io.*;
import java.util.*;

// 10714 케이크 자르기2
public class BAEKJOON10714 {

    static int N;
    static int[] input;
    static long[][] dp; // dp[l][r] 가져간 가장 왼쪽 위치 l, 가장 오른쪽 위치 r

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
        dp = new long[N+1][N+1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = -1;
            }
        }

        long ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, input[i] + ioi(i, i));
        }
        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }

    static long ioi(int left, int right) {
        // 1. 가장 끝까지 왔으면
        if (goRight(right) == left) {
            return 0;
        }
        // 2. 왼쪽이 더 크면 왼쪽으로
        if (input[goLeft(left)] > input[goRight(right)]) {
            return joi(goLeft(left), right);
        }
        // 3. 아니면 오른쪽으로
        return joi(left, goRight(right));
    }

    static long joi(int left, int right) {
        // 1. 가장 끝까지 왔으면
        if (goRight(right) == left) {
            return dp[left][right] = 0;
        }
        // 2. 갱신이 됐으면
        if (dp[left][right] != -1) {
            return dp[left][right];
        }
        // 3. left / right 뿌린 것 합침
        long leftV = input[goLeft(left)] + ioi(goLeft(left), right);
        long rightV = input[goRight(right)] + ioi(left, goRight(right));

        return dp[left][right] = Math.max(leftV, rightV);
    }

    static int goRight(int id) {
        return (id + 1) % N;
    }

    static int goLeft(int id) {
        return (id + N - 1) % N;
    }
}