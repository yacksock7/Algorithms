package list01;
import java.io.*;
import java.util.*;

/**
 *  TODO Day-4 : 나머지
 *
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	    정답 비율
 *  1초	    128 MB	    247037	143048	118637	    57.737%
 *
 *  문제
 *  두 자연수 A와 B가 있을 때, A%B는 A를 B로 나눈 나머지 이다. 예를 들어, 7, 14, 27, 38을 3으로 나눈 나머지는 1, 2, 0, 2이다.
 *  수 10개를 입력받은 뒤, 이를 42로 나눈 나머지를 구한다. 그 다음 서로 다른 값이 몇 개 있는지 출력하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄부터 열번째 줄 까지 숫자가 한 줄에 하나씩 주어진다. 이 숫자는 1,000보다 작거나 같고, 음이 아닌 정수이다.
 *
 *  출력
 *  첫째 줄에, 42로 나누었을 때, 서로 다른 나머지가 몇 개 있는지 출력한다.
 *
 *  예제 01. 1                10
 *          2
 *          3
 *          4
 *          5
 *          6
 *          7
 *          8
 *          9
 *          10
 */
public class D4_3052 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] sources = new int[10];
            for (int i = 0; i < sources.length; i++) {
                sources[i] = Integer.parseInt(br.readLine());
            }

            String result = mySolution(sources);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  sources의 각 item을 42로 나누어 나머지를 구한다.
     *  구한 나머지를 Set에 넣는다.
     *  set의 length()를 반환한다.
     */
    private static String mySolution(int[] sources) {

        Set<Integer> resultSet = new HashSet<>();
        for (int i = 0; i < sources.length; i++) {
            resultSet.add(sources[i] % 42);
        }
        return String.valueOf(resultSet.size());
    }
}