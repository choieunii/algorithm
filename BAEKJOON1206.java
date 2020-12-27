import java.util.*;
import java.lang.*;
public class BAEKJOON1206 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int col = s.nextInt();
		int row = s.nextInt();
		String input[] = new String[col];	
		int cnt1 =0 ,cnt2=0;
		int answer = 0;
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		String white[] = {"WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW"};
		String black[] = {"BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB"};
		
 		for(int i=0;i<col;i++) {
			input[i]= s.next();
		}//지민이 입력

 		for(int i=0;i<=row-8;i++) {
 			for(int j=0;j<=col-8;j++) {
 				for(int k=j;k<j+8;k++) {
 					String tmp = input[k].substring(i,i+8);
 					
 					if(!tmp.equals(white[k-j])) {
 						for(int p=i;p<i+8;p++) {
 							if(tmp.charAt(p-i) != white[k-j].charAt(p-i))
 								cnt1++;
 						}
 					}
 					
 					if(!tmp.equals(black[k-j])) {
 						for(int p=i;p<i+8;p++) {
 							if(tmp.charAt(p-i) != black[k-j].charAt(p-i))
 								cnt2++;
 						}
 					}
 				}
 				min1 = min1 > cnt1 ? cnt1 : min1;
 				min2 = min2 > cnt2 ? cnt2 : min2;
 				cnt1=0;
 				cnt2=0;
 			}
 		}
 		
 		if(min1>min2) {
 			answer = min2;
 		}else { answer = min1;}
 		
 		System.out.println(answer);
		
	}
}
