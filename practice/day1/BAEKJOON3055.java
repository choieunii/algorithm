package practice.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;
    char type;

    Point(int y, int x, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public String toString() {
        return "[y=" + y + ", x=" + x + ", type=" + type + "]";
    }
}

public class BAEKJOON3055 {

    static int[] mx = {-1, 1, 0, 0};
    static int[] my = {0, 0, -1, 1};
    static int R = 0;
    static int C = 0;
    static char[][] map;
    static int[][] dp;
    static boolean foundAnswer;
    static Queue<Point> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        dp = new int[R][C];
        queue = new LinkedList<>();

        Point point = null;
        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'S') {
                    point = new Point(i, j, 'S'); // 스타팅 포인트는 임시로!!
                } else if (map[i][j] == '*') {
                    queue.add(new Point(i, j, '*'));
                }
            }
        }
        queue.add(point); //임시로 넣었던 스타팅 포인트를 queue에 마지막에 넣음.

        while (!queue.isEmpty()) {
            //1. 큐에서 꺼내옴
            Point p = queue.poll();
            //2. 목적지인가? if(p==D)
            if (p.type == 'D') {
                System.out.println(dp[p.y][p.x]);
                foundAnswer = true; // 찾았따!
                break;
            }
            //3. 갈 수 있는 곳을 순회 for(좌, 우, 위, 아래)
            for (int i = 0; i < 4; i++) {
                int ty = p.y + my[i];
                int tx = p.x + mx[i];
                //4. 갈 수 있는가? if(맵을 벗어나지 않고 )
                if (0 <= ty && ty < R && 0 <= tx && tx < C) {
                    if (p.type == '*') {
                        if (map[ty][tx] == '.' || map[ty][tx] == 'S') {
                            //5. 체크인
                            map[ty][tx] = '*';
                            //6. 큐를 넣음 queue.add(next)
                            queue.add(new Point(ty, tx, '*'));
                        }
                    } else {
                        //[.]이거나 [D]이거나
                        if (map[ty][tx] == '.' || map[ty][tx] == 'D') {
                            if (dp[ty][tx] == 0) { // 0이면 방문하지 않았다!
                                //5. 체크인
                                dp[ty][tx] = dp[p.y][p.x] + 1;
                                //6. 큐에 넣음 queue.add(next);
                                queue.add(new Point(ty, tx, map[ty][tx]));
                            }
                        }
                    }
                }

            }
        }

        if (!foundAnswer) {
            System.out.println("KAKTUS");
        }
    }

    static void printMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
