package sort;

import java.io.*;
import java.util.Arrays;

/**
 *  TODO Day-14 : 단어 정렬
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  3 초	    256 MB	    170309	77850	59916	44.183%
 *  문제
 *  온라인 저지에 가입한 사람들의 나이와 이름이 가입한 순서대로 주어진다. 이때, 회원들을 나이가 증가하는 순으로, 나이가 같으면 먼저 가입한 사람이 앞에 오는 순서로 정렬하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 온라인 저지 회원의 수 N이 주어진다. (1 ≤ N ≤ 100,000)
 *
 *  둘째 줄부터 N개의 줄에는 각 회원의 나이와 이름이 공백으로 구분되어 주어진다. 나이는 1보다 크거나 같으며, 200보다 작거나 같은 정수이고, 이름은 알파벳 대소문자로 이루어져 있고, 길이가 100보다 작거나 같은 문자열이다. 입력은 가입한 순서로 주어진다.
 *
 *  출력
 *  첫째 줄부터 총 N개의 줄에 걸쳐 온라인 저지 회원을 나이 순, 나이가 같으면 가입한 순으로 한 줄에 한 명씩 나이와 이름을 공백으로 구분해 출력한다.
 *
 *  예제 1.   3                   20 Sunyoung
 *           21 Junkyu          21 Junkyu
 *           21 Dohyun          21 Dohyun
 *           20 Sunyoung
 *
 */
public class D14_10814 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int length = Integer.parseInt(br.readLine());
//            String[] sources = new String[length];
//            for (int i = 0; i < sources.length; i++) {
//                sources[i] = br.readLine();
//            }
            int length = Integer.parseInt("3");
            String[] sources = ("21 Junkyu\n" +
                    "21 Dohyun\n" +
                    "20 Sunyoung").split("\n");

            String result = mySolution(sources);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     *  나이와 이름을 담는 객체 생성?
     *  나이 순 -> 가입한 수 (현재 정렬 순)
     */
    private static String mySolution(String[] sources) {

        Arrays.sort(sources, (value, other) -> {
            String[] values = value.split(" ");
            String[] others = other.split(" ");

            return Integer.parseInt(values[0]) - Integer.parseInt(others[0]);
        });

        StringBuilder sb = new StringBuilder();
        for (String source : sources) {
            sb.append(source).append("\n");
        }

        return sb.toString();
    }

}