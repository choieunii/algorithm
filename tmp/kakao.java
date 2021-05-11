//package tmp;
//
//import java.util.*;
//import java.lang.*;
//
//class Solution {
//    static class Row{
//        int num;
//        int index;
//        Row(int num, int index){
//            System.out.println(num+" "+ index);
//            this.num = num;
//            this.index = index;
//        }
//        Row plusIndex(){
//            index++;
//            return this;
//        }
//        Row minusIndex(){
//            index--;
//            return this;
//        }
//    }
//    public String solution(int n, int k, String[] cmd) {
//        String answer = "";
//        List<Row> list = new ArrayList<>();
//        int current = k;
//
//        for(int i=0;i<n;i++){
//            list.add(new Row(i,i));
//        }
//        Row tmpRemove = new Row(0,0);
//        int tmpCurrent=0;
//        for(String s : cmd){
//            String[] split = s.split(" ");
//            String key = split[0];
//            int value = 0;
//
//            if(split.length>1) value = Integer.parseInt(split[1]);
//
//            if(key.equals("D")){
//                current+=value;
//            }else if(key.equals("U")){
//                current-=value;
//            }else if(key.equals("C")){
//                if(current<0 || current>list.size()) continue;
//                tmpRemove = list.get(current);
//                tmpCurrent = current;
//                for(int i=current;i<list.size();i++){
//                    list.set(i,list.get(i).minusIndex());
//                }
//                list.remove(current);
//            }else if(key.equals("Z") && tmpRemove!=null){
//                list.add(tmpCurrent, tmpRemove);
//                for(int i=tmpCurrent;i<list.size();i++){
//                    list.set(i,list.get(i).plusIndex());
//                }
//                tmpRemove = null;
//            }
//        }
//        return answer;
//    }
//
//
//    static void print(List list){
//    }
//}