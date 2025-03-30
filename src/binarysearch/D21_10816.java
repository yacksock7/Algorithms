package binarysearch;

import java.io.*;
import java.util.Arrays;

/**
 *  TODO Day-21 : 숫자 카드 2
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1초	    256 MB	    177774	71474	50762	38.454%
 *
 *  문제
 *  숫자 카드는 정수 하나가 적혀져 있는 카드이다. 상근이는 숫자 카드 N개를 가지고 있다. 정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 상근이가 몇 개 가지고 있는지 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 상근이가 가지고 있는 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이 주어진다.
 *  둘째 줄에는 숫자 카드에 적혀있는 정수가 주어진다. 숫자 카드에 적혀있는 수는 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.
 *  셋째 줄에는 M(1 ≤ M ≤ 500,000)이 주어진다.
 *  넷째 줄에는 상근이가 몇 개 가지고 있는 숫자 카드인지 구해야 할 M개의 정수가 주어지며, 이 수는 공백으로 구분되어져 있다. 이 수도 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.
 *
 *  출력
 *  첫째 줄에 입력으로 주어진 M개의 수에 대해서, 각 수가 적힌 숫자 카드를 상근이가 몇 개 가지고 있는지를 공백으로 구분해 출력한다.
 *
 *  예제 1.   10                              3 0 0 1 2 0 0 2
 *           6 3 2 10 10 10 -10 -10 7 3
 *           8
 *           10 9 -5 2 3 4 5 -10
 *
 */
public class D21_10816 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(br.readLine());
            int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int m = Integer.parseInt(br.readLine());
            int[] targets = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


//            int[] numbers = Arrays.stream("6 3 2 10 10 10 -10 -10 7 3".split(" ")).mapToInt(Integer::parseInt).toArray();
//            int[] targets = Arrays.stream("10 9 -5 2 3 4 5 -10".split(" ")).mapToInt(Integer::parseInt).toArray();

            String result = tSolution(numbers, targets);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String tSolution(int[] numbers, int[] vList) {
        Arrays.sort(numbers);


        StringBuilder sb = new StringBuilder();
        for (int v : vList) {
            int lowerBoundIndex = findLowerBoundIndex(numbers, v);
            int upperBoundIndex = findUpperBoundIndex(numbers, v);
            sb.append(upperBoundIndex-lowerBoundIndex).append(" ");
        }

        return sb.toString();
    }

    private static int findLowerBoundIndex(int[] arr, int v) {
        int l = 0, r = arr.length-1;
        int lowerBoundIndex = arr.length;

        while (l <= r) {
            int m = (l+r) / 2;
            if (arr[m] < v) l = m+1;
            else {
                r = m-1;
                lowerBoundIndex = m;
            }
        }

        return lowerBoundIndex;
    }
    private static int findUpperBoundIndex(int[] arr, int v) {
        int l = 0, r = arr.length-1;
        int upperBoundIndex = arr.length;

        while (l <= r) {
            int m = (l+r) / 2;
            if (arr[m] <= v) l = m+1;
            else {
                r = m-1;
                upperBoundIndex = m;
            }
        }

        return upperBoundIndex;
    }
}