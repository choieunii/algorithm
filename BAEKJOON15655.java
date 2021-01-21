import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.lang.*;

public class Main {
    static int N;
    static int M;
    static boolean[] visit;
    static int[] buffer;
    static int[] input;
    static int count=0;
    static StringBuilder sb = new StringBuilder();
    public static void dfs(int depth) {
        // 탈출 조건
        if (depth > M) {

        }

        //완성 조건
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(buffer[i]+ " ");
            }
            sb.append("\n");
            return;
        }

        //실행부
        for (int i = 0; i < count; i++) {
            if (!visit[i]) {
                visit[i] = true;
                buffer[depth] = input[i];
                dfs(depth + 1);
                for(int j=i+1;j<count;j++) {
                    visit[j] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        input = new int[N];
        while(st2.hasMoreTokens()){
            input[count] = Integer.parseInt(st2.nextToken());
            count++;
        }
        Arrays.sort(input);

        visit = new boolean[N];
        buffer = new int[M];
        dfs(0);
        System.out.println(sb.toString());
    }
}
