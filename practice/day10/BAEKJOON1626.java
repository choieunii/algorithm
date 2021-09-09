package practice.day10;

import java.io.*;
import java.util.*;

//1626 두 번째로 작은 스패닝 트리
public class BAEKJOON1626 {

    static class edge implements Comparable<edge> {
        int start, target, cost;
        boolean isShortest;

        public edge(int start, int target, int cost) {
            this.start = start;
            this.target = target;
            this.cost = cost;
            this.isShortest = false;
        }

        public edge(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }

        @Override
        public int compareTo(edge o) {
            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return "edge [start=" + start + ", target=" + target + ", cost=" + cost + ", isShortest=" + isShortest
                    + "]";
        }

    }

    static int V, E;
    static int[][] dp; // dp[i][j] = 1번 경찰차가 i번 사건을 처리했고, 2번 경찰차가 j번 사건을 처리했을때 최소비용
    static ArrayList<edge> edgeList;
    static int[] parent;

    static ArrayList[] adjList;
    static int[] depth;
    static int[][] lcaParent;
    static int[][] maxDist;
    static int[][] secMaxDist;
    static int K;

    static StringBuilder sb;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<edge>();

        int a, b, w;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            edgeList.add(new edge(a, b, w));
        }

        // 1. MST - 크루스칼
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }
        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<edge>();
        }

        int cnt = 0; // 탈출조건
        int cost = 0; // MST 비용

        edgeList.sort(null);

        for (int i = 0; i < E; i++) {

            if (cnt == V - 1)
                break; // MST 완성시 탈출

            edge now = edgeList.get(i);

            int pa = find(now.start);
            int pb = find(now.target);

            if (pa == pb)
                continue; // 이미 같은 팀
            union(now.start, now.target);
            cost += now.cost;
            now.isShortest = true; // MST 간선 기록

            // lca 준비물
            adjList[now.start].add(new edge(now.target, now.cost));
            adjList[now.target].add(new edge(now.start, now.cost));
            cnt++;
        }

        // 1-1. MST 없는 경우
        if (cnt != V - 1 || E <= V - 1) {
            bw.write("-1");

            bw.flush();
            bw.close();
            br.close();
            return;
        }

        // 2. LCA 구현
        // 2-1. LCA 관련 변수 초기화
        K = 0;
        for (int i = 1; i <= V; i *= 2) {
            K++;
        }

        depth = new int[V + 1];
        lcaParent = new int[K][V + 1];
        maxDist = new int[K][V + 1];
        secMaxDist = new int[K][V + 1];

        // 2-2. DEPTH 확인
        dfs(1, 1);

        // 3. 2^N 까지 parent 채우기
        fillParent();

        // bw.write(sb.toString());

        long ans = Long.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < E; i++) {
            edge now = edgeList.get(i);
            if (now.isShortest) continue;
            max = check(now.start, now.target, now.cost);
            if (max == -1 || max ==  now.cost) continue;
            ans = Math.min(ans, now.cost-max);
        }

        if (ans == Long.MAX_VALUE) {
            bw.write(String.valueOf(-1));
        }
        else {
            bw.write(String.valueOf(ans+cost));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int find(int id) {
        if (parent[id] == id)
            return id;
        return parent[id] = find(parent[id]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        parent[pb] = pa;
    }

    // DEPTH 확인 DFS
    static void dfs(int id, int cnt) {
        // 1. depth를 기록
        depth[id] = cnt;

        // 2. 자식들의 depth를 기록
        int len = adjList[id].size();
        for (int i = 0; i < len; i++) {
            edge next = (edge) adjList[id].get(i);
            // 아직 깊이를 모르면 → 미방문 노드
            if (depth[next.target] == 0) {
                dfs(next.target, cnt + 1);
                lcaParent[0][next.target] = id; // 첫번째 부모를 기록

                maxDist[0][next.target] = next.cost;
                secMaxDist[0][next.target] = -1;
            }
        }
        return;
    }

    // 부모 채우기
    static void fillParent() {
        int max, secMax;
        int paMax, paSecMax;
        for (int i = 0; i < K; i++) {
            for (int j = 1; j <= V; j++) {
                int pid = lcaParent[i][j];
                if (pid!=0 && lcaParent[i][pid]  !=0) {

                    // j가 조상으로 갈때
                    max = maxDist[i][j];
                    secMax = secMaxDist[i][j];

                    // pid가 조상으로 갈때
                    paMax = maxDist[i][pid];
                    paSecMax = secMaxDist[i][pid];

                    if (max > paMax) {
                        maxDist[i+1][j] = max;
                        secMaxDist[i+1][j] = Math.max(paMax, secMax);
                    }

                    else if (max < paMax) {
                        maxDist[i+1][j] = paMax;
                        secMaxDist[i+1][j] = Math.max(max,  paSecMax);
                    }

                    else {
                        maxDist[i+1][j] = max;
                        secMaxDist[i+1][j] = Math.max(secMax, paSecMax);
                    }
                    lcaParent[i+1][j] = lcaParent[i][pid];
                }
            }
        }
    }

    // 최소공통조상
    static int check(int a, int b, int cost) {
        // 1. depth[a] >= depth[b] 이도록 조정하기
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int ret = -1;

        // 2. 더 깊은 a를 2^K승 점프하여 depth를 맞추기
        for (int i = K - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b]) {
                if (maxDist[i][a] != cost) {
                    ret = Math.max(ret, maxDist[i][a]);
                } else if(secMaxDist[i][a] != -1) {
                    ret = Math.max(ret, secMaxDist[i][a]);
                }
                a = lcaParent[i][a];
            }
        }

        // 3. depth를 맞췄는데 같다면 종료
        if (a == b) return ret;

        // 4. a-b는 같은 depth이므로 2^K승 점프하며 공통부모 바로 아래까지 올리기
        for (int i = K - 1; i >= 0; i--) {
            if (lcaParent[i][a] != lcaParent[i][b]) {

                if (maxDist[i][a] != cost) {
                    ret = Math.max(ret, maxDist[i][a]);
                } else if (secMaxDist[i][a] != -1) {
                    ret = Math.max(ret, secMaxDist[i][a]);
                }

                if (maxDist[i][b] != cost) {
                    ret = Math.max(ret, maxDist[i][b]);
                } else if (secMaxDist[i][b] != -1) {
                    ret = Math.max(ret, secMaxDist[i][b]);
                }

                a = lcaParent[i][a];
                b = lcaParent[i][b];
            }
        }

        if (maxDist[0][a] != cost) {
            ret = Math.max(ret, maxDist[0][a]);
        }
        else if (secMaxDist[0][a] != -1) {
            ret = Math.max(ret,  secMaxDist[0][a]);
        }

        if (maxDist[0][b] != cost) {
            ret = Math.max(ret, maxDist[0][b]);
        }
        else if (secMaxDist[0][b] != -1) {
            ret = Math.max(ret,  secMaxDist[0][b]);
        }

        // 원래였으면 lcaParent도 한 번 갱신

        return ret;
    }

}