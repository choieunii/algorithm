package practice.day7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class building {
    int time; // 각 건물 1개의 건설시간
    int indegree; // 건물의 선행조건 개수
    int ans; // 선행건물 포함 최소 건설시간
    ArrayList<Integer> adjList; // 이 건물이 지어져야 지을 수 있는 건물 리스트

    public building() {
        this.time = 0;
        this.indegree = 0;
        this.ans = 0;
        this.adjList = new ArrayList<Integer>();
    }
}

public class BAEKJOON1516 {

    static int N; // N : 건물의 수
    static building[] buildingList; // 건물정보 리스트
    static int input; // 입력받은 정수 (임시 변수)
    static Queue<Integer> queue; // 위상정렬용 큐
    static StringBuilder sb; // 정답 출력용 StringBuilder

    public static void main(String[] args) throws IOException {
     //   System.setIn(new FileInputStream("path"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1. 입력
        // 1) N개의 건물정보 생성
        N = Integer.parseInt(br.readLine());
        buildingList = new building[N + 1];
        for (int i=1; i<=N; i++) {
            buildingList[i] = new building();
        }

        // 2) N개의 건물정보 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            // 2-1) 입력된 시간 정보 받기
            buildingList[i].time = Integer.parseInt(st.nextToken());

            // 2-2) -1이 입력될때까지 입력받기
            input = Integer.parseInt(st.nextToken());
            while(input!=-1) {
                // 2-2-1) 선행건물 개수 증가시키기
                buildingList[i].indegree++;
                // 2-2-2) 선행건물의 인접리스트에 현재 건물 id를 추가
                buildingList[input].adjList.add(i);
                // 2-2-3) -1 입력 재확인
                input = Integer.parseInt(st.nextToken());
            }

        }

        // 2. 위상정렬 재료 만들기
        //    - 선행건물이 없는 경우 (indegree = 0)인 경우 큐에 넣기
        queue = new ArrayDeque<Integer>();
        for (int i = 1; i <= N; i++) {
            if (buildingList[i].indegree == 0) {
                queue.add(i);
            }
        }

        // 3. 위상정렬
        while(!queue.isEmpty()) {
            // 1) 선행 건물이 더이상 없는 녀석을 뽑아서 최종 건설시간에 자기 자신을 더해줌
            int id = queue.poll();
            buildingList[id].ans += buildingList[id].time;

            // 2) 자신을 지어야 지을 수 있는 건물들에게 선행건물의 건설이 끝났음을 알림
            for (int adjId : buildingList[id].adjList) {
                // 2-1) 선행건물의 개수 빼주기
                buildingList[adjId].indegree--;
                // 2-2) 타겟건물은 아직 건물을 지을 수 없는 상태
                //      → 현재 상태에서 타겟건물의 ans는 선행건물의 건설시간 중 최댓값 - ans를 최댓값으로 갱신시켜야함
                buildingList[adjId].ans = Math.max(buildingList[adjId].ans, buildingList[id].ans);
                // 2-3) 타겟건물의 선행건물 개수가 0이면 큐에 넣기
                if (buildingList[adjId].indegree == 0) {
                    queue.add(adjId);
                }
            }
        }

        // 4. 정답 출력
        sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(buildingList[i].ans + "\n");
        }
        System.out.println(sb);
    }
}
