import java.util.*;
import java.lang.*;

public class BAEKJOON1110 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int input, newnum=0;
		String newstr = null;
		int ten, one, sum = 0;
		int cnt=0;
		input=s.nextInt();
		if(input<10)
		{
			newstr= "0"+ Integer.toString(input);
		}
		newnum = input;
		while(true) {
			ten= newnum/10;
			one= newnum%10;
			sum= ten+one;
			ten=sum%10;
			newstr= Integer.toString(one) + Integer.toString(ten);
			newnum =Integer.parseInt(newstr);
			cnt++;
			if(input==newnum)
			{
				break;
			}
		}
		System.out.println(cnt);
	}
}
