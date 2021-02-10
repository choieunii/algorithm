import java.util.*;

public class BAEKJOON1436 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s= new Scanner(System.in);
		int n = s.nextInt();
		int count=666;
		int num = 0;
		String strcnt=""; 
		String answer = "";
		while(n!=num) {
			strcnt=Integer.toString(count);
			if(strcnt.contains("666"))
			{
				answer=strcnt;
				num++;
			}
			count++;
		}
		System.out.println(answer);
	}
}
