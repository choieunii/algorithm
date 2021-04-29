package baekjoon;

import java.io.*;
import java.util.*;

public class BAEKJOON14889 {
    static int N;
    static int[][] array;
    static boolean[] visit;
    static int Min = Integer.MAX_VALUE;

    static void dfs(int idx, int count){
        if(count == N/2){
            /*최솟값찾기*/
            cal();
            return;
        } //탈출조건
        for(int i=idx;i<N;i++){
            if(!visit[i]){
                visit[i]=true;
                dfs(i+1,count+1);
                visit[i] = false;
            }
        }
    }
    static void cal(){
        int team_start = 0;
        int team_link = 0;
        for(int i=0;i<N-1;i++){
            for(int j=i+1;j<N;j++){
                if(visit[i]==true && visit[j]==true){
                    team_start += array[i][j];
                    team_start += array[j][i];
                }
                else if(visit[i]==false && visit[j]==false){
                    team_link += array[i][j];
                    team_link += array[j][i];
                }
            }
        }
        int val = Math.abs(team_start - team_link);

        if(val == 0){
            System.out.println(val);
            System.exit(0);
        }

        Min = Math.min(val,Min);
    }

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        array = new int[N][N];
        visit = new boolean[N];

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,0);
        System.out.println(Min);
    }
}
