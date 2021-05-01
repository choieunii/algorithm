package programmars.hash;

import java.lang.*;
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        HashMap<String, String> phoneMap = new HashMap<>();

        for(String phone : phone_book){
            phoneMap.put(phone, phone);
        }

        for(String phone : phone_book){
            for(int idx=0; idx< phone.length(); idx++){
                if(phoneMap.containsKey(phone.substring(0,idx))){
                    answer = false;
                }
            }
        }
        return answer;
    }
}
