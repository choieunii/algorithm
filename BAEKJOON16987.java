import java.io.*;
import java.util.*;

public class BAEKJOON16987 {
    static int count;
    static int visit[];
    static int egg[][];
    static int max = Integer.MIN_VALUE;

    public static void dfs(int idx, int eggCnt) {
        if (idx == count) {
            max = Math.max(max,eggCnt);
            return;
        }
        if(egg[idx][0]<=0){dfs(idx+1,eggCnt);}
        for (int i = 0; i < count; i++) {
            if(i==idx||egg[idx][0]<=0||egg[i][0]<=0){
                continue;
            }
            egg[i][0] -= egg[idx][1];
            egg[idx][0] -= egg[i][1];
            int tmp = 0;
            if(egg[i][0]<=0)tmp++;
            if(egg[idx][0]<=0)tmp++;
            dfs(idx+1,eggCnt+tmp);
            egg[i][0] += egg[idx][1];
            egg[idx][0] += egg[i][1];
        }
        max = Math.max(max,eggCnt);

    }

    public static void main(String[] args) throws IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        count = Integer.parseInt(br.readLine());
        egg = new int[count][2];
        for(int i=0;i<count;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<2;j++){
                egg[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);
        System.out.println(max);
    }
}
