package practice.day9;

import java.io.*;
import java.util.*;

public class BAEKJOON7579 {

    static class info {
        int mem, cost;

        public info(int mem, int cost) {
            this.mem = mem;
            this.cost = cost;
        }

        public int getMem() {
            return mem;
        }

        public void setMem(int mem) {
            this.mem = mem;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

    }

    static int N, M, ans, costSum;
    static int[][] dp;
    static info[] array;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력 받기
        StringTokenizer st = new StringTokenizer (br.readLine()) ;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int ans = 0;
        // dp[i][j] : i번째 앱까지 확인했을때, j cost를 소모해서 얻을 수 있는 최대 메모리
        int[][] dp = new int[N + 1][10001];		// 메모리 한계 100*100 = 10000

        array = new info[N+1];
        st = new StringTokenizer (br.readLine()) ;
        for (int i= 1; i<=N; i++) {
            array[i] = new info(0,0);
            array[i].setMem(Integer.parseInt(st.nextToken()));
        }

        costSum = 0;
        st = new StringTokenizer (br.readLine()) ;
        for (int i= 1; i<=N; i++) {
            array[i].setCost(Integer.parseInt(st.nextToken()));
            costSum += array[i].getCost();
        }

        // 2. 냅색 알고리즘 실행
        for (int i = 1; i <= N; i++)
        {
            for (int j = 0; j <= costSum; j++)
            {
                // 2-1. 갱신이 가능하면 둘 중 큰 값 넣어주기
                if (j - array[i].getCost() >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - array[i].getCost() ] + array[i].getMem());
                }
                // 2-2. 아니면 원래 있던 값
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            }
        }

        // 3. 최소비용 확인
        for (int i = 0; i <= costSum; i++)
        {
            // M바이트 확보한 최소비용
            if (dp[N][i] >= M)
            {
                ans = i;
                break;
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }
}