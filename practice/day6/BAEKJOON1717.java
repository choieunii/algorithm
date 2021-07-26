package practice.day6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEKJOON1717 {
    static int N,M;
    static int type,a,b;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        //초기화
        parent = new int[N+1];
        for(int i=1;i<=N;i++){
            parent[i] =i;
        }

        for(int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            type = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if(type == 0){
                union(a,b);
            }else if(type == 1)
            {
                if(find(a) == find(b)){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }

    static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        parent[pa] = pb;
    }
}
