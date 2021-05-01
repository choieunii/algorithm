package programmars.hash;

import java.util.*;
import java.lang.*;
class Clothes {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> clothesMap = new HashMap<>();
        for(int i=0;i<clothes.length;i++){
            clothesMap.put(clothes[i][1],clothesMap.getOrDefault(clothes[i][1],0)+1);
        }

        for(Integer value : clothesMap.values()){
            answer *= (value+1);
        }

        return answer-1;
    }
}