package sort;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  TODO Day-14 : 단어 정렬
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  2 초	    256 MB	    220587	93822	70134	40.780%
 *
 *  문제
 *  알파벳 소문자로 이루어진 N개의 단어가 들어오면 아래와 같은 조건에 따라 정렬하는 프로그램을 작성하시오.
 *
 *  길이가 짧은 것부터
 *  길이가 같으면 사전 순으로
 *  단, 중복된 단어는 하나만 남기고 제거해야 한다.
 *
 *  입력
 *  첫째 줄에 단어의 개수 N이 주어진다. (1 ≤ N ≤ 20,000) 둘째 줄부터 N개의 줄에 걸쳐 알파벳 소문자로 이루어진 단어가 한 줄에 하나씩 주어진다. 주어지는 문자열의 길이는 50을 넘지 않는다.
 *
 *  출력
 *  조건에 따라 정렬하여 단어들을 출력한다.
 *
 *  예제 1.   13               i
 *           but              im
 *           i                it
 *           wont             no
 *           hesitate         but
 *           no               more
 *           more             wait
 *           no               wont
 *           more             yours
 *           it               cannot
 *           cannot           hesitate
 *           wait
 *           im
 *           yours
 *
 *
 *
 */
public class D14_1181 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int length = Integer.parseInt(br.readLine());
//            String[] sources = new String[length];
//            for (int i = 0; i < sources.length; i++) {
//                sources[i] = br.readLine();
//            }
            int length = Integer.parseInt("5");
            String[] sources = ("but\n" +
                    "i\n" +
                    "wont\n" +
                    "hesitate\n" +
                    "no\n" +
                    "more\n" +
                    "no\n" +
                    "more\n" +
                    "it\n" +
                    "cannot\n" +
                    "wait\n" +
                    "im\n" +
                    "yours").split("\n");

            String result = mySolution(sources);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  길이가 짧은 것부터
     *  길이가 같으면 사전 순으로
     *  단, 중복된 단어는 하나만 남기고 제거해야 한다.
     */
    private static String mySolution(String[] sources) {
        Set<String> set = Arrays.stream(sources).collect(Collectors.toSet());;
        List<String> checked = new ArrayList<>(set);

        checked.sort((a, b) -> {
            if (a.length() == b.length())
                return a.compareTo(b);
            return a.length() - b.length();
        });

        StringBuilder sb = new StringBuilder();
        for (String source : checked) {
            sb.append(source).append("\n");
        }

        return sb.toString();
    }

}