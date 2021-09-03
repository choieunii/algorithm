package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 1932번 정수 삼각형
public class BAEKJOON1932 {
    static int n, ans;
    static int [][] triangle;
    static int [][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        triangle = new int[n][n];
        dp = new int[n][n];

        for(int i=0;i<n;i++){
            String[] inputArr = br.readLine().split(" ");
            for(int j=0;j< inputArr.length;j++){
                triangle[i][j] = Integer.parseInt(inputArr[j]);
            }
        }

        dp[0][0] = triangle[0][0];
        for(int i=0;i<n-1;i++){
            for(int j=0;j<=i;j++){
                dp[i+1][j] = Math.max(dp[i+1][j],dp[i][j]);
                dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j]);
            }

            for(int j=0;j<=i+1;j++){
                dp[i+1][j] += triangle[i+1][j];
            }
        }

       //printArr(dp);
        for (int j=0; j< n; j++){
            ans = Math.max(ans, dp[n-1][j]);
        }
        System.out.println(ans);
    }
    static void printArr(int [][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
