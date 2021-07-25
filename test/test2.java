package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test2 {
    static int T;
    static int[] numA;
    static int[] numB;
    static char[] filter;
    static int filterCnt = 0;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        answer = new int[T];

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String A = st.nextToken();
            String B = st.nextToken();
            int L = Integer.parseInt(st.nextToken());

            String tmp = br.readLine();
            filter = tmp.toCharArray();
            numA = initFormat(A);
            numB = initFormat(B);

            for (int cnt = 0; cnt < 6; cnt++) {
                if (numA[cnt] == 0 && numB[cnt] == 0) continue;
                int start = calFilter(cnt);
                while (isApply(cnt, start) && checkFilterRange(cnt, start)) {
                    applyFilter(cnt, start);
                }
            }
            if (isEqual()) {
                answer[i] = filterCnt;
            } else {
                answer[i] = -1;
            }
            filterCnt = 0;
        }
        printAnswer();
    }

    static boolean isApply(int cnt, int start) {
        if (!checkFilter(cnt, start)) {
            reverseFilter();
            if (!checkFilter(cnt, start)) {
                return false;
            }
        }
        return true;
    }

    static boolean checkFilterRange(int cnt, int start) {
        for (int i = 0; i < filter.length; i++) {
            if (filter[i] == '+' && numA[cnt - start + i] >= 9) {
                return false;
            } else if (filter[i] == '-' && numA[cnt - start + i] <= 0) {
                return false;
            }
        }
        return true;
    }

    static int calFilter(int cnt) {
        if (cnt >= 6 - filter.length) {
            return Math.abs(6 - cnt - filter.length);
        } else {
            return 0;
        }
    }

    static boolean checkFilter(int cnt, int start) {
        if (numA[cnt] < numB[cnt] && filter[start] == '+') return true;
        if (numA[cnt] > numB[cnt] && filter[start] == '-') return true;
        return false;
    }

    static void printAnswer() {
        int count = 1;
        for (int ans : answer) {
            System.out.println("#" + count + " " + ans);
            count++;
        }
    }

    static boolean isEqual() {
        for (int i = 0; i < 6; i++) {
            if (numA[i] != numB[i]) return false;
        }
        return true;
    }

    static void reverseFilter() {
        char[] reverseArr = new char[filter.length];
        for (int i = filter.length - 1, j = 0; i >= 0; i--, j++) {
            reverseArr[j] = filter[i];
        }
        filter = reverseArr;
    }

    static void applyFilter(int cnt, int start) {
        for (int i = 0; i < filter.length; i++) {
            if (filter[i] == '+') {
                numA[cnt - start + i] += 1;
            } else if (filter[i] == '-') {
                numA[cnt - start + i] -= 1;
            }
        }
        filterCnt++;
    }

    static void printFormat(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    static int[] initFormat(String number) {
        int[] format = new int[6];
        char[] tmp = number.toCharArray();

        for (int i = 0; i < tmp.length; i++) {
            format[6 - tmp.length + i] = tmp[i] - '0';
        }
        return format;
    }

}
