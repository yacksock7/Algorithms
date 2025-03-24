package prefixsum;

import java.io.*;
import java.util.Arrays;

/**
 *  TODO Day-16 : 구간합 구하기
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1 초	    256 MB	    149463	61418	44563	38.434%
 *
 *  문제
 *  수 N개가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 수의 개수 N과 합을 구해야 하는 횟수 M이 주어진다.
 *  둘째 줄에는 N개의 수가 주어진다.
 *  수는 1,000보다 작거나 같은 자연수이다.
 *  셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
 *
 *  출력
 *  총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.
 *
 *  제한
 *  1 ≤ N ≤ 100,000
 *  1 ≤ M ≤ 100,000
 *  1 ≤ i ≤ j ≤ N
 *
 *  예제 1.  5 3              12
 *          5 4 3 2 1       9
 *          1 3             1
 *          2 4
 *          5 5
 */
public class D16_11659 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int length = Integer.parseInt(br.readLine().split(" ")[1]);
//            String[] list = br.readLine().split(" ");
//            String[] ranges = new String[length];
//
//            for (int i = 0; i < length; i++) {
//                ranges[i] = br.readLine();
//            }

            String[] list = ("5 4 3 2 1").split(" ");
            String[] ranges = ("1 3\n" +
                    "2 4\n" +
                    "5 5").split("\n");


            String result = mySolution02(list, ranges);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * - 구간합 문제
     *      배열 순회 방법과 누적합 배열을 사용하는 방법을 구현해보자.
     */

    // 배열 순회
    private static String mySolution(String[] sources, String[] ranges) {
        int[] numbers = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            numbers[i] = Integer.parseInt(sources[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (String range : ranges) {
            String[] split = range.split(" ");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);

            int sum = 0;
            for (int i = start-1; i < end ; i++) {
                sum += numbers[i];
            }
            sb.append(sum).append("\n");
        }

        return sb.toString();
    }

    // 누적합 배열
    /**
     * 전처리
     * 직전 length의 요소까지의 합을 담은 배열, 즉 누적합 배열을 만든다.
     *
     * 합을 구해야하는 구간의 end+1 - start+1가 구간합이다.
     *  따라서 range를 돌면서 구간합을 출력한다.
     */
    private static String mySolution02(String[] sources, String[] ranges) {
        int[] acc = new int[sources.length + 1];
        for (int i = 0; i < sources.length ; i++) {
            acc[i+1] = acc[i] + Integer.parseInt(sources[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (String range : ranges) {
            String[] split = range.split(" ");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);

            sb.append(acc[end] - acc[start-1]).append("\n");
        }

        return sb.toString();
    }

}