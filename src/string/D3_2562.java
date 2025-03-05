package string;

import java.io.*;

/**
 *  TODO Day-3 : 최댓값
 *
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	    정답 비율
 *  1초	    128 MB	    371262	171375	140965	    45.481%
 *
 *  문제
 *  9개의 서로 다른 자연수가 주어질 때, 이들 중 최댓값을 찾고 그 최댓값이 몇 번째 수인지를 구하는 프로그램을 작성하시오.
 *
 *  예를 들어, 서로 다른 9개의 자연수
 *
 *  3, 29, 38, 12, 57, 74, 40, 85, 61
 *
 *  이 주어지면, 이들 중 최댓값은 85이고, 이 값은 8번째 수이다.
 *
 *  입력
 *  첫째 줄부터 아홉 번째 줄까지 한 줄에 하나의 자연수가 주어진다. 주어지는 자연수는 100 보다 작다.
 *
 *  출력
 *  첫째 줄에 최댓값을 출력하고, 둘째 줄에 최댓값이 몇 번째 수인지를 출력한다.
 *
 *  예제 1.   3           85
 *          29           8
 *          38
 *          12
 *          57
 *          74
 *          40
 *          85
 *          61
 *
 */

public class D3_2562 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] num = new int[9];
            for (int i = 0; i < 9; i++) {
                num[i] = Integer.parseInt(br.readLine());
            }

//            int[] num = {3, 29, 38, 12, 57, 74, 40, 85, 61};

            String result = mySolution(num);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String mySolution(int[] num) {

        int max = 0;
        int maxIdx = -1;
        for (int i = 0; i < num.length; i++) {
            int maxTemp = Math.max(max, num[i]);
            if (maxTemp != max) {
                max = maxTemp;
                maxIdx = i+1;
            }
        }

        return String.format("%d\n%d", max, maxIdx);
    }

}
