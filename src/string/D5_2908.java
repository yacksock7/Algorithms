package string;

import java.io.*;

/**
 *  TODO Day-5 : 상수
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1 초	    128 MB	    170091	116378	98991	68.717%
 *
 *  문제
 *  상근이의 동생 상수는 수학을 정말 못한다. 상수는 숫자를 읽는데 문제가 있다. 이렇게 수학을 못하는 상수를 위해서 상근이는 수의 크기를 비교하는 문제를 내주었다. 상근이는 세 자리 수 두 개를 칠판에 써주었다. 그 다음에 크기가 큰 수를 말해보라고 했다.
 *  상수는 수를 다른 사람과 다르게 거꾸로 읽는다. 예를 들어, 734와 893을 칠판에 적었다면, 상수는 이 수를 437과 398로 읽는다. 따라서, 상수는 두 수중 큰 수인 437을 큰 수라고 말할 것이다.
 *  두 수가 주어졌을 때, 상수의 대답을 출력하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 상근이가 칠판에 적은 두 수 A와 B가 주어진다. 두 수는 같지 않은 세 자리 수이며, 0이 포함되어 있지 않다.
 *
 *  출력
 *  첫째 줄에 상수의 대답을 출력한다.
 *
 *  예제 1. 734 893 / 437
 *  예제 2. 221 231 / 132
 *  예제 3. 839 237 / 938
 */
public class D5_2908 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String source = "734 893"; // br.readLine();

            String result = mySolution(source);

            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  source를 공백 기준으로 분리해 2개의 숫자를 얻는다.
     *  얻은 두개의 숫자를 각각 reverse()한다.
     *  Integer로 변환해 최댓값을 구하여 리턴한다.
     */
    private static String mySolution(String source) {
        String[] sources = source.split(" ");
        Integer a = Integer.parseInt("" + sources[0].charAt(2) + sources[0].charAt(1) + sources[0].charAt(0));
        Integer b = Integer.parseInt("" + sources[1].charAt(2) + sources[1].charAt(1) + sources[1].charAt(0));
        int max = Math.max(a, b);
        return String.valueOf(max);
    }
}