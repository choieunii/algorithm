package ref.programmars;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution2 {
    public class Music implements Comparable<Music>{
        private int played;
        private int id;
        private String genre;
        public Music(String genre, int played, int id){
            this.genre = genre;
            this.played = played;
            this.id = id;
        }

        @Override
        public int compareTo(Music o) {
            if(this.played == o.played) return this.id - o.id; // 같으면 아이디로 정렬
            return o.played-this.played; //아니면 플레이로 정렬
        }
        public String getGenre(){return genre;}
    }
    public int[] solution(String[] genres, int[] plays){
        return IntStream.range(0,genres.length)
                .mapToObj(i-> new Music(genres[i], plays[i],i))
                .collect(Collectors.groupingBy(Music::getGenre))
                .entrySet().stream()
                .sorted((a,b) -> sum(b.getValue())-sum(a.getValue()))
                .flatMap(x->x.getValue().stream().sorted().limit(2))
                .mapToInt(x->x.id).toArray();
    }
    private int sum(List<Music> value){
        int answer = 0;
        for(Music music : value){
            answer+=music.played;
        }
        return answer;
    }
}