package practice.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BAEKJOON3425 {
    static int N;
    static int K;
    static char[][] words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(K <5) return;

        for(int i=0;i<N;i++){
            String input = br.readLine();
            words[i] = input.toCharArray();
        }

        HashSet<String> hashSet = new HashSet<>();

    }
}
