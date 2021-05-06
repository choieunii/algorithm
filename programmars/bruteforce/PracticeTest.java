package programmars.bruteforce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PracticeTest {
    public Integer[] solution(int[] answers) {
        Integer[] answer = {};
        int[] typeOne = {1, 2, 3, 4, 5};
        int[] typeTwo = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] typeThree = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int one = 0, two = 0, three = 0;
        int[] cnt = {0, 0, 0};

        for (int i : answers) {
            if (typeOne[one % typeOne.length] == i) {
                cnt[0]++;
            }
            if (typeTwo[two % typeTwo.length] == i) {
                cnt[1]++;
            }
            if (typeThree[three % typeThree.length] == i) {
                cnt[2]++;
            }
            one++;
            two++;
            three++;
        }

        int max = Math.max(cnt[2], Math.max(cnt[0], cnt[1]));

        List<Integer> answerList = new ArrayList<>();

        if (max == cnt[0]) answerList.add(1);
        if (max == cnt[1]) answerList.add(2);
        if (max == cnt[2]) answerList.add(3);

        Collections.sort(answerList);

        answer = answerList.toArray(new Integer[answerList.size()]);

        return answer;
    }
}
