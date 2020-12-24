import java.util.*;
import java.lang.*;
public class BAEKJOON1051 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int row = s.nextInt();
		int col = s.nextInt();
		int square[][] = new int[row][col];
		int answer = 0;
		int length = 1;
		
		for(int i=0;i<row;i++)
		{
			String tmp = s.next();
			for(int j=0;j<col;j++) {
				square[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		while(length<=row && length<=col) {
			for(int i=0;i<row-length;i++) {
				for(int j=0;j<col-length;j++) {
					if((square[i][j] == square[i][j+length])&&( square[i][j+length] == square[i+length][j])&&(square[i+length][j]== square[i+length][j+length]))
					{
						answer = length;
					}
				}
			}
			length++;
		}
		
		System.out.print((answer+1)*(answer+1));
	}

}
