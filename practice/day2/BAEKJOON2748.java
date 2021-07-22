package practice.day2;

import java.util.Scanner;

public class BAEKJOON2748 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long[] arr = new long[999];
        int N = s.nextInt();

        arr[1] = arr[2] =1;
        for(int i=3;i<=N;i++){
            arr[i] = arr[i-1] + arr[i-2];
        }

        System.out.println(arr[N]);
    }
}
