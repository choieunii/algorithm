package practice.day6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BAEKJOON1922 {
    static int N,M, ans, Ecnt;
    static int[] parent; // Union Find 용 parent 배열
    static PriorityQueue<Info> pq;

    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("path"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for (int i=1 ; i<=N; i++) parent[i] = i;

        pq = new PriorityQueue<>();
        for(int i=1;i<=M;i++){
            int start, target, cost;
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            //cost 순서로 최소 힙 정렬
            pq.offer(new Info(start, target, cost));
        }

        ans = Ecnt = 0;
        while(!pq.isEmpty()){
            if(Ecnt==N-1) break; // 간선 개수가 N-1 일 경우 모든 간선이 연결된 상황

            Info cur = pq.poll();
            if(cur.start == cur.target) continue; // 같은 컴퓨터는 패스

            if(find(cur.start)!=find(cur.target)){ // 연결되어 있지 않을 경우 연결 및 비용 추가
                Ecnt++;
                union(cur.start,cur.target);
                ans+=cur.cost;
            }
        }
        System.out.println(ans);

    }
    static int find(int a){
        if(parent[a]==a) return a;
        return find(parent[a]);
    }
    static void union(int a, int b){
        parent[find(a)] = find(b);
    }
}
class Info implements Comparable<Info>{
    int start, target, cost;

    public Info(int start, int target, int cost) {
        this.start = start;
        this.target = target;
        this.cost = cost;
    }

    @Override		// 최소힙 비교연산자
    public int compareTo(Info o) {
        return this.cost-o.cost;
    }
}