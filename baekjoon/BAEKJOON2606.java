package baekjoon;

import javax.print.attribute.IntegerSyntax;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON2606 {
    static int M;
    static int N;
    static boolean computer[][];
    static boolean visited[];
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        computer = new boolean[M + 1][M + 1];
        visited = new boolean[M + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            computer[first][second] = true;
            computer[second][first] = true;
        }// 연결된 컴퓨터

        bfs();
        System.out.print(count);
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int input = queue.poll(); // 큐에서 나온거
          //  System.out.println(input);
            for (int i = 0; i <= M; i++) {
                if (computer[input][i] == true && visited[i] == false) {
                    //컴퓨터가 연결되어있고, 방문하지 않았으면
                    visited[i] = true;
                    queue.offer(i);
                    count++;
                }
            }
        }
    }
}
