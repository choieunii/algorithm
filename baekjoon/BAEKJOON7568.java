package baekjoon;

import java.util.*;
import java.lang.*;

public class BAEKJOON7568 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		int height[] = new int[num];
		int weight[] = new int[num];
		int count = 1;
		for (int i = 0; i < num; i++) {
			height[i] = s.nextInt();
			weight[i] = s.nextInt();
		}
		for (int i = 0; i < num; i++) {
			for (int j = 0; j< num; j++) {
				if (height[j] > height[i] && weight[j] > weight[i]) {
					count++;
				}
			}
			System.out.print(count + " ");
			count = 1;
		}

	}
}
