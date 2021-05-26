package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2576{
    static int[][] board;
    static int[] dx = {-1,-2,-2,-1,-2};
    static int[] dy = {-1,0,-1,1,1};
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int caseCnt = Integer.parseInt(br.readLine());
        answer = new int[caseCnt];

        for(int cnt=0;cnt<caseCnt;cnt++){
            int length = Integer.parseInt(br.readLine());
            board = new int[2][length];
            for(int k=0;k<2;k++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<length;j++){
                    board[k][j] = Integer.parseInt(st.nextToken());
                }
            }
            board[0][1] = board[1][0] + board[0][1];
            board[1][1] = board[1][1] + board[0][0];

            for(int m=2;m<length;m++){
                for(int n=0;n<2;n++){
                    int max = Integer.MIN_VALUE;
                    for(int idx = 0;idx<5;idx++){
                        int tmpx = m+dx[idx];
                        int tmpy = n+dy[idx];
                        if(tmpx<0 || tmpy<0 || tmpx>=length || tmpy>=2) continue;
                        max = Math.max(max,board[tmpy][tmpx]);
                    }
                    board[n][m] +=max;
                   // printArray(length);
                    answer[cnt] = Math.max(answer[cnt], board[n][m]);
                }
            }
        }
        for(int ans : answer){
            System.out.println(ans);
        }
    }
    static void printArray(int length){
        for(int i=0;i<2;i++){
            for(int j=0;j<length;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
