package practice.day5.BAEKJOON3955;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BAEKJOON3955 {
    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("path"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());


            EGResult result = extendedGcd(A, B);
            // D = gcd(A,B)
            // Ax + By = C 일때 C % D ==0 이어야 해를 가질 수 있음 : 배주 항등식
            if (result.r != 1) {
                System.out.println("IMPOSSIBLE");
            } else {
                // x0 = s * C/D
                // y0 = t * C/D
                long x0 = result.s;
                long y0 = result.t;

                // x = x0 + B/D * k
                // y = y0 - A/D * k

                // x < 0
                // x0 + B * k < 0
                // k < - x0 / B
                // 0 < y <= 1e9
                // 0 < y0 - A * k <= 1e9
                // - y0 < - A * k <= 1e9 - y0

                //(y0 - 1e9) / A <= k < y0 / A
                //                         k < - x0 / B

                long kFromY = (long) (Math.ceil((double) y0 / (double) A)) - 1;
                long kFromX = (long) Math.ceil((double) -x0 / (double) B) - 1;

                long k = Math.min(kFromX, kFromY);
                long y = y0 - A * k;
                if (y <= 1e9) {
                    System.out.println(y);
                } else {
                    System.out.println("IMPOSSIBLE");
                }
            }
        }
    }

    public static EGResult extendedGcd(long a, long b) {
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        long temp;
        while (r1 != 0) {
            long q = r0 / r1;
            temp = r0 - q * r1;
            r0 = r1;
            r1 = temp;

            temp = s0 - q * s1;
            s0 = s1;
            s1 = temp;

            temp = t0 - q * t1;
            t0 = t1;
            t1 = temp;
        }
        return new EGResult(s0, t0, r0);
    }
}

class EGResult {
    long s;
    long t;
    long r;

    public EGResult(long s, long t, long r) {
        super();
        this.s = s;
        this.t = t;
        this.r = r;
    }

}