package practice.day8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.*;
import java.util.*;

// 11657 타임머신
public class BAEKJOON11657 {

    static class Edge {
        int start, target, cost;

        public Edge(int start, int target, int cost) {
            this.start = start;
            this.target = target;
            this.cost = cost;
        }
    }

    static int N, M; // N - 도시 수, M - 버스(간선) 수
    static long[] dist;
    static Edge[] edgeList;
    static int ans; // 출력할 답
    static boolean infFlag;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edgeList = new Edge[M + 1];

        int a, b, c;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            // 간선리스트로 받기
            edgeList[i] = new Edge(a, b, c);
        }

        // 2. 밸만 포드
        // ** dist는 출발지 1 제외하고 모두 무한대로
        dist = new long [N+1];
        for (int i = 2; i <= N; i++) {
            dist[i] = INF;
        }
        BellmanFord();

        // 3. 출력
        // 3-1. 무한루프가 가능하면 -1 출력
        if (infFlag) {
            System.out.println("-1");
        }
        // 3-2. 무한루프 없으면 모든 도시의 최솟값 출력
        else {
            StringBuilder sb = new StringBuilder(); // 정답 출력용
            for (int i = 2; i<=N; i++) {
                if (dist[i]==INF) {
                    sb.append("-1\n");
                }
                else {
                    sb.append(dist[i]+"\n");
                }
            }
            System.out.println(sb.toString());
        }
    }

    static void BellmanFord() {
        // 1. N - 1번 동안 간선 M을 모두 확인하기
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= M; j++) {
                Edge now = edgeList[j];

                // 1-1. 출발지가 현재 무한대이면 continue
                if (dist[now.start] == INF)
                    continue;
                // 1-2. 최솟값으로 값 갱신 가능하면 갱신
                dist[now.target] = Math.min(dist[now.target], dist[now.start] + now.cost);
            }
        }

        // 2. 마지막으로 간선 M을 모두 확인해서 갱신이 발생하면 무한루프
        for (int j = 1; j <= M; j++) {
            Edge now = edgeList[j];

            if (dist[now.start] == INF)
                continue;

            // 갱신이 발생한다면 무한루프에 빠질 수 있음
            if (dist[now.start] + now.cost < dist[now.target]) {
                infFlag = true;
                return;
            }
        }
    }

}
