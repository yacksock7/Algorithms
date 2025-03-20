package sort;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  TODO Day-15 : 좌표 압축
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  2 초	    512 MB	    117074	50110	37504	39.997%
 *
 *  문제
 *  수직선 위에 N개의 좌표 X1, X2, ..., XN이 있다. 이 좌표에 좌표 압축을 적용하려고 한다.
 *  Xi를 좌표 압축한 결과 X'i의 값은 Xi > Xj를 만족하는 서로 다른 좌표 Xj의 개수와 같아야 한다.
 *  X1, X2, ..., XN에 좌표 압축을 적용한 결과 X'1, X'2, ..., X'N를 출력해보자.
 *
 *  입력
 *  첫째 줄에 N이 주어진다.
 *  둘째 줄에는 공백 한 칸으로 구분된 X1, X2, ..., XN이 주어진다.
 *
 *  출력
 *  첫째 줄에 X'1, X'2, ..., X'N을 공백 한 칸으로 구분해서 출력한다.
 *
 *  제한
 *  1 ≤ N ≤ 1,000,000
 *  -10^9 ≤ Xi ≤ 10^9
 *
 *  예제 1.   5                               2 3 0 3 1
 *           2 4 -10 4 -9
 *
 *  예제 1.   6                               1 0 1 0 1 0
 *          1000 999 1000 999 1000 999
 *
 */
public class D15_18870 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int length = Integer.parseInt(br.readLine());
//            String[] sources = br.readLine().split(" ");
            int length = Integer.parseInt("7");
            String[] sources = ("0 100 -10 -10 -100 0 10").split(" ");

            String result = mySolution(sources);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 결론은 특정 숫자보다 작은 숫자의 개수를 구하는 문제
     *
     * 전처리
     * - sources를 int[] 변경.
     *
     * 풀이
     * 1. set을 이용해 sources의 중복을 제거한다.
     * 2. 중복이 제거된 list를 오름차 순으로 정렬한다.
     * 3. 정렬된 list의 value와 index를 담는 Map을 만든다.
     * 4. sources를 순회하며 해당하는 value의 순서를 map에서 찾는다.
     *
     */
    private static String mySolution(String[] sources) {
        List<Integer> numbers = Arrays.stream(sources)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());


        List<Integer> uniqueNumbers = new HashSet<>(numbers).stream()
                .sorted()
                .collect(Collectors.toList());

        Map<Integer, Integer> numberMap = new HashMap<>();
        for (int i = 0; i < uniqueNumbers.size(); i++) {
            numberMap.put(uniqueNumbers.get(i), i);
        }

        StringBuilder sb = new StringBuilder();
        for (Integer number : numbers) {
            sb.append(numberMap.get(number)).append(" ");
        }

        return sb.toString();
    }

}