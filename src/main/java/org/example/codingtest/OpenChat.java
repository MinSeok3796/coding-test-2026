package org.example.codingtest;
import java.util.*;

/* 프로그래머스 Lv.2 오픈채팅방 문제
https://school.programmers.co.kr/learn/courses/30/lessons/42888?language=java
 */
public class OpenChat {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            List<String> input = new ArrayList<>();

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if (line.isEmpty()) break;
                input.add(line);
            }

            String[] record = input.toArray(new String[0]);

            Solution sol = new Solution();
            String[] result = sol.solution(record);

            for (String s : result){
                System.out.println(s);
            }

        }

}

class Solution {
    Queue<String[]> work = new LinkedList<>();
    Map<String, String> name = new HashMap<>();
    public String[] solution(String[] record) { // record의 구성은 '기능-uid-닉네임'의 순서

        // 기능 분리
        decompose(record);

        String[] answer = new String[work.size()];
        int count = 0;
        // 재조립
        while( !work.isEmpty()){
            String[] action = work.poll(); //행위를 하나씩 추출

            switch (action[0]){
                case "Enter":
                    answer[count] = name.get(action[1]) +"님이 들어왔습니다.";
                    break;

                case "Leave":
                    answer[count] = name.get(action[1]) +"님이 나갔습니다.";
                    break;
            }
            count++;
        }




        return answer;
    }

    public void decompose(String[] record){
        for(int i = 0; i < record.length; i++){ //하나씩 반복
            String[] arr = record[i].split(" ");

            switch (arr[0]) {

                case "Enter":
                    work.offer(new String[]{arr[0], arr[1]}); // 행위 - uid를 연결
                    name.put(arr[1], arr[2]); // uid - 이름 연결
                    break;

                case "Leave":
                    work.offer(new String[]{arr[0], arr[1]});
                    break;

                case "Change":
                    name.put(arr[1], arr[2]); // 이름 업데이트
                    break;
            }
        }
    }

}
