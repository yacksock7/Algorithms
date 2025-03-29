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
public class D20_14425 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            String[] firstLine = br.readLine().split(" ");
//            int n = Integer.parseInt(firstLine[0]);
//            int m = Integer.parseInt(firstLine[1]);
//
//            String[] arr = new String[n];
//            for (int i = 0; i < n; i++) {
//                arr[i] = br.readLine();
//            }
//
//            String[] texts = new String[m];
//            for (int i = 0; i < m; i++) {
//                texts[i] = br.readLine();
//            }

            String[] arr = ("baekjoononlinejudge\n" +
                    "startlink\n" +
                    "codeplus\n" +
                    "sundaycoding\n" +
                    "codingsh").split("\n");

            String[] texts = ("baekjoon\n" +
                    "codeplus\n" +
                    "codeminus\n" +
                    "startlink\n" +
                    "starlink\n" +
                    "sundaycoding\n" +
                    "codingsh\n" +
                    "codinghs\n" +
                    "sondaycoding\n" +
                    "startrink\n" +
                    "icerink").split("\n");

            String result = mySolution(arr, texts);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String mySolution(String[] arr, String[] texts) {
        Arrays.sort(arr);

        int result = 0;
        for (String text : texts) {
//            int idx = Arrays.binarySearch(arr, text);
//            boolean exist = idx >= 0;

            boolean exist = isExist(arr, text);

            if (exist) result++;
        }

        return String.valueOf(result);
    }

    private static boolean isExist(String[] arr, String text) {
        int l = 0;
        int r = arr.length -1;
        while (l <= r) {
            int m = (l+r) /2;
            if (arr[m].compareTo(text) < 0) {
                l = m+1;
            } else if (arr[m].compareTo(text) > 0) {
                r = m-1;
            } else {
                return true;
            }
        }

        return false;
    }
}