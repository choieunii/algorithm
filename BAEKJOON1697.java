import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BAEKJOON1697 {
    static int N = 0;
    static int K = 0;
    static int visited[] = new int[100000+1];
    static int move[] = new int[3];

    public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N=Integer.parseInt(st.nextToken());
    K=Integer.parseInt(st.nextToken());
    if(N==K){
        System.out.println(0);
    }else {
        bfs();
    }
    }

    public static void bfs(){
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(N);
        visited[N] = 0;
        while(!q.isEmpty()) {
            int tmp = q.poll();
            move[0] = tmp +1;
            move[1] = tmp -1;
            move[2] = tmp * 2;
            for(int i=0;i<3;i++){
                if(move[i]>=0 && move[i]<=100000 && visited[move[i]] == 0){
                    visited[move[i]] = visited[tmp] +1;
                    q.offer(move[i]);
                }
            }
            if(tmp == K){
                System.out.println(visited[tmp]);
                break;
            }
        }
    }
}
