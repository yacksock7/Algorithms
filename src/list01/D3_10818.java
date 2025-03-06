package list01;

import java.io.*;

/**
 *  TODO Day-3 : 최소, 최대
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1 초	    256 MB	    431914	196939	147969	44.400%
 *
 *  문제
 *  N개의 정수가 주어진다. 이때, 최솟값과 최댓값을 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 정수의 개수 N (1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄에는 N개의 정수를 공백으로 구분해서 주어진다. 모든 정수는 -1,000,000보다 크거나 같고, 1,000,000보다 작거나 같은 정수이다.
 *
 *  출력
 *  첫째 줄에 주어진 정수 N개의 최솟값과 최댓값을 공백으로 구분해 출력한다.
 *
 *  예제 1. 5 \n 20 10 35 30 7 / 7 35
 *
 */
public class D3_10818 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String count = br.readLine();
            String[] test = br.readLine().split(" ");

            String result = mySolution(test);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  nums length() 만큼 반복하면서
     *  max 값 체크
     *  min 값 체크
     */
    private static String mySolution(String[] nums) {

        int max = -1_000_000;
        int min = 1_000_000;
        for (String numStr : nums) {
            int num = Integer.parseInt(numStr);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return String.format("%d %d", min, max);
    }
}
