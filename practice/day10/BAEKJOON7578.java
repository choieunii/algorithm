package practice.day10;

import java.io.*;
import java.util.*;

//7578 공장
public class BAEKJOON7578 {

    static int N;
    static long ans;
    static int[] factory;
    static int[] num;
    static long[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        factory = new int[N + 1];
        tree = new long[N*4+10];
        num = new int [1000001];	// 최대치

        int tmp;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            tmp = Integer.parseInt(st.nextToken());
            num[tmp] = i;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            tmp = Integer.parseInt(st.nextToken());
            factory[i] = num[tmp];
        }

        ans = 0;
        int val;
        for (int i = 1; i <= N; i++) {
            val = factory[i];

            // val보다 큰 인덱스 중 방문한 적 있는 인덱스의 개수를 더함
            ans += sum(1, N, 1, val + 1, N);

            // val를 방문했다는 의미로 1을 더해 줌.+ val이 포함된 구간합도 추가
            update(1, N, 1, val, 1);
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static long sum(int start, int end, int node, int left, int right) {
        if (end < left || right < start) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    public static void update(int start, int end, int node, int id, int diff) {
        if (id < start || id > end) {
            return;
        }

        tree[node] += diff;

        if (start != end) {
            int mid = (start + end) / 2;
            update(start, mid, node * 2, id, diff);
            update(mid + 1, end, node * 2 + 1, id, diff);
        }
    }

}