package sort;

import java.io.*;
import java.util.*;

/**
 *  TODO Day-14 : 회사에 있는 사람
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1 초	    256 MB	    71973	30196	22997	41.027%
 *
 *  문제
 *  상근이는 세계적인 소프트웨어 회사 기글에서 일한다. 이 회사의 가장 큰 특징은 자유로운 출퇴근 시간이다. 따라서, 직원들은 반드시 9시부터 6시까지 회사에 있지 않아도 된다.
 *  각 직원은 자기가 원할 때 출근할 수 있고, 아무때나 퇴근할 수 있다.
 *  상근이는 모든 사람의 출입카드 시스템의 로그를 가지고 있다. 이 로그는 어떤 사람이 회사에 들어왔는지, 나갔는지가 기록되어져 있다. 로그가 주어졌을 때, 현재 회사에 있는 모든 사람을 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 로그에 기록된 출입 기록의 수 n이 주어진다. (2 ≤ n ≤ 106) 다음 n개의 줄에는 출입 기록이 순서대로 주어지며, 각 사람의 이름이 주어지고 "enter"나 "leave"가 주어진다. "enter"인 경우는 출근, "leave"인 경우는 퇴근이다.
 *  회사에는 동명이인이 없으며, 대소문자가 다른 경우에는 다른 이름이다. 사람들의 이름은 알파벳 대소문자로 구성된 5글자 이하의 문자열이다.
 *
 *  출력
 *  현재 회사에 있는 사람의 이름을 사전 순의 역순으로 한 줄에 한 명씩 출력한다.
 *
 *  예제 1.   4               Askar
 *           Baha enter      Artem
 *           Askar enter
 *           Baha leave
 *           Artem enter
 *
 *  예제 2.   9
 *          Carol enter
 *          Alice enter
 *          Dave enter
 *          Carol leave
 *          Erin enter
 *          Erin leave
 *          Bob enter
 *          Carol enter
 *          Bob leave
 */
public class D14_7785 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int length = Integer.parseInt(br.readLine());
//            String[] sources = new String[length];
//            for (int i = 0; i < sources.length; i++) {
//                sources[i] = br.readLine();
//            }
            int length = Integer.parseInt("9");
            String[] sources = ("Carol enter\n" +
                    "Alice enter\n" +
                    "Dave enter\n" +
                    "Carol leave\n" +
                    "Erin enter\n" +
                    "Erin leave\n" +
                    "Bob enter\n" +
                    "Carol enter\n" +
                    "Bob leave").split("\n");

            String result = mySolution(sources);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     *  1. sources에 있는 모든 사람들에 대한 중복 제거를 한다. - by set (sourceSet)
     *  2. sources에 있는 사람들 중 leave 기록된 사람들을 set으로 만든다. (leaveSet)
     *  3. sourceSet에서 leave에 해당하지 않는 사람을 거른다(filter)
     *  4. 역순으로 정렬하여 출력한다.
     *      - 사전의 역순, 대소문자 구분
     *
     *  반례 -> 또 들어올 수 있어...
     *
     *  그럼 다시
     *  1. sources를 순회하면서 sourceMap을 만들어서 들어오면 ++ 나가면 --; Map<String, Integer>
     *  2. sourceMap 에서 숫자가 0보다 큰 사람 filter
     *  3. 사전 역순으로 정렬.
     */
    private static String mySolution(String[] sources) {
        Map<String, Integer> sourceMap = new HashMap();

        for (String source : sources) {
            String[] split = source.split(" ");
            String name = split[0];
            String flag = split[1];

            if (flag.equals("leave")) {
                sourceMap.put(name, sourceMap.get(name) - 1);
            } else {
                if (sourceMap.containsKey(source)) {
                    sourceMap.put(name, sourceMap.get(name) + 1);
                } else {
                    sourceMap.put(name, 1);
                }
            }
        }

        sourceMap.keySet().stream()
                .filter(v -> sourceMap.get(v) != 0)
                .sorted((v, o) -> o.compareTo(v))
                .forEach(System.out::println);
        return "";
    }

}