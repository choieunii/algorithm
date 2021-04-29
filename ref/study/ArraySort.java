package ref.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArraySort {
    public static void main(String[] args) {
        Map<String, Integer> hmMap = new HashMap<>();
        hmMap.put("A", 5);
        hmMap.put("B", 1);
        hmMap.put("C", 2);
        hmMap.put("D", 4);
        hmMap.put("E", 3);
        List<String> listKeySet = new ArrayList<>(hmMap.keySet()); // 오름차순 정렬 방법
        System.out.println("-------------- 오름차순 정렬 --------------");
        Collections.sort(listKeySet, (value1, value2) -> (hmMap.get(value1).compareTo(hmMap.get(value2))));
        for (String key : listKeySet) {
            System.out.println("key : " + key + " , " + "value : " + hmMap.get(key));
        } // 내림차순 정렬 방법
        System.out.println("-------------- 내림차순 정렬 --------------");
        Collections.sort(listKeySet, (value1, value2) -> (hmMap.get(value2).compareTo(hmMap.get(value1))));
        for (String key : listKeySet) {
            System.out.println("key : " + key + " , " + "value : " + hmMap.get(key));
        }
    }
}
