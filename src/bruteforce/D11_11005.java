package bruteforce;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  TODO Day-11 : 진법 변환 2
 *
 *  시간 제한	            메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  0.5 초 (추가 시간 없음)	256 MB	    69028	32050	27508	46.106%
 *
 *  문제
 *  10진법 수 N이 주어진다. 이 수를 B진법으로 바꿔 출력하는 프로그램을 작성하시오.
 *  10진법을 넘어가는 진법은 숫자로 표시할 수 없는 자리가 있다. 이런 경우에는 다음과 같이 알파벳 대문자를 사용한다.
 *  A: 10, B: 11, ..., F: 15, ..., Y: 34, Z: 35
 *
 *  입력
 *  첫째 줄에 N과 B가 주어진다. (2 ≤ B ≤ 36) N은 10억보다 작거나 같은 자연수이다.
 *
 *  출력
 *  첫째 줄에 10진법 수 N을 B진법으로 출력한다.
 *
 *  예제 1. 60466175 36 / ZZZZZ
 *
 */
public class D11_11005 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String source = "1000000000 2";
            String[] sources = source.split(" ");
            int n = Integer.parseInt(sources[0]);
            int b = Integer.parseInt(sources[1]);
            String result = mySolution(n, b);
            bw.write(String.valueOf(result.length()));

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     */
    private static String mySolution(int n, int b) {
       final String[] standard = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

       StringBuilder result = new StringBuilder();
       if (n == 0) result.append("0");
       while (n > 0) {
           result.append(standard[n%b]);
           n /= b;
       }

        return result.reverse().toString();
    }


    private static void others() {

        int maxLength = 0;
        for (int n = 1_000_000_000; n >= 1; n--) {
            for (int b = 2; b < 36; b++) {
                String result = mySolution(n, b);
                maxLength = Math.max(maxLength, result.length());
                System.out.printf("[n:%d], [b:%d], [maxLength:%d], ", n, b, maxLength);
                System.out.println();
            }
        }
    }
}