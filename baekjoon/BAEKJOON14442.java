package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.LinkedList;

class BAEKJOON14442{
    static int arr[][] = new int[1000][1000];
    static int n,m,k;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[][][] map;

    static int answer = Integer.MAX_VALUE;

    static class Node{
        int x,y,k;

        public Node(int x,int y,int k){
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m][k+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int m=0;m<k+1;m++){
                    map[i][j][m] = -1;
                }
            }
        }

        for(int i=0;i<n;i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                arr[i][j] = temp[j] - '0';
            }
        }

        bfs();
        System.out.println(answer!=Integer.MAX_VALUE?answer:-1);

    }

    public static void bfs(){
        Queue<Node> q = new LinkedList<>();
        map[0][0][0] = 1;
        q.add(new Node(0,0,0));
        while(!q.isEmpty()){
            Node p = q.poll();

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx <0 || nx>=n || ny <0 || ny>=m ) continue;
                if(arr[nx][ny]==0 && map[nx][ny][p.k]==-1){
                    map[nx][ny][p.k] = map[p.x][p.y][p.k] + 1;
                    q.add(new Node(nx,ny,p.k));
                }
                if(arr[nx][ny] == 1 && p.k+1<=k && map[nx][ny][p.k+1]==-1){
                    map[nx][ny][p.k+1] = map[p.x][p.y][p.k]+1;
                    q.add(new Node(nx,ny,p.k+1));
                }
            }
        }

        for(int i=0;i<=k;i++){
            if(map[n-1][m-1][i] != -1){
                answer = Math.min(answer,map[n-1][m-1][i]);
            }
        }
    }
}
