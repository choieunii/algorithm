import java.util.*;

public class BAEKJOON2231 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		int input = s.nextInt();
		int var = input;
		int min = input;
		while (var >0) {
			if (cal(var)+var == input) {
				if (min >= var) {
					min = var;
				}
			}
			var--;
		}
		if (min == input) {
				min = 0;
		}
		System.out.println(min);
	}

	public static int cal(int x) {
		int sum = 0;
		while (x > 0) {
			sum += x % 10;
			x /= 10;
		}
		return sum;
	}
}
