package practice.day7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.*;
import java.util.*;


// 백준 2458 키순서
public class BAEKJOON2458 {

    static int N, M;					// N : 학생의 수  /  M : 연산의 수
    static int a, b;  					// input 받는 학생 a, b
    static int[][] dist;				// floyd-warshall을 위한 2차원 배열
    static final int INF = 999999999;	// 초기화를 위한 불가능한 최댓값
    static int ans;						// 출력할 정답

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1. 2차원 배열에 INF (최댓값)으로 초기화
        dist = new int [N+1][N+1];
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                dist[i][j] = INF;
            }
        }

        // 2. 입력 : a - b 학생의 키 순서를 아는 경우 1로 거리 배열 입력
        for (int i = 1; i<= M; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            dist[a][b] = 1;
        }

        // 3. 특정 학생이 모든 학생과의 거리를 체크해야하므로 플로이드 워셜 수행
        // 플로이드-워셜 : 경유지 - 출발지 - 도착지 3중 for문
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // dist[i][j]보다 작은 값 나올 경우 갱신
                    dist[i][j] = dist[i][j] <= dist[i][k] + dist[k][j] ? dist[i][j] : dist[i][k] + dist[k][j];
                }
            }
        }

        // 4. 모든 학생과의 비교가 가능한 경우
        //    → 거리가 INF 가 아닌 학생의 수가 N-1인 경우 : 키가 몇번째인지 알 수 있으므로 ans++
        ans = 0;
        for (int i = 1; i<=N; i++) {
            int cnt = 0;
            for (int j=1; j<=N; j++) {
                if (dist[i][j] != INF || dist[j][i] != INF ) cnt++;
            }
            if (cnt == N-1) ans++;
        }
        System.out.println(ans);
    }

}