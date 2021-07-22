package practice.day1;

import java.util.*;

public class BAEKJOON1713 {
    static int N, K;
    static int[] inputs;
    static Person[] people;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        inputs = new int[K];
        people = new Person[101];

        List<Person> list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int num = sc.nextInt();
            if (people[num] == null) {
                people[num] = new Person(num, 0, 0, false);
            }
            if (people[num].isIn) {
                people[num].count++;
            } else {
                if (list.size() == N) {
                    Collections.sort(list);
                    Person p = list.remove(0);
                    p.isIn = false;
                }
                people[num].count = 1;
                people[num].isIn = true;
                people[num].timeStamp = i;
                list.add(people[num]);
            }
        }
        Collections.sort(list, (o1, o2) -> o1.num - o2.num);

        for(Person i: list){
            System.out.print(i.num + " ");
        }
    }
}

class Person implements Comparable<Person> {
    int num;
    int count;
    int timeStamp;
    boolean isIn;

    public Person(int num, int count, int timeStamp, boolean isIn) {
        super();
        this.num = num;
        this.count = count;
        this.timeStamp = timeStamp;
        this.isIn = isIn;
    }

    @Override
    public String toString() {
        return num+" ";
    }

    @Override
    public int compareTo(Person person) {
        int result = count - person.count;
        if (result == 0) {
            return timeStamp - person.timeStamp;
        } else {
            return result;
        }
    }
}
