package programmars.makeprime;

import java.util.*;

class Solution3 {
    static boolean[] isPrime = new boolean[3000];
    static int[] num = new int[3];
    static int[] arr;
    static boolean[] visited;
    static int result = 0;

    public int solution(int[] nums) {
        arr = nums;
        visited = new boolean[nums.length];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i < 3000; i++) if (isPrime[i]) for (int j = 2; i * j < 3000; j++) isPrime[i * j] = false;
        recur(0, 0);
        return result;
    }

    static void recur(int depth, int idx) {
        if (depth == 3) {
            if (isPrime[Arrays.stream(num).sum()]) result++;
            return;
        }
        for (int i = idx; i < arr.length; i++) {
            if (visited[i]) continue;
            num[depth] = arr[i];
            visited[i] = true;
            recur(depth + 1, i + 1);
            visited[i] = false;
        }
    }
}

