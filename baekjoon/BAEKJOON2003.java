package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2003번 수들의 합2
public class BAEKJOON2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        // 입력값 받기
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(twoPointer(arr, m));
    }
    static int twoPointer(int [] arr, int m){
        int cnt = 0;
        int start = 0, end = 0;
        int len = arr.length;
        int sum = 0;

        while(true){
            if(sum >=m) {
                sum -= arr[start++];
            }else if(end >= len){
                break;
            }else {
                sum += arr[end++];
            }
            if(sum == m) cnt++;
        }
        return cnt;
    }
    static void print(int []arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
