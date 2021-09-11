package practice.day8;

import java.io.*;
import java.util.*;

// 1854 K번째 최단경로 찾기
public class BAEKJOON1854 {

    static int N, M, K;								// N : 도시 수, M : 도로 수, K : K번째 경로
    static ArrayList[] adjList;						// 인접리스트
    static PriorityQueue<Integer>[] dist;			// dist[i]  i번째 최단경로 - PQ로 K개 까지 저장

    static class edge implements Comparable<edge>{
        int target, cost;

        public edge(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }

        @Override		// 사전순 출력
        public int compareTo(edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 1. 입력 & 변수 준비
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 1-1. 인접리스트, 거리배열 초기화
        adjList = new ArrayList[N+1];
        dist = new PriorityQueue[N+1];
        for (int i = 1; i<=N; i++) {
            adjList[i] = new ArrayList<Integer>();
            dist[i] = new PriorityQueue<Integer>(K);	// K개만큼만 할당
        }

        // 1-2. 단방향 간선 입력
        int a, b, c;
        for (int i = 1; i<= M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            adjList[a].add(new edge(b,c));
        }

        // 2. K번째 최단경로를 찾는 다익스트라
        kthDijkstra(1);

        // 3. 출력
        StringBuilder sb = new StringBuilder ();
        for (int i = 1; i<=N; i++) {
            // K번째 최단경로 존재하는 경우
            if (dist[i].size() == K) sb.append((dist[i].peek() * -1)+"\n");
                // 존재하지 않는 경우
            else sb.append("-1\n");
        }
        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    static void kthDijkstra(int start) {
        // 1. 일반 다익스트라처럼 출발점 비용 0 넣고 시작
        PriorityQueue<edge> pq = new PriorityQueue<edge>();
        pq.add(new edge(start,0));
        dist[start].add(0);

        // 2. pq를 비울때까지 다익스트라 진행 (K번째 경로를 확인해야하므로 모두 확인)
        while(!pq.isEmpty()) {
            edge now = pq.poll();

            int len = adjList[now.target].size();
            for (int i = 0; i<len; i++) {

                edge next = (edge) adjList[now.target].get(i);

                // 2-1. 저장된 최단비용이 K개 이하인 경우 PQ에 넣기 -> 넣으면 알아서 정렬
                if (dist[next.target].size() < K) {
                    // minHaep을 maxHeap으로 바꾸기 위해 -1을 곱해서 dist pq에 넣기
                    dist[next.target].add((now.cost + next.cost)* -1 );
                    // 일반 다익스트라와 동일하게 최솟값이 경신되었으므로 pq에 넣기
                    pq.add(new edge(next.target, now.cost + next.cost));
                }

                // 2-2. 저장된 비용이 K개이므로, dist MAX 값과 비교해서 작은 값을 dist PQ에 갱신
                else if ((dist[next.target].peek())*-1 > (now.cost + next.cost)) {
                    dist[next.target].poll();	// 뽑기
                    // minHaep을 maxHeap으로 바꾸기 위해 -1을 곱해서 dist pq에 넣기
                    dist[next.target].add((now.cost+next.cost)*-1);
                    // 일반 다익스트라와 동일하게 최솟값이 경신되었으므로 pq에 넣기
                    pq.add(new edge(next.target, now.cost + next.cost));
                }
            }
        }
    }
}