package practice.day7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 11266 단절점
public class BAEKJOON11266 {

    static int V,E, order, ans;
    static ArrayList[] adjList;
    static int[] visit;
    static boolean[] isDjj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

        // 1. 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[V+1];
        for (int i=1; i<=V; i++) {
            adjList[i] = new ArrayList<Integer>();
        }

        // 양방향 간선
        int a, b;
        for (int i = 1; i<=E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        // 2. visit 배열, 단절점 여부 초기화 후 V 모두 DFS
        visit = new int [V+1];
        isDjj = new boolean [V+1];

        order = 1;
        for (int i=1; i<=V; i++) {
            if (visit[i] == 0) {
                dfs(i, true, 0);
            }
        }

        // 3. 단절점 개수 count 및 출력
        ans = 0;
        StringBuilder sb = new StringBuilder ();
        for (int i=1; i<=V; i++) {
            if (isDjj[i]) {
                ans++;
                sb.append(i+" ");
            }
        }

        System.out.print(ans+"\n"+sb);
        br.close();
    }

    static int dfs(int id, boolean isRoot, int parent) {

        // 1. 방문 순서 기록 후 자식(child)과 비교 준비
        visit[id] = order;
        order++;

        int ret = visit[id];
        int child = 0;

        // 2. 자식 DFS
        int len = adjList[id].size();
        for (int i = 0; i<len; i++) {
            int now = (int) adjList[id].get(i);

            // 왔던길 돌아가기 방지
            if (now == parent) continue;

            // 2-1. 자식 최초 방문했을 경우
            if (visit[now]==0) {
                child++;

                // 자식 정점 중 방문순서가 빠른 값
                int low = dfs(now, false, id);

                // Root 가 아니고, order 역전이 불가능하면 단절점
                if (!isRoot && low >= visit[id]) {
                    isDjj[id] = true;
                }
                ret = Math.min(ret, low);
            }

            // 2-2. 자식을 이미 방문한 경우
            else {
                ret = Math.min(ret, visit[now]);
            }
        }

        // 3. 루트 정점인 경우 자식 개수가 2개 이상이면 단절점
        if (isRoot && child>=2) {
            isDjj[id] = true;
        }
        return ret;
    }
}