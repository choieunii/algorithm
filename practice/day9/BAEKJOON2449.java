package practice.day9;

import java.io.*;
import java.util.*;

// 14003 가장 긴 증가하는 부분 수열 5
public class BAEKJOON2449 {

    static int N, K;
    static int[] input;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        input = new int[N];
        dp = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        divideConquer(0, N - 1);

        bw.write(String.valueOf(dp[0][N - 1]));

        bw.flush();
        bw.close();
        br.close();
    }

    static int divideConquer(int start, int end) {
        // 1. 자기 자신이면 return 0;
        if (start == end)
            return 0;

        // 2. 이미 dp[start][end]를 알고 있으면 return
        int ret = dp[start][end];
        if (ret != 0)
            return ret;

        // 3. 가장 바닥까지 쪼개서 값 확인
        ret = Integer.MAX_VALUE; // 최대치로 두고 갱신 예정
        int left, right;
        for (int i = start; i < end; i++) {
            left = divideConquer(start, i);
            right = divideConquer(i + 1, end);

            // 3-1. 양쪽 구간의 색이 같은 경우
            if (input[start] == input[i + 1]) {
                ret = Math.min(ret, left + right);
            }
            // 3-2. 양쪽 구간의 색이 다르면 비용 1 추가
            else
                ret = Math.min(ret, left + right + 1);
        }
        return dp[start][end] = ret;
    }

}