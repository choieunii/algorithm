package test;

import java.util.*;
import java.lang.*;
import java.io.*;

public class test1 {
    static int[] arr;
    static List<Integer> before = new ArrayList<>();
    static PriorityQueue<Integer> after = new PriorityQueue<>();
    static int T;
    static int total;
    static int min = Integer.MAX_VALUE;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(br.readLine());
            arr = new int[N];

            if (N < 2) {
                answer.add(Integer.parseInt(br.readLine()));
            } else {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    before.add(Integer.parseInt(st.nextToken()));
                } // 입력

                int count = 1;
                while (!before.isEmpty()) {
                    if (before.size() < 2) {
                        total += before.get(0);
                        break;
                    }
                    if(count % 2 == 1){
                        Collections.sort(before);
                    }else {
                        Collections.sort(before, Collections.reverseOrder());
                    }

                    int first = before.get(0);
                    int second = before.get(1);

                    total += Math.max(first,second);

                    after.offer(first);
                    after.offer(second);

                    before.remove(0);
                    before.remove(0);

                    if (before.isEmpty()) break;

                    int tmp = after.poll();
                    total += tmp;
                    before.add(tmp);

                    count++;
                }
                answer.add(total);
            }
        }
        printAnswer();
    }
    static void printAnswer(){
        int count = 1;
        for (int ans : answer) {
            System.out.println("#" + count + " " + ans);
            count++;
        }
    }
}