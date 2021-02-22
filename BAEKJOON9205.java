package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BAEKJOON9205 {
    static int t = 0;
    static int n = 0;
    static ArrayList<Point> list = new ArrayList<>();
    static Queue<Point> queue = new LinkedList<>();
    static boolean visit[];
    static Point last;
    static boolean[] happy = new boolean[51];
    static int cnt = 0;

    public static class Point {
        int x;
        int y;

        Point(int curx, int cury) {
            this.x = curx;
            this.y = cury;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            visit = new boolean[n + 3];
            for (int j = 0; j < n + 2; j++) {
                String[] str = br.readLine().split(" ");
                int curx = Integer.parseInt(str[0]);
                int cury = Integer.parseInt(str[1]);
                list.add(new Point(curx, cury));
            }

            Point start = list.get(0);
            Point end = list.get(n + 1);

            if (isHappy(start, end)) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
            list.clear();
            queue.clear();
        }
    }

    public static boolean isHappy(Point start, Point end) {
        queue.offer(start);

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (cur == end) {
                return true;
            }

            for (int i = 1; i < n + 2; i++) {
                Point second = list.get(i);
                if (visit[i]) {
                    continue;
                }
                if (manhattan(cur, second) <= 1000) {
                    visit[i] = true;
                    queue.offer(second);
                }
            }
        }
        return false;
    }

    public static int manhattan(Point first, Point second) {
        int x = Math.abs(first.x - second.x);
        int y = Math.abs(first.y - second.y);
        return x+y; //문제좀 제대로 읽자
    }

}


