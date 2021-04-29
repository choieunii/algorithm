package algospot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Festival {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());

        double[] answerArr = new double[count];
        for (int i = 0; i < count; i++) {
            double answer = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] array = new int[N];
            for (int j = 0; j < N; j++) {
                array[j] = Integer.parseInt(st.nextToken());
            }

            for (int cnt = L; cnt <= N; cnt++) {
                for (int first = 0; first <= N - cnt; first++) {
                    double tmp = 0;
                    for (int second = 0; second < cnt; second++) {
                        tmp += array[first + second];
                    }
                    tmp = tmp / cnt;
                    answer = Math.min(tmp, answer);
                }
            }
            answerArr[i] = answer;
        }
        for (double print : answerArr) {
            System.out.printf("%.11f", print);
            System.out.println();
        }
    }
}
