package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class BAEKJOON13335 {

	static int n, w, L, trucks[], bridge[];

	private static int moveBridge() {

		int time = 0, weight = 0, cnt = 0;
		while (cnt < n) {
			int reach = bridge[0];
			for (int i = 1; i < w; i++) {
				bridge[i - 1] = bridge[i];
			}
			bridge[w - 1] = 0;

			if (reach != 0) {
				weight -= reach;
			}
			if (cnt < n && weight + trucks[cnt] <= L) {
				bridge[w - 1] = trucks[cnt];
				weight += trucks[cnt];
				cnt++;
			}

			time++;
		}

		return time + w;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		bridge = new int[w];
		trucks = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			trucks[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(moveBridge());
	}

}
