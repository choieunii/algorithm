package practice.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEKJOON2003 {
    public static void main(String args[]) throws IOException {
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

    static int twoPointer(int[] arr, int m) {
        int count = 0;
        int startPoint = 0, endPoint = 0;
        int len = arr.length;
        int sum = 0;

        while(true) {
            // 현재 startIndex의 값을 빼고 한 칸 앞으로 이동.
            if(sum >= m) {
                sum -= arr[startPoint++];
            }
            else if(endPoint >= len) {
                break;
            }
            else { // 현재 endIndex를 한 칸 앞으로 이동 후 그인덱스의 원소 값을 더한다.
                sum += arr[endPoint++];
            }
            if(sum == m) {
                count++;
            }
        }
        return count;
    }
}