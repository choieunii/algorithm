package baekjoon;

import java.util.*;
import java.lang.*;

public class BAEKJOON1913 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int input = s.nextInt();
		int select = s.nextInt();
		int snail[][] = new int[999][999];
		int MAX = input * input;
		int row = 0, col = 0;
		int snailCnt = input-1;
		int inputRow = 0, inputCol = 0;

		while (true) {
			for (int i = 0; i < snailCnt; i++) {
				snail[row++][col] = MAX;
				MAX--;
			}//아래
			for (int i = 0; i < snailCnt; i++) {
				snail[row][col++] = MAX;
				MAX--;
			}//오른쪽
			for (int i = 0; i < snailCnt; i++) {
				snail[row--][col] = MAX;
				MAX--;
			}//위
			for (int i = 0; i < snailCnt; i++) {
				snail[row][col--] = MAX;
				MAX--;
			}//왼쪽
			
			col++;
			row++;
			if (snailCnt == 2) {
				snail[row][col] = 1;
				break;
			}//최종 1일경우
			
			snailCnt -= 2;
		}
		for (int i = 0; i < input; i++) {
			for (int j = 0; j < input; j++) {
				if(snail[i][j]==select) {
					inputRow = i+1;
					inputCol = j+1;
				}
				sb.append(snail[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
		System.out.print(inputRow+ " "+inputCol);

	}
}
