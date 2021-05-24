package programmars.makeprime;

import java.util.Arrays;
import java.lang.Math;

class Solution2 {
    private static int answer = 0;

    public int solution(int[] nums) {
        int[] comArr = new int[3];
        combination(comArr, nums.length, 3, 0, 0, nums);

        return answer;
    }

    private static void combination(int[] comArr, int n, int r,
                                    int index, int target, int[] nums){
        if (r==0){
            if(checkPrime(Arrays.stream(comArr).sum())){
                answer++;
            }
            return;
        }
        if(target==n) return;

        comArr[index] = nums[target];
        combination(comArr, n, r-1, index+1, target+1, nums);
        combination(comArr, n, r, index, target+1, nums);
    }

    private static boolean checkPrime(int check) {
        int count=0;
        double checkSqrt = Math.sqrt(check);
        double checkZero = checkSqrt - (int)checkSqrt;
        if(checkZero!=0.0) {
            for(int i=1; i<(int)checkSqrt+1; i++) {
                if(check%i==0) {
                    count++;
                }
            }
            count++;
        }
        if(count==2) {
            return true;
        } else {
            return false;
        }
    }
}
