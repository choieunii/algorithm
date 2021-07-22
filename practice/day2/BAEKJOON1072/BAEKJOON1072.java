package practice.day2.BAEKJOON1072;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BAEKJOON1072 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        long X = sc.nextLong();
        long Y = sc.nextLong();

        long z = 100 * Y / X;

        if (z >= 99) {
            System.out.println(-1);
        } else {
            long start = 0;
            long end = X;
            while (start < end) { // start 와 end 가 만나면 종료한다.
                long mid = (start + end) / 2;
                long newRate = (100 * (Y + mid) / (X + mid));
                if (newRate == z) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            System.out.println(end);
        }

    }
}
