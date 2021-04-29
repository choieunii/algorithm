package baekjoon;

import javax.print.attribute.IntegerSyntax;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BAEKJOON16234 {
    static int N;
    static int L;
    static int R;
    static int A[][];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean visited[][];
    static int count = 0;
    static boolean flag;

    public static class Dot {
        int x;
        int y;

        public Dot(int curx, int cury) {
            this.x = curx;
            this.y = cury;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) { //true 일동안
            flag = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(new Dot(i, j));
                    }
                }
            }
           // printMap();
            if (!flag) {
                System.out.println(count);
                return;
            }
            count++;
            visited = new boolean[N][N];
        }
    }

    public static void printMap() {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void bfs(Dot dot) {
        Queue<Dot> queue = new LinkedList<Dot>();
        ArrayList<Dot> List = new ArrayList<Dot>();
        queue.offer(dot);
        visited[dot.x][dot.y] = true;
        List.add(dot);

        while (!queue.isEmpty()) { //큐가 비지 않을동안
            Dot tmp = queue.poll();// poll하고

            for (int i = 0; i < 4; i++) {
                int row = tmp.x + dx[i];
                int col = tmp.y + dy[i]; //상하좌우를 볼건데

                if (row < 0 || col < 0 || row >= N || col >= N) {
                    continue; //범위넘으면 패스
                }

                if (visited[row][col]) {
                    continue; //방문 했어도 패스
                }

                int sub = Math.abs(A[tmp.x][tmp.y] - A[row][col]);
                //살펴본 나라 인구차이 절댓값

                if (sub >= L && sub <= R) { // 인구차이가 L보다 크고 R보다 작으면
                    queue.offer(new Dot(row, col)); //큐에 넣고
                    List.add(new Dot(row, col)); // 리스트 넣고
                    visited[row][col] = true; // 방문도 했음
                    flag = true;
                }
            }
        }
        int size = List.size();
        if (size > 1) { // 인구 이동이 일어날시
            int sum = 0;
            for (int k = 0; k < size; k++) {
                Dot updateDot = List.get(k);
                sum += A[updateDot.x][updateDot.y];
            }// 함계
            for (int k = 0; k < size; k++) {
                Dot updateDot = List.get(k);
                A[updateDot.x][updateDot.y] = sum / size;
            }// 인구 업데이트
            List.clear();
        }
    }

}


