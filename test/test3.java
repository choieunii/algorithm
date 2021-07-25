package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class test3 {
    static Car myCar;
    static int[][] board;
    static Stack<Car> stack = new Stack<>();
    static Queue<Car> checkList = new LinkedList<>();
    static List<Car> tmpList = new ArrayList<>();
    static Car[] carList;
    static int [] answer;
    static int moveCnt = 0;

    static class Car {
        int car;
        Point[] point;
        String dir;

        Car(int car, Point[] point, String dir) {
            this.car = car;
            this.point = point;
            this.dir = dir;
        }

        String checkCarDirection() {
            if (this.dir.equals("W") || this.dir.equals("E")) {
                return "row";
            } else {
                return "col";
            }
        }

        void carMove() {
            if (dir.equals("W")) {
                sort("W");
                Point cur = this.point[0];
                int west = westMove(cur.y-1);
                if(west>0 && !stack.contains(carList[west])){
                    tmpList.add(carList[west]);
                    sort("E");
                    cur = this.point[0];
                    int east = eastMove(cur.y+1);
                    System.out.println(east + " east");
                    if(east>0 && !stack.contains(carList[east])){
                        tmpList.add(carList[east]);
                    }
                }
            } else if (dir.equals("E")) {
                sort("E");
                Point cur = this.point[0];
                int east = eastMove(cur.y+1);
                if(east>0 && !stack.contains(carList[east])){
                    tmpList.add(carList[east]);
                    sort("W");
                    cur = this.point[0];
                    int west = westMove(cur.y-1);
                    System.out.println(west + " west");
                    if(west>0 && !stack.contains(carList[west])){
                        tmpList.add(carList[west]);
                    }
                }
            } else if (dir.equals("N")) {
                sort("N");
                Point cur = this.point[0];
                int north = northMove(cur.x-1);
                System.out.println(north + " north");
                if(north>0 && !stack.contains(carList[north])){
                    tmpList.add(carList[north]);
                    sort("S");
                    cur = this.point[0];
                    int south = southMove(cur.x+1);
                    if(south>0 && !stack.contains(carList[south])){
                        tmpList.add(carList[south]);
                    }
                }
            } else {
                sort("S");
                Point cur = this.point[0];
                int south = southMove(cur.x+1);
                if(south>0 && !stack.contains(carList[south])){
                    tmpList.add(carList[south]);
                    sort("N");
                    cur = this.point[0];
                    int north = northMove(cur.x-1);
                    if(north>0 && !stack.contains(carList[north])){
                        tmpList.add(carList[north]);
                    }
                }
            }
        }

        int checkMycarCanMove(int loc) {
            Point cur = myCar.point[0];
            if (board[cur.x][loc] != 0) {
                return board[cur.x][loc];
            } else if (cur.x == 3 && loc == 5 && board[cur.x][loc] == 0) {
                return 0;
            }
            loc += 1;
            return checkMycarCanMove(loc);
        }

        void boardInit() {
            board[point[0].x][point[0].y] = 0;
            board[point[1].x][point[1].y] = 0;
        }

        void sort(String type){
            if(type.equals("N")){
                Arrays.sort(point, (a, b) -> a.x - b.x);
            }else if(type.equals("S")){
                Arrays.sort(point, (a, b) -> b.x - a.x);
            }else if(type.equals("W")){
                Arrays.sort(point, (a, b) -> a.y - b.y);
            }else{
                Arrays.sort(point, (a, b) -> b.y - a.y);
            }
        }
        int northMove(int loc) {
            if (loc == 1) return 0;
            if (board[loc][point[0].y]!=0) return board[loc][point[0].y];
            boardInit();
            point[0].x -= 1;
            point[1].x -= 1;
            return northMove(point[0].x);
        }

        int southMove(int loc) {
            if (loc == 5) return 0;
            if (board[loc][point[0].y]!=0) return board[loc][point[0].y];
            boardInit();
            point[0].x += 1;
            point[1].x += 1;
            return southMove(point[0].x);
        }

        int westMove(int loc) {
            if (loc == 1) return 0;
            if (board[point[0].x][loc]!=0) return board[point[0].x][loc];
            boardInit();
            point[0].y -= 1;
            point[1].y -= 1;
            return westMove(point[0].y);
        }

        int eastMove(int loc) {
            if (loc == 5) return 0;
            if (board[point[0].x][loc]!=0) return board[point[0].x][loc];
            boardInit();
            point[0].y += 1;
            point[1].y += 1;
            return eastMove(point[0].y);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        answer = new int[T];

        for (int i = 0; i < T; i++) {
            int car = Integer.parseInt(br.readLine());
            board = new int[6][6];
            carList = new Car[car +1];

            for (int cnt = 1; cnt <= car; cnt++) {
                setCar(br.readLine(), cnt);
            }
            printBoard();

            myCar = carList[1];
            int tmp = myCar.checkMycarCanMove(myCar.point[0].y+1);
            if(tmp != 0){
                stack.push(myCar);
                checkList.offer(carList[tmp]);
                while(!stack.isEmpty()){
                    System.out.println();
                    for(Car a : stack){
                        System.out.print(a.car + " ");
                    }
                    System.out.println(" stackList");
                    System.out.println();
                    for(Car a: tmpList){
                        System.out.print(a.car + " ");
                    }

                    System.out.println(" tmpList");
                    System.out.println();
                    for(Car a: checkList){
                        System.out.print(a.car + " ");
                    }
                    System.out.println();
                    System.out.println(" checkList");
                    Car haveToMove = checkList.poll();
                    haveToMove.carMove();
                    if(!tmpList.isEmpty()){
                        stack.push(haveToMove);
                        checkList.addAll(tmpList);
                    }
                    tmpList = new ArrayList<>();
                }

            }else{
                answer[i] = -1;
            }
        }
    }

    static void setCar(String line, int cnt) {
        StringTokenizer st = new StringTokenizer(line);
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        String dir = st.nextToken();
        Point[] point = new Point[2];

        point[0] = new Point(x, y);
        board[x][y] = cnt;

        if (dir.equals("E")) {
            y = y - 1;
        } else if (dir.equals("W")) {
            y = y + 1;
        } else if (dir.equals("S")) {
            x = x - 1;
        } else {
            x = x + 1;
        }

        point[1] = new Point(x, y);
        board[x][y] = cnt;

        Car car = new Car(cnt, point, dir);
        carList[cnt] = car;
    }

    static void printBoard() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void printAnswer() {
        int count = 1;
        for (int ans : answer) {
            System.out.println("#" + count + " " + ans);
            count++;
        }
    }
}
