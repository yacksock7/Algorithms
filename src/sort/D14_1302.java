package sort;

import java.io.*;
import java.util.*;

/**
 *  TODO Day-14 : 베스트셀러
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  2 초	    128 MB	    30389	16611	13811	54.860%
 *
 *  문제
 *  김형택은 탑문고의 직원이다. 김형택은 계산대에서 계산을 하는 직원이다. 김형택은 그날 근무가 끝난 후에, 오늘 판매한 책의 제목을 보면서 가장 많이 팔린 책의 제목을 칠판에 써놓는 일도 같이 하고 있다.
 *  오늘 하루 동안 팔린 책의 제목이 입력으로 들어왔을 때, 가장 많이 팔린 책의 제목을 출력하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 오늘 하루 동안 팔린 책의 개수 N이 주어진다. 이 값은 1,000보다 작거나 같은 자연수이다. 둘째부터 N개의 줄에 책의 제목이 입력으로 들어온다. 책의 제목의 길이는 50보다 작거나 같고, 알파벳 소문자로만 이루어져 있다.
 *
 *  출력
 *  첫째 줄에 가장 많이 팔린 책의 제목을 출력한다. 만약 가장 많이 팔린 책이 여러 개일 경우에는 사전 순으로 가장 앞서는 제목을 출력한다.
 *
 *  예제 1.   5           top
 *           top
 *           top
 *           top
 *           top
 *           kimtop
 *
 *  예제 2.   9           table
 *           table
 *           chair
 *           table
 *           table
 *           lamp
 *           door
 *           lamp
 *           table
 *           chair
 *
 *  예제 3.   6           a
 *           a
 *           a
 *           a
 *           b
 *           b
 *           b
 *
 *  예제 4.   8           chocolate
 *           icecream
 *           peanuts
 *           peanuts
 *           chocolate
 *           candy
 *           chocolate
 *           icecream
 *           apple
 *
 *  예제 4.   1           soul
 *           soul
 *
 */
public class D14_1302 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

//            int length = Integer.parseInt(br.readLine());
//            String[] sources = new String[length];
//            for (int i = 0; i < sources.length; i++) {
//                sources[i] = br.readLine();
//            }
            int length = Integer.parseInt("6");
            String[] sources = ("a\n" +
                    "a\n" +
                    "a\n" +
                    "b\n" +
                    "b\n" +
                    "b").split("\n");

            mySolution(sources);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  가장 많은 이름 -> 여러 개일 경우, 사전 순으로 출력
     *
     *  1. sources를 순회하면서 품목별 판매량 HashMap으로 관리
     *  2. HashMap의 values로 Max값 검색;
     *  3. HashMap의 KeySet으로 Max값인 품목 filter;
     *  4. keys 사전순 정렬;
     */
    private static void mySolution(String[] sources) {

        Map<String, Integer> resultMap = new HashMap<>();
        for (String source : sources) {
            if (resultMap.containsKey(source)) {
                resultMap.put(source, resultMap.get(source) + 1);
            } else {
                resultMap.put(source, 1);
            }
        }

        int max = resultMap.values().stream()
                .max(Comparator.comparing(x -> x))
                .get();

        String result = resultMap.keySet().stream()
                .filter(v -> resultMap.get(v) == max)
                .sorted()
                .findFirst()
                .get();

        System.out.println(result);
    }
}