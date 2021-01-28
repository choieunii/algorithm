import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON7576 {
    static int max = Integer.MIN_VALUE;
    static int array[][];
    static int M;
    static int N;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static class tomato{
        int row;
        int col;
        public tomato(int x,int y){
            row = x;
            col = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = new int[M][N];

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();

    }
    static void bfs(){
        Queue<tomato> q = new LinkedList<tomato>();

        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(array[i][j] == 1){
                    q.offer(new tomato(i,j));
                }
            }
        }// 처음에 토마토가 익었으면 큐에다가 넣는다.

        while(!q.isEmpty()){
            tomato to = q.poll();

            for(int i=0;i<4;i++){
                int row = to.row + dx[i];
                int col = to.col + dy[i];

                if(row <M && col<N && 0<=row && 0<=col){
                    if(array[row][col]==0){
                        array[row][col] = array[row-dx[i]][col-dy[i]] +1;
                        q.offer(new tomato(row,col));
                    }//토마토가 0이면 익혀버리고 큐에 넣는다.
                }
            }// 아아 이렇게 하면 상하좌우를 전부 볼 수 있다
        }
        if(!checkTomato()){
            System.out.println("-1");
        }
        else{
            System.out.println(max-1);
        }
    }
    static boolean checkTomato(){
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(array[i][j]==0){
                    return false;
                }
                max = Math.max(max, array[i][j]);
            }
        }
        return true;
    }
}
