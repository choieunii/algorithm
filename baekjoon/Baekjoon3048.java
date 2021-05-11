package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Ant {
    char name;
    int num;

    public Ant(char s, int n) {
        this.name = s;
        this.num = n;
    }
}

public class Baekjoon3048 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Ant> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N1 = Integer.parseInt(st.nextToken());
        int N2 = Integer.parseInt(st.nextToken());

        String groupOne = br.readLine();

        StringBuilder sb = new StringBuilder(groupOne);
        String reverse = sb.reverse().toString();

        for (int i = 0; i < reverse.length(); i++)
            list.add(new Ant(reverse.charAt(i), 1));

        String groupTwo = br.readLine();

        for (int i = 0; i < groupTwo.length(); i++)
            list.add(new Ant(groupTwo.charAt(i), 2));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            for (int i = 0; i < list.size() - 1; i++) {
                Ant cur = list.get(i);
                Ant next = list.get(i + 1);
                if (cur.num != 2 && cur.num != next.num) {
                    list.set(i, next);
                    list.set(i + 1, cur);
                    i++;
                }
            }
        }

        list.forEach(ant -> System.out.print(ant.name));
    }
}
