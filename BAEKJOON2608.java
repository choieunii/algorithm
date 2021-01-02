import java.util.*;
import java.util.Stack;
import java.lang.*;

public class BAEKJOON2608 {

	public static int changeAra(String R) {
		int[] roma = new int[999];
		int Ara = 0;

		roma['I'] = 1;
		roma['V'] = 5;
		roma['X'] = 10;
		roma['L'] = 50;
		roma['C'] = 100;
		roma['D'] = 500;
		roma['M'] = 1000;

		for (int i = 0; i < R.length(); i++) {

			if (i!=R.length()-1 && roma[R.charAt(i)] < roma[R.charAt(i + 1)]) {
				Ara += roma[R.charAt(i + 1)] - roma[R.charAt(i)];
				i++;
			} else {
				Ara += roma[R.charAt(i)];
			}
		}
		return Ara;
	}
	
	public static String changeRoma(int A) {
		
		StringBuilder sb = new StringBuilder();
		while(A>0) {
		if(A >= 1000) {
			A -= 1000;
			sb.append("M");
		}else if(A>=900) {
			A -= 900;
			sb.append("CM");
		}else if(A>=500) {
			A-=500;
			sb.append("D");
		}else if(A>=400) {
			A-=400;
			sb.append("CD");
		}else if(A>=100) {
			A-=100;
			sb.append("C");
		}else if(A>=90) {
			A-=90;
			sb.append("XC");
		}else if(A>=50) {
			A-=50;
			sb.append("L");
		}else if(A>=40) {
			A-=40;
			sb.append("XL");
		}else if(A>=10) {
			A-=10;
			sb.append("X");
		}else if(A>=9) {
			A-=9;
			sb.append("IX");
		}else if(A>=5) {
			A-=5;
			sb.append("V");
		}else if(A>=4) {
			A-=4;
			sb.append("IV");
		}
		else {
			A-=1;
			sb.append("I");
		}
		}
		return sb.toString();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		String first = s.next();
		String second = s.next();
		int sumAra = changeAra(first)+changeAra(second);
		System.out.println(sumAra);
		System.out.print(changeRoma(sumAra));
	}
}
