package ref.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 미로만들기 {
    static int N = 0;
    static char[] note;
    static char[][] maze;
    static boolean visited[][];
    static int mazeLen = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String input = br.readLine();
        note = new char[N];
        int countF = 1;
        for (int i = 0; i < N; i++) {
            note[i] = input.charAt(i);
            if (note[i] == 'F') countF++;
        }
        while (mazeLen * mazeLen < countF) {
            mazeLen++;
        }
        visited = new boolean[mazeLen][mazeLen];
        for (int i = 0; i < mazeLen; i++) {
            for (int j = 0; j < mazeLen; j++) {
                if (makeMaze(i, j)) {
                    printMap();
                    return;
                } else {
                    visited = new boolean[mazeLen][mazeLen];
                }
            }
        }
    }

    static void printMap() {
        for (int i = 0; i < mazeLen; i++) {
            for (int j = 0; j < mazeLen; j++) {
                if (visited[i][j]) {
                    System.out.print('.');
                } else {
                    System.out.print('#');
                }
            }
            System.out.println();
        }
    }

    static boolean makeMaze(int x, int y) {
        int dir = 2; //남쪽을 보고 있을 때
        visited[x][y] = true;
        for (int cnt = 0; cnt < N; cnt++) {
            if (note[cnt] == 'F') {
                if (dir % 4 == 0) {
                    if (y + 1 > mazeLen - 1) return false;
                    visited[x][y + 1] = true;
                } else if (dir % 4 == -1 || dir % 4 == 3) {
                    if (x - 1 < 0 || x - 1 > mazeLen - 1) return false;
                    visited[x - 1][y] = true;
                } else if (dir % 4 == 1 || dir % 4 == -3) {
                    if (x + 1 > mazeLen - 1) return false;
                    visited[x + 1][y] = true;
                } else {
                    if (y - 1 < 0 || y - 1 > mazeLen - 1) return false;
                    visited[x][y - 1] = true;
                }
            } else if (note[cnt] == 'L') {
                dir--;
            } else if (note[cnt] == 'R') {
                dir++;
            }
        }
        return true;
    }
}