package baekjoon;

import java.util.*;
import java.lang.*;

public class BAEKJOON2563 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int paperCnt = s.nextInt();
		int paper[][] = new int[100][100];
		int area = 0;
 		while(paperCnt>0) {
			int left = s.nextInt();
			int bottom = s.nextInt();
			for(int i=left;i<left+10;i++) {
				for(int j=bottom;j<bottom+10;j++) {
					paper[i][j] = 1;
					}
			}
				paperCnt--;
		}
 		
 		for(int i=0;i<100;i++) {
 			for(int j=0;j<100;j++) {
 				if(paper[i][j]==1) {
 					area++;
 				}
 			}
 		}
 		System.out.print(area);
	}
}
