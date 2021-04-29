package baekjoon;

import java.util.*;
import java.lang.*;
import java.io.*;

public class BAEKJOON14713 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<String> queue;

		int input = Integer.parseInt(br.readLine());
		Object bird[] = new Object[101];
		String confilm[] = new String[10001];
		int confilmCnt = 0;
		boolean result = false;
		boolean flag = false;

		int p = 0;
		for (int i = 0; i < input; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			queue = new LinkedList<>();
			while (st.hasMoreTokens()) {
				queue.add(st.nextToken());
			}
			bird[i] = queue;
		} // 앵무새가 가져온 문장

		StringTokenizer st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			confilm[p] = st.nextToken();
			p++;
		} // 마지막에 해석할 문장

		while (confilm[confilmCnt] != null) {
			result = false;
			for (int i = 0; i < input; i++) {
				if (((Queue<String>) bird[i]).peek() != null
						&& confilm[confilmCnt].equals(((Queue<String>) bird[i]).peek())) {
					((Queue<String>) bird[i]).remove();
					result = true;
					break;
				}
			}
			confilmCnt++;
		}
		if (result == false) {
			System.out.println("Impossible");
			return;
		}

		for (int i = 0; i < input; i++) {
			if (((Queue<String>) bird[i]).peek() != null) {
				System.out.println("Impossible");
				return;
			}
		}

		System.out.println("Possible");

	}
}
