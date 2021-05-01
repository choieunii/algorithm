package programmars.hash;

import java.lang.*;
import java.util.*;

class BestAlbum {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        ArrayList<Integer> arrAnswer = new ArrayList<>();
        HashMap<String, Integer> genreMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            genreMap.put(genres[i], genreMap.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<String> keySetList = new ArrayList<>(genreMap.keySet());
        keySetList.sort((o1, o2) -> (genreMap.get(o2).compareTo(genreMap.get(o1))));

        for (String key : keySetList) {
            HashMap<Integer, Integer> hs = new HashMap<>();
            for (int i = 0; i < genres.length; i++) {
                if (key.equals(genres[i])) {
                    hs.put(i, plays[i]);
                }
            }

            List<Integer> keyLists = new ArrayList<>(hs.keySet());
            keyLists.sort((s1, s2) -> hs.get(s2).compareTo(hs.get(s1)));

            int j = 0;
            for (Integer c : keyLists) {
                if (j > 1) {
                    break;
                }
                arrAnswer.add(c);
                j++;
            }
        }
        answer = new int[arrAnswer.size()];
        for (int i = 0; i < arrAnswer.size(); i++) {
            answer[i] = arrAnswer.get(i);
        }

        return answer;
    }
}
