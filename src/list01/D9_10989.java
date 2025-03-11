package list01;

import java.io.*;
import java.util.Arrays;

/**
 *  TODO Day-9 : 수 정렬하기 3
 *
 *  시간 제한	        메모리 제한	    제출	    정답	    맞힌 사람	정답 비율
 *  5 초 (하단 참고)	8 MB (하단 참고)	338902	81521	62259	23.921%
 *
 *  문제
 *  N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 수의 개수 N(1 ≤ N ≤ 10,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 수가 주어진다. 이 수는 10,000보다 작거나 같은 자연수이다.
 *
 *  출력
 *  첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.
 *
 *  예제 1.  10      1
 *          5       1
 *          2       2
 *          3       2
 *          1       3
 *          4       3
 *          2       4
 *          3       5
 *          5       5
 *          7       7
 *          1
 */
public class D9_10989 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int length = Integer.parseInt("10");

            int[] source = Arrays.stream(("5\n" +
                    "10000\n" +
                    "2\n" +
                    "3\n" +
                    "1\n" +
                    "4\n" +
                    "2\n" +
                    "3\n" +
                    "5\n" +
                    "7\n" +
                    "1").split("\n")).mapToInt(Integer::parseInt).toArray();


//            int length = Integer.parseInt(br.readLine());
//            int[] source = new int[length];
//            for (int i = 0; i < length; i++) {
//                source[i] = Integer.parseInt(br.readLine());
//            }

            String result = mySolution02(source);

            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  시간 초과....
     *  1. 0 ~ source.length까지 순회하며 아래 작업을 반복한다. (index = i)
     *  2. i > 0 && i < i-1 ? i와 i-1의 순서를 바꿔준다.
     *  3. 순서가 바뀌었다면, for(int j = 0; j < i; j++) 만큼 2번을 반복한다.
     *  4. if (바뀌지 않았으면) break;
     */
    private static String mySolution(int[] numbers) {

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < i; j++) {
                int idx = i-j;

                if (numbers[idx] < numbers[idx-1]) {
                    int temp = numbers[idx];
                    numbers[idx] = numbers[idx-1];
                    numbers[idx-1] = temp;
                } else {
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            sb.append(numbers[i]).append("\n");
        }

        return sb.toString();
    }


    /**
     *
     */
    private static String mySolution02(int[] numbers) {

        int[] counted = new int[10001];
        for (int i = 0; i < numbers.length; i++) {
            counted[numbers[i]]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < counted.length; i++) {
            for (int j = 0; j < counted[i]; j++) {
                sb.append(i +"\n");
            }
        }

        return sb.toString();
    }

}