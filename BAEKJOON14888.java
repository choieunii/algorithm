import java.io.*;
import java.util.*;

public class BAEKJOON14888 {
    static int count;
    static int visit[];
    static int number[];
    static int operator[] = new int[4];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void dfs(int idx, int num) {
        if (idx == count) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                operator[i]--;
                if (i == 0) {
                    dfs(idx + 1, num + number[idx]);
                } else if (i == 1) {
                    dfs(idx + 1, num - number[idx]);
                } else if (i == 2) {
                    dfs(idx + 1, num * number[idx]);
                } else if (i == 3) {
                    dfs(idx + 1, num / number[idx]);
                }
                operator[i]++;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        count = Integer.parseInt(br.readLine());
        number = new int[count];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st2.nextToken());
        }

        dfs(1, number[0]);

        System.out.println(max);
        System.out.println(min);
    }
}
