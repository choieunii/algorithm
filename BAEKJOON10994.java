import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	public static String[][] initStar(int N,String[][] starArray) {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				starArray[i][j] = " ";
			}
		}
		return starArray;
	}
	public static String[][] makeStar(int N, String[][] starArray) {
		int cnt =0;
		while(cnt<=N/2) {
		for(int i=1+cnt;i<=1+cnt;i++) {
			for(int j=1+cnt;j<=N-cnt;j++) {
			starArray[i][j] = "*";
			}
		}//제일 윚줄
		for(int i=N-cnt;i<=N-cnt;i++) {
			for(int j=1+cnt;j<=N-cnt;j++) {
				starArray[i][j] = "*";
				}
		}//제일 아랫줄
		for(int i=1+cnt;i<=N-cnt;i++) {
			for(int j=1+cnt;j<=1+cnt;j++) {
				starArray[i][j] = "*";
				}
		}//왼쪽줄
		for(int i=1+cnt;i<=N-cnt;i++) {
			for(int j=N-cnt;j<=N-cnt;j++) {
				starArray[i][j] = "*";
				}
		}//오른족줄
		cnt+=2;
		}
		starArray[N/2+1][N/2+1] = "*";
		return starArray;
	}
	public static void printStar(int N, String[][] starArray) {
		StringBuilder sb = new StringBuilder();
		for(int i =1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				sb.append(starArray[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(br.readLine());
		int N = 4*(input-1)+1;
		
	    String starArray[][] = new String[N+1][N+1];
		starArray = initStar(N,starArray);
		starArray = makeStar(N,starArray);
		printStar(N,starArray);
	}
}
//
