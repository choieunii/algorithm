package practice.day2;

import java.util.Scanner;

public class BAEKJOON1806 {
    static int n, s;
    static int arr[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        s = sc.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int sum = 0;
        int left = 0, right = 0;
        int min = Integer.MAX_VALUE;
        while (true) {
            if (sum >= s) {
                sum -= arr[left++];
                min = Math.min(min, right - left + 1);
            } else if (right == n) {
                break;
            } else {
                sum += arr[right++];
            }

        }
        if (min != Integer.MAX_VALUE) System.out.println(min);
        else System.out.println(0);
    }
}