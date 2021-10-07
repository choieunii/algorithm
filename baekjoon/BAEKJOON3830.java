package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 3830번 교수님은 기다리지 않는다 Union-Find
public class BAEKJOON3830 {
    static int N, M;
    static int[] parent;
    static long[] dist;
    static String type;
    static int a,b,w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(;N!=0 && M!=0;){
            parent = new int[N+1];
            for(int i=1;i<=N;i++){
                parent[i] = i;
            }
            dist = new long[N+1];

            for(int i=1;i<=M;i++){
                st = new StringTokenizer(br.readLine());
                type = st.nextToken();

                if(type.equals("!")){
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());
                    w = Integer.parseInt(st.nextToken());

                    union(a,b,w);
                }else {
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());

                    if(find(a)!= find(b)){
                        sb.append("UNKNOWN\n");
                    }else {
                        sb.append((dist[b] - dist[a] + "\n"));
                    }
                }
            }

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb.toString());
    }

    static int find(int id){
        if(parent[id] == id) return id;

        int pId = find(parent[id]);
        dist[id] += dist[parent[id]];

        return parent[id] = pId;
    }

    static void union(int a, int b, long diff){
        int pa = find(a);
        int pb = find(b);

        if(pa == pb) return;

        dist[pb] = dist[a] - dist[b] + diff;
        parent[pb] = pa;
        return;
    }
}
