package binarysearch;

import java.io.*;
import java.util.Arrays;

/**
 *  TODO Day-20 : 문자열 집합
 *
 *  시간 제한	        메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  2 초 (하단 참고)	1536 MB	    65442	35716	27348	54.162%
 *
 *  문제
 *  총 N개의 문자열로 이루어진 집합 S가 주어진다.
 *  입력으로 주어지는 M개의 문자열 중에서 집합 S에 포함되어 있는 것이 총 몇 개인지 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 문자열의 개수 N과 M (1 ≤ N ≤ 10,000, 1 ≤ M ≤ 10,000)이 주어진다.
 *  다음 N개의 줄에는 집합 S에 포함되어 있는 문자열들이 주어진다.
 *  다음 M개의 줄에는 검사해야 하는 문자열들이 주어진다.
 *  입력으로 주어지는 문자열은 알파벳 소문자로만 이루어져 있으며, 길이는 500을 넘지 않는다. 집합 S에 같은 문자열이 여러 번 주어지는 경우는 없다.
 *
 *  출력
 *  첫째 줄에 M개의 문자열 중에 총 몇 개가 집합 S에 포함되어 있는지 출력한다.
 *
 *  예제 1.   5 11                    4
 *           baekjoononlinejudge
 *           startlink
 *           codeplus
 *           sundaycoding
 *           codingsh
 *           baekjoon
 *           codeplus
 *           codeminus
 *           startlink
 *           starlink
 *           sundaycoding
 *           codingsh
 *           codinghs
 *           sondaycoding
 *           startrink
 *           icerink
 */
public class D20_1920 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int arrLength = Integer.parseInt(br.readLine());
//            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//            int numbersLength = Integer.parseInt(br.readLine());
//            int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[] arr = Arrays.stream("4 1 5 2 3".split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] numbers = Arrays.stream("1 3 7 9 5".split(" ")).mapToInt(Integer::parseInt).toArray();

            String result = mySolution(arr, numbers);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String mySolution(int[] arr, int[] numbers) {

        StringBuilder sb = new StringBuilder();
        Arrays.sort(arr);

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];

//            int idx = Arrays.binarySearch(arr, number);
//            boolean exist = idx >= 0;

            boolean exist = isExist(arr, number);

            sb.append(exist ? 1:0).append("\n");
        }

        return sb.toString();
    }

    private static boolean isExist(int[] arr, int number) {
        int l = 0;
        int r = arr.length-1;

        while (l <= r) {
            int m = (l+r) / 2;
            if (arr[m] < number) {
                l = m+1;
            } else if (arr[m] > number) {
                r = m-1;
            } else {
                return true;
            }
        }

        return false;
    }
}