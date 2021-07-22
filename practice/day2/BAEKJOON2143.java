package practice.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BAEKJOON2143 {
    static long T;
    static int N, M;
    static long[] inputA, inputB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Long.parseLong(br.readLine());

        N = Integer.parseInt(br.readLine());
        inputA = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inputA[i] = Long.parseLong(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        inputB = new long[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            inputB[i] = Long.parseLong(st.nextToken());
        }

        List<Long> subA = new ArrayList<>();
        List<Long> subB = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            long sum = inputA[i];
            subA.add(sum);
            for (int j = i + 1; j < N; j++) {
                sum += inputA[j];
                subA.add(sum);
            }
        }

        for (int i = 0; i < M; i++) {
            long sum = inputB[i];
            subB.add(sum);
            for (int j = i + 1; j < M; j++) {
                sum += inputB[j];
                subB.add(sum);
            }
        }
        //1. 부분 배열 만들기

        Collections.sort(subA);
        Collections.sort(subB, Comparator.reverseOrder());
        //2. 각각 오름차순, 내림차순으로 정렬

        long result = 0;

        result = 0;
        int ptA = 0;
        int ptB = 0;
        //3. 투포인터
        while (ptA < subA.size() && ptB < subB.size()) {
            long currentA = subA.get(ptA);
            long target = T - currentA;

            if (subB.get(ptB) > target) { // 타켓보다 B가 가지고 있는 값이 크다면?
                ptB++; //B 포인터를 옮긴다.
            } else if (subB.get(ptB) == target) {
                long countA = 0;
                long countB = 0;
                while (ptA < subA.size() && subA.get(ptA) == currentA) {
                    ptA++;
                    countA++;
                }
                while (ptB < subB.size() && subB.get(ptB) == target) {
                    ptB++;
                    countB++;
                }
                result += countA * countB;
            } else { // 작은 경우는
                ptA++;
            }
        }

        System.out.println(result);

    }
}
