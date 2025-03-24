package prefixsum;

import java.io.*;

/**
 *
 *  TODO Day-16 : Generic Queries
 *  - 문제 참조 : https://www.acmicpc.net/problem/16713
 *
 */
public class D16_16713 {

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


            String[] list = ("4 4 2 1 0").split(" ");
            String[] ranges = ("1 1\n" +
                    "1 2\n" +
                    "1 3\n" +
                    "2 4").split("\n");


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
            acc[i+1] = acc[i] ^ Integer.parseInt(sources[i]);
        }

        int result = 0 ;
        for (String range : ranges) {
            String[] split = range.split(" ");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);

            result ^= acc[end] ^ acc[start-1];
        }

        return String.valueOf(result);
    }

}