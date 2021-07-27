package practice.day7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEKJOON3830 {
    static int N,M;
    static int[] parent;
    static long[] dist;
    static String type;
    static int a,b,w;
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("path"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (; N != 0 && M != 0;) {
            // Union Find를 위한 parent 초기화
            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }
            dist = new long[N + 1];

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                type = st.nextToken();

                // 1. 무게를 쟀을 경우 - 판단 가능한 그룹으로 union
                if (type.equals("!")) {
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());
                    w = Integer.parseInt(st.nextToken());

                    union(a, b, w);
                }
                // 2. 무게를 판단하는 경우
                else {
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());

                    // 2-1. 무게를 판단할수 없는 경우 UNKNOWN
                    if (find(a) != find(b)) {
                        sb.append("UNKNOWN\n");
                    }
                    // 2-2. 무게를 판단할 수 있는 경우
                    else {
                        sb.append((dist[b] - dist[a]) + "\n");
                    }
                }
            }
            // 다음 테스트 케이스의 N, M 입력받기
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb.toString());
    }
    static int find(int id) {
        // 1. root인 경우 기존 union find 동일
        if (parent[id] == id)
            return id;
        // 2. root가 아닌 경우 root와의 거리를 구해서 갱신
        int pId = find(parent[id]);
        dist[id] += dist[parent[id]];
        return parent[id] = pId;
    }

    static void union(int a, int b, long diff) {
        int pa = find(a);
        int pb = find(b);

        // 이미 같은 그룹이면 거리 계산이 되어있으므로 종료
        if (pa == pb)
            return;

        // parent를 변경하고, 무게 차이를 갱신
        dist[pb] = dist[a] - dist[b] + diff;
        parent[pb] = pa;

        return;
    }
}
