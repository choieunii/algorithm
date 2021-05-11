//package tmp;
//
//import java.lang.*;
//import java.util.*;
//
//class Solution {
//    public Integer[] solution(String[][] places) {
//        Integer[] answer = {};
//        List<Integer> list = new ArrayList<>();
//
//        for(int place=0;place<places.length;place++){
//            boolean checkFlag = false;
//
//            char[][] room = new char[5][5];
//            for(int i=0;i<5;i++){
//                for(int j=0;j<5;j++){
//                    room[i][j] = places[place][i].charAt(j);
//                }
//            }
//
//            for(int i=0;i<5;i++){
//                for(int j=0;j<5;j++){
//                    if(room[i][j] == 'P'){
//                        if(checkRoom(room, i, j)){
//                            checkFlag = true;
//                            break;
//                        }
//                    }
//                }
//            }
//            if(checkFlag){
//                list.add(0);
//            }else{
//                list.add(1);
//            }
//        }
//        answer = list.toArray(new Integer[list.size()]);
//
//        return answer;
//    }
//    static boolean checkRoom(char[][] room, int x, int y){
//
//        int[] dx = {1,-1,0,0,1,1,-1,-1};
//        int[] dy = {0,0,1,-1,1,-1,1,-1};
//
//        for(int i=0;i<8;i++){
//            int tmpx = x + dx[i];
//            int tmpy = y + dy[i];
//
//            if(tmpx<0 || tmpx>=5 || tmpy<0 || tmpy>=5) continue;
//
//            if(room[tmpx][tmpy]=='P'){
//
//                tmpx -= 1;
//                tmpy -= 1;
//
//                if(!isRange(tmpx,tmpy)) continue;
//
//                if(room[tmpx+1][tmpy] == 'X' && room[tmpx][tmpy+1] == 'X'){
//                    continue;
//                } else{
//                    return true;
//                }
//
//            }else if(room[tmpx][tmpy]=='O'){
//                if(i == 0) tmpx +=1;
//                else if(i==1) tmpx-=1;
//                else if(i==2) tmpy+=1;
//                else if(i==3) tmpy-=1;
//
//                if(!isRange(tmpx,tmpy)) continue;
//
//                if(room[tmpx][tmpy] == 'P'){
//                    return true;
//                }else{
//                    continue;
//                }
//            }else if(room[tmpx][tmpy]=='X'){
//                continue;
//            }
//
//        }
//        return false;
//    }
//
//    static boolean isRange(int x, int y){
//        if(x<0 || x>=5 || y<0 || y>=5){
//            return false;
//        }else{
//            return true;
//        }
//    }
//
//    static void print(char[][] arr){
//        for(int i=0;i<arr.length;i++){
//            for(int j=0;j<arr[i].length;j++){
//                System.out.print(arr[i][j]);
//            }
//            System.out.println();
//        }
//    }
//}