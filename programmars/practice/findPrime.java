package programmars.practice;

public class findPrime {
    public int solution(int n) {
        int[] arr = new int[n + 1];
        int cnt = 0;

        for (int i = 2; i <= n; i++) {
            arr[i] = i;
        } // 초기화

        for (int i = 2; i * i <= n; i++) {
            if (arr[i] == 0) continue; //0면 pass

            for (int j = i + i; j <= n; j += i) {
                arr[j] = 0; //i의 배수들을 0로
            }
        }

        for (int i = 2; i <= n; i++) {
            if (arr[i] != 0) {
                cnt++; //0가 아닐경우 cnt 증가
            }
        }
        return cnt;
    }
}
