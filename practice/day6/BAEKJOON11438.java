package practice.day6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BAEKJOON11438 {
    static int N,M,K;

    static int[] depth;
    static int[][] parent;

    static ArrayList[] tree;
    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("path"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 1. 입력 & 변수 준비
        N = Integer.parseInt(br.readLine());
        // 2^K > N인 K 찾기
        K = 0;
        for(int i=1;i<=N;i*=2){
            K++;
        }

        //LCA 관련 변수 초기화
        depth = new int[N+1];
        parent = new int[K][N+1];

        //TREE 변수 초기화
        tree = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            tree[i] = new ArrayList<Integer>();
        }

        int a,b;
        for(int i=1;i<=N-1;i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            //양방향 간선
            tree[a].add(b);
            tree[b].add(a);
        }

        // 2. DEPTH 확인
        dfs(1,1);

        // 3. 2^N 까지 parent 채우기
        fillParent();

        // 4. LCA 진행
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            sb.append(lca(a,b)+"\n");
        }

        System.out.println(sb.toString());

    }

    //DEPTH 확인 DFS
    static void dfs(int id, int cnt){
        //1. depth를 기록
        depth[id] = cnt;
        //2. 자식들의 depth를 기록
        int len = tree[id].size();
        for(int i=0;i<len;i++){
            int next = (Integer)tree[id].get(i);
            //아직 깊이를 모르면 -> 미방문 노드
            if(depth[next] == 0){
                dfs(next, cnt+1);
                parent[0][next] = id;
            }
        }
        return;
    }

    //부모 채우기
    static void fillParent(){
        for(int i=1;i<K;i++){
            for(int j=1;j<=N;j++){
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }
    }

    //최소 공통 조상
    static int lca(int a, int b){
        //1. depth[a] >= depth[b] 이도록 조정하기
        if(depth[a]<depth[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }
        //2. 더 깊은 a를 2^K승 점프하여 depth를 맞추기
        for(int i=K-1;i>=0;i--){
            if(Math.pow(2,i) <= depth[a] - depth[b]){
                a = parent[i][a];
            }
        }

        //3. depth를 맞췄는데 같다면 종료
        if(a==b) return a;

        //4. a-b는 같은 depth이므로 2^K승 점프하며 공통부모 바로 아래까지 올리기
        for(int i=K-1;i>=0;i--){
            if(parent[i][a]!=parent[i][b]){
                a = parent[i][a];
                b = parent[i][b];
            }
        }
        return parent[0][a];
    }
}
