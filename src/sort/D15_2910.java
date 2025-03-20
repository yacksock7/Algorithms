package sort;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  TODO Day-15 : 빈도 정렬
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1 초	    128 MB	    14688	8299	6178	56.011%
 *
 *  문제
 *  위대한 해커 창영이는 모든 암호를 깨는 방법을 발견했다. 그 방법은 빈도를 조사하는 것이다.
 *  창영이는 말할 수 없는 방법을 이용해서 현우가 강산이에게 보내는 메시지를 획득했다. 이 메시지는 숫자 N개로 이루어진 수열이고, 숫자는 모두 C보다 작거나 같다. 창영이는 이 숫자를 자주 등장하는 빈도순대로 정렬하려고 한다.
 *  만약, 수열의 두 수 X와 Y가 있을 때, X가 Y보다 수열에서 많이 등장하는 경우에는 X가 Y보다 앞에 있어야 한다. 만약, 등장하는 횟수가 같다면, 먼저 나온 것이 앞에 있어야 한다.
 *  이렇게 정렬하는 방법을 빈도 정렬이라고 한다.
 *  수열이 주어졌을 때, 빈도 정렬을 하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 메시지의 길이 N과 C가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ C ≤ 1,000,000,000)
 *  둘째 줄에 메시지 수열이 주어진다.
 *
 *  출력
 *  첫째 줄에 입력으로 주어진 수열을 빈도 정렬한 다음 출력한다.
 *
 *
 *  예제 1.   5 2                             2 2 2 1 1
 *           2 1 2 1 2
 *
 *  예제 2.   9 3                             1 1 1 3 3 3 2 2 2
 *           1 3 3 3 2 2 2 1 1
 *
 *  예제 3.   9 77                            11 11 11 33 33 25 25 77 54
 *           11 33 11 77 54 11 25 25 33
 *
 */
public class D15_2910 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int length = Integer.parseInt(br.readLine().split(" ")[0]);
//            String[] sources = br.readLine().split(" ");

//            int length = Integer.parseInt("9 77".split(" ")[0]);
            String[] sources = ("11 33 11 77 54 11 25 25 33").split(" ");

            String result = mySolution(sources);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 내 생각에는 숫자별 개수 + 처음 등장한 index로 정리한다. -> Map<Integer, Object>
     * 정리한 map의 key를 정렬한다. 숫자별 개수 -> 처음 등장한 index 기준
     * 정렬한 key 순서대로 숫자별 개수만큼 출력한다.
     *
     * 전처리
     * - NumberObj class 정의
     *      private int number;
     *      private int count;
     *      private int firstIdx;
     *
     * 풀이
     * 1. sources를 순회하며, Map<Integer, NumberObj>
     * 2. 정리한 map의 key를 정렬한다.
     *      숫자별 개수가 같으면 -> 처음 등장한 index가 작은 순
     *      숫자별 개수가 다르면 -> 숫자별 개수가 큰 순
     * 3. 정렬된 key의 순서대로 key를 count만큼 출력한다.
     *
     */
    private static String mySolution(String[] sources) {

        Map<Integer, NumberObject> sourceMap = new HashMap<>();
        for (int i = 0; i < sources.length; i++) {
            int source = Integer.parseInt(sources[i]);
            if (sourceMap.containsKey(source)) {
                NumberObject number = sourceMap.get(source);
                number.addCount();
                sourceMap.put(source, number);

            } else {
                sourceMap.put(source, new NumberObject(i));
            }
        }

        List<Integer> numbers =
                sourceMap.keySet().stream()
                        .sorted((v, o) -> {
                            NumberObject vNumber = sourceMap.get(v);
                            NumberObject oNumber = sourceMap.get(o);
                            if (vNumber.getCount() == oNumber.getCount()) {
                                return vNumber.getFirstIdx() - oNumber.getFirstIdx();
                            } else {
                                return oNumber.getCount() - vNumber.getCount();
                            }
                        }).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (Integer number : numbers) {
            int count = sourceMap.get(number).getCount();
            for (int i = 0; i < count ; i++) {
                sb.append(number).append(" ");
            }
        }

        return sb.toString();
    }

    static class NumberObject {
        private int count;
        private int firstIdx;

        public NumberObject(int firstIdx) {
            this.firstIdx = firstIdx;
            this.count = 1;
        }

        public int getCount() {
            return count;
        }

        public void addCount() {
            this.count++;
        }

        public int getFirstIdx() {
            return firstIdx;
        }
    }

}