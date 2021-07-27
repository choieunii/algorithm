package practice.day7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.*;
import java.util.*;

// 11404 플로이드
public class BAEKJOON11404 {

    static BufferedReader br;
    static BufferedWriter bw;

    static int n, m;
    static int a, b, c;
    static int[][] dist;

    static final int INF = 20000000;

    static class Edge {
        int target, cost;

        public Edge(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // 1. 거리배열 무한대로 초기화
        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = INF;
            }
        }

        // 2. 양방향 간선으로 입력받기
        StringTokenizer st;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            // 새로운 버스 비용이 적은 경우 갱신
            if (c < dist[a][b]) {
                dist[a][b] = c;
            }
        }

        // 3. 플로이드-워셜 진행
        // 3중 for문 순서 경유 - 출발 - 도착
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    // 기존 (출발-도착) > (출발-경유) + (경유-도착) 이면 갱신
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 4. 전체 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i==j) {
                    sb.append(0+" ");
                }
                else if (dist[i][j] == INF) {
                    sb.append(0+" ");
                }
                else {
                    sb.append(dist[i][j]+" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

}