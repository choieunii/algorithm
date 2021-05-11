package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1986 {
    static int N;
    static int M;
    static char[][] board;
    static boolean[][] checkBoard;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N + 1][M + 1];
        checkBoard = new boolean[N + 1][M + 1];

        st = new StringTokenizer(br.readLine());

        int queenCnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < queenCnt; i++) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 'Q';
            checkBoard[x][y] = true;
        }


        st = new StringTokenizer(br.readLine());

        int knightCnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < knightCnt; i++) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 'K';
            checkBoard[x][y] = true;
        }


        st = new StringTokenizer(br.readLine());

        int pawnCnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < pawnCnt; i++) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = 'P';
            checkBoard[x][y] = true;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (board[i][j] == 'Q') {
                    checkQueenLeft(i-1, j);
                    checkQueenRight(i+1, j);
                    checkQueenUp(i, j-1);
                    checkQueenDown(i, j+1);
                    checkQueenLU(i-1, j-1);
                    checkQueenLD(i-1, j+1);
                    checkQueenRU(i+1, j-1);
                    checkQueeRD(i+1, j+1);
                } else if (board[i][j] == 'K') {
                    checkKnight(i, j);
                }
            }
        }

        System.out.println(checkSafe());

    }

    static int checkSafe() {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (!checkBoard[i][j]) {
                    cnt++;
                }
                System.out.print(checkBoard[i][j]);
            }
            System.out.println();
        }
        return cnt;
    }

    static void checkQueenLeft(int x, int y) {
        if (x < 1 || checkBoard(x,y)) {
            return;
        } else {
            checkBoard[x][y] = true;
            checkQueenLeft(x - 1, y);
        }
    }

    static void checkQueenRight(int x, int y) {
        if (x > N || checkBoard(x,y)) {
            return;
        } else {
            checkBoard[x][y] = true;
            checkQueenRight(x +1, y);
        }
    }

    static void checkQueenUp(int x, int y) {
        if ( y < 1 || checkBoard(x,y)) {
            return;
        } else {
            checkBoard[x][y] = true;
            checkQueenUp(x, y - 1);
        }
    }

    static void checkQueenDown(int x, int y) {
        if ( y > M || checkBoard(x,y)) {
            return;
        } else {
            checkBoard[x][y] = true;
            checkQueenDown(x, y + 1);
        }
    }


    static void checkQueenLU(int x, int y) {
        if (x < 1 || y < 1 || checkBoard(x,y)) {
            return;
        } else {
            checkBoard[x][y] = true;
            checkQueenLU(x - 1, y - 1);
        }
    }

    static void checkQueenLD(int x, int y) {
        if (x < 1 || y > M || checkBoard(x,y)) {
            return;
        } else {
            checkBoard[x][y] = true;
            checkQueenLD(x - 1, y + 1);
        }
    }

    static void checkQueenRU(int x, int y) {
        if (x > N || y < 1 || checkBoard(x,y)) {
            return;
        } else {
            checkBoard[x][y] = true;
            checkQueenRU(x + 1, y - 1);
        }
    }

    static void checkQueeRD(int x, int y) {
        if (x > N || y > M || checkBoard(x,y)) {
            return;
        } else {
            checkBoard[x][y] = true;
            checkQueenLD(x + 1, y + 1);
        }
    }

    static void checkKnight(int x, int y) {
        int[] dx = {1, -1, 1, -1, 2, 2, -2, -2};
        int[] dy = {2, 2, -2, -2, 1, -1, 1, -1};
        for (int i = 0; i < 8; i++) {
            int tmpx = x + dx[i];
            int tmpy = y + dy[i];
            if (tmpx < 1 || tmpy < 1 || tmpx > N || tmpy > M || checkBoard(tmpx,tmpy)) {
                continue;
            }
            checkBoard[tmpx][tmpy] = true;
        }
    }
    static boolean checkBoard(int x, int y){
        if( board[x][y]=='Q' || board[x][y] == 'K' || board[x][y] == 'P') {
            return true;
        }
        return false;
    }
}
