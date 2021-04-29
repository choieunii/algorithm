package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEKJOON15651 {
    static int N;
    static int M;
    static boolean [] visit;
    static int [] buffer;
    static int count;
    static StringBuilder sb = new StringBuilder();
    public static void dfs(int depth) {
        // 탈출 조건
        if(depth > M){

        }

        //완성 조건
        if(depth == M){
            for(int i=0;i<M;i++){
                sb.append(buffer[i]+1 + " ");
            }
            sb.append("\n");
            return;
        }

        //실행부
        for(int i = 0; i < N; i++){

            if(!visit[i]){
                buffer[depth] = i;
                dfs(depth + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N];
        buffer = new int[M];
        dfs(0);
        System.out.println(sb.toString());

    }
}
