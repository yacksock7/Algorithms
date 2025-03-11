package list01;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  TODO Day-9 : 두 수의 합
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1초	    128MB	    74362	26839	19499	34.579%
 *
 *  문제
 *  n개의 서로 다른 양의 정수 a1, a2, ..., an으로 이루어진 수열이 있다. ai의 값은 1보다 크거나 같고, 1000000보다 작거나 같은 자연수이다. 자연수 x가 주어졌을 때, ai + aj = x (1 ≤ i < j ≤ n)을 만족하는 (ai, aj)쌍의 수를 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 수열의 크기 n이 주어진다. 다음 줄에는 수열에 포함되는 수가 주어진다. 셋째 줄에는 x가 주어진다. (1 ≤ n ≤ 100000, 1 ≤ x ≤ 2000000)
 *
 *  출력
 *  문제의 조건을 만족하는 쌍의 개수를 출력한다.
 *
 *  예제 1.  9                        3
 *          5 12 7 10 9 1 2 3 11
 *          13
 */
public class D9_3273 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int length = Integer.parseInt("7");

            String source = "1 2 3 4 5 6 7";
            int target = 8;

//            int length = Integer.parseInt(br.readLine());
//            int[] source = new int[length];
//            for (int i = 0; i < length; i++) {
//                source[i] = Integer.parseInt(br.readLine());
//            }

            String result = mySolution(source, target);

            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  방법 01
     *  1. 오름차순으로 정렬한다.
     *  2. 0 ~ list.length까지 순회한다.
     *  3. index의 value + index+j의 value = 13 인지 확인하고
     *  4. index의 value + index+j의 value > 13 이면 break;
     *
     *
     *  방법 02
     *  1. list를 set으로 변환한다.
     *  2. list 순회하며, target - list[i]에 해당하는 값이 있는지 찾는다.
     *  3. 값이 있으면 result++;를 진행한다.
     */
    private static String mySolution(String source, int target) {
        List<Integer> numbers =
                Arrays.stream(source.split(" "))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList());
        Set<Integer> numberSet = new HashSet<>(numbers);

        Long result = numbers.stream().filter(number -> {
            int a = target - number;
            return numberSet.contains(a);
        }).count();

        return String.valueOf(result / 2);
    }
}