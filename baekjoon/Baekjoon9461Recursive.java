package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon9461Recursive {
    static int []dp;
    static int []value;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        for(int i=1;i<=count;i++){
            int N = Integer.parseInt(br.readLine());
            System.out.println(dp(N));
        }
    }
    static int dp(int count){
        if(count<0) {
            return 0;
        }else if(count == 1 || count == 2 || count == 3){
            return 1;
        }else if(count == 4 || count == 5){
            return 2;
        }
        return dp(count-1) + dp(count-5);
    }
}
