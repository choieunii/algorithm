package ref.programmars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        ArrayList<Integer> arrAnswer = new ArrayList<>();
        HashMap<String, Integer> hmGenres = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            hmGenres.put(genres[i], hmGenres.getOrDefault(genres[i], 0) + plays[i]);
            // 계속해서 더해져 value 값이 들어간다.
        }// HashMap 에 넣는다.

        List<String> keySetList = new ArrayList<>(hmGenres.keySet());// key 값만 받아와서 리스트에 넣는다.
        keySetList.sort((o1, o2) -> (hmGenres.get(o2).compareTo(hmGenres.get(o1))));//hashmap 값으로
        //내림차순 정렬한다.

        for (String key : keySetList) { // 아아ㅏ 이중포문??
            //리스트를 돌면서
            HashMap<Integer, Integer> hs = new HashMap<>();
            for (int i = 0; i < genres.length; i++) {
                if (key.equals(genres[i])) {
                    hs.put(i, plays[i]);
                    //장르랑 키가 같다면 선언한 해시맵에 값을 넣는다.
                    //aaaa,bbbb,ccc
                }
            }

            List<Integer> keyLists = new ArrayList<>(hs.keySet());
            //아까전에
            keyLists.sort((s1,s2)->hs.get(s2).compareTo(hs.get(s1)));
            //hash맵 value (play 값으로) 내림 차순 정렬한다.

            int j=0;
            for(Integer c : keyLists){
                //keyList를 돌면서
                if(j>1){break;} // 2가 넘어갈 경우 break
                arrAnswer.add(c);//answer 에 넣는다
                j++;
            }
        }
        answer=new int[arrAnswer.size()];
        for(int i=0;i<arrAnswer.size();i++){
            answer[i] = arrAnswer.get(i); // 배열로 만들어서 return
        }
        return answer;
    }

}
