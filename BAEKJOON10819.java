import java.io.*;
import java.util.*;

public class BAEKJOON10819 {
    static int count;
    static boolean visit[];
    static int array[];
    static int tarray[];
    static int max = Integer.MIN_VALUE;

    public static void dfs(int idx) {
        if (idx == count) {
            int sum = 0;
            for (int i = 0; i < count-1; i++) {
                sum += Math.abs(tarray[i] - tarray[i + 1]);
            }
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < count; i++) {
            if (visit[i]) {
                continue;
            }
            visit[i] = true;
            tarray[idx] = array[i];
            dfs(idx + 1);
            visit[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        count = Integer.parseInt(br.readLine());
        array = new int[count];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int i = 0;
        while (st.hasMoreTokens()) {
            array[i] = Integer.parseInt(st.nextToken());
            i++;
        }
        Arrays.sort(array);
        visit = new boolean[count];
        tarray = new int[count];
        dfs(0);
        System.out.println(max);
    }
}
