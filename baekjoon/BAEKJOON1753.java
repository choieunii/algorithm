package baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//백준 1753번 최단 경로, 다익스트라

class Edge implements Comparable<Edge>{
    int id, cost;

    public Edge(int id, int cost){
        this.id = id;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}


public class BAEKJOON1753 {
    static int V, E, K;
    static int u,v,w;
    static int[] dist;
    static ArrayList[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        // 1. 거리 배열
        dist = new int[V+1];
        for(int i=1;i<=V;i++){
            dist[i] = Integer.MAX_VALUE;
        }

        //2. 인접리스트
        adjList = new ArrayList[V+1];
        for(int i=1;i<=V;i++){
            adjList[i] = new ArrayList<Edge>();
        }

        for(int i=1;i<=E;i++){
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            adjList[u].add(new Edge(v,w));
        }

        dijkstra(K);

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=V;i++){
            if(dist[i] == Integer.MAX_VALUE){
                sb.append("INF\n");
            }else {
                sb.append(dist[i]+"\n");
            }
        }
        System.out.println(sb.toString());
        br.close();
    }

    static void dijkstra(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Edge(start,0));

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(now.cost > dist[now.id]) continue;

            int len = adjList[now.id].size();
            for(int i=0; i<len;i++){
                Edge next = (Edge) adjList[now.id].get(i);

                if(dist[next.id] > now.cost + next.cost){
                    dist[next.id] = now.cost + next.cost;
                    pq.add(new Edge(next.id, dist[next.id]));
                }

            }
        }
    }
}
