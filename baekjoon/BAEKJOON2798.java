package baekjoon;

import java.util.*;
import java.lang.*;
public class BAEKJOON2798 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int N;
		int M;
		N=s.nextInt();
		M=s.nextInt();
		int a[] = new int[N];
		
		for(int i=0;i<N;i++) {
			a[i]=s.nextInt();
		}
		
		int max=0;
		Arrays.sort(a);
		for(int i = 0; i < N-2; i++) {
            for(int j = i+1; j < N-1; j++) {
                for(int k = j+1; k < N; k++) {
                    int sum = a[i] + a[j] + a[k];
                    if(sum==M){
                    	max=sum;
                    	break;
                    }
                    if(sum>=max && sum < M) {
                        max=sum;
                    }
                }
            }
        }
		System.out.print(max);
	}
}
