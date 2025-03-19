package sort;

import java.io.*;
import java.util.Arrays;

/**
 *  TODO Day-14 : 수 정렬하기 2
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  2 초 	256 MB	    366575	113978	79882	31.474%
 *
 *  문제
 *  N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 수가 주어진다. 이 수는 절댓값이 1,000,000보다 작거나 같은 정수이다. 수는 중복되지 않는다.
 *
 *  출력
 *  첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다
 *
 *  예제 1.   5           1
 *           5          2
 *           4          3
 *           3          4
 *           2          5
 *           1
 *
 *
 *
 */
public class D14_2751 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int length = Integer.parseInt(br.readLine());
//            Integer[] sources = new Integer[length];
//            for (int i = 0; i < sources.length; i++) {
//                sources[i] = Integer.parseInt(br.readLine());
//            }
            int length = Integer.parseInt("5");
            int[] sources = Arrays.stream(("5\n" +
                    "4\n" +
                    "3\n" +
                    "2\n" +
                    "1").split("\n")).mapToInt(Integer::parseInt).toArray();

            String result = mySolution(sources);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  Arrays.sort()를 사용하여 오름차순으로 정렬
     */
    private static String mySolution(int[] sources) {
        Arrays.sort(sources);


        StringBuilder sb = new StringBuilder();
        for (Integer source : sources) {
            sb.append(source).append("\n");
        }

        return sb.toString();
    }

}