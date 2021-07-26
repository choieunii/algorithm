package practice.day5.BAEKJOON1837;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BAEKJOON1837 {
    static int MIN = Integer.MAX_VALUE;
    static int MAX = 1000001;
    static int K;
    static char[] P;
    static boolean[] checked;
    static List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        P = st.nextToken().toCharArray();
        K = Integer.parseInt(st.nextToken());

        checked = new boolean[MAX + 1];

        for (int i = 2; i <= MAX; i++) {
            if (!checked[i]) {
                primes.add(i);
                for (int j = i * 2; j <= MAX; j += i) {
                    checked[j] = true; //checked 는 소수 아님, prime 에 소수
                }
            }
        }

        boolean isBad = false;
        for (int prime : primes) {
            if (checkIsBad(prime) && prime < K) {
                MIN = Math.min(prime, MIN);
                isBad = true;
            }
        }
        if (isBad) {
            System.out.println("BAD " + MIN);
        } else {
            System.out.println("GOOD");
        }
    }

    static boolean checkIsBad(int x) {
        int ret = 0;
        for (int i = 0; i < P.length; i++) {
            ret = (ret * 10 + (P[i] - '0')) % x;
        }
        if (ret == 0) {
            return true;
        } else {
            return false;
        }
    }
}
