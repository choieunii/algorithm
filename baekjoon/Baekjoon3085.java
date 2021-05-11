package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon3085 {
    static int N, result = Integer.MIN_VALUE;
    static char bomboni[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bomboni = new char[N][N];
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<N; j++)
                bomboni[i][j] = str.charAt(j);
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N-1; j++) {
                swap(i,j,i,j+1);
                result = Math.max(result, solve());
                swap(i,j,i,j+1);
            }
        }
        for(int j=0; j<N; j++) {
            for(int i=0; i<N-1; i++) {
                swap(i,j,i+1,j);
                result = Math.max(result, solve());
                swap(i,j,i+1,j);
            }
        }
        System.out.println(result);
    }
    private static int solve() {
        int max = 1;
        for(int i=0; i<N; i++) {
            int cnt = 1;
            for(int j=1; j<N; j++) {
                if(bomboni[i][j-1] == bomboni[i][j]) {
                    cnt++;
                }else {
                    max = Math.max(max, cnt);
                    cnt = 1;
                }
            }
            max = Math.max(max, cnt);
        }
        for(int j=0; j<N; j++) {
            int cnt = 1;
            for(int i=1; i<N; i++) {
                if(bomboni[i-1][j] == bomboni[i][j]) {
                    cnt++;
                }else {
                    max = Math.max(max, cnt);
                    cnt = 1;
                }
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
    private static void swap(int i, int j, int k, int l) {
        char tmp = bomboni[i][j];
        bomboni[i][j] = bomboni[k][l];
        bomboni[k][l] = tmp;
    }
}

