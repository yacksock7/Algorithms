package prefixsum;

import java.io.*;
import java.util.Arrays;

/**
 *
 *  TODO Day-18 : 태상이의 훈련소 생활
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1 초	    512 MB	    3047	1813	1425	60.177%
 *
 *  문제
 *      2020년 5월 14일 논산훈련소에 입대한 태상이는 첫 총기 훈련에서 가스 조절기를 잃어버리는 중대한 실수를 범했다. 그로 인해, 태상이는 조교들에게 눈총을 받게 되었다. 조교들은 태상이에게 연병장(운동장)의 흙을 옮기는 일을 주고 제대로 수행하지 못하면 징계를 내리려고 한다.
 *      연병장은 일렬로 이어진 N개의 칸으로 이루어져 있으며 각 칸마다 높이를 가지고 있고, 첫 번째 칸부터 순서대로 1번, 2번, 3번, ..., N번 칸으로 명칭이 붙어있다. 조교들은 총 M명이 있으며, 각 조교들은 태상이에게 a번 칸부터 b번 칸까지 높이 k만큼 흙을 덮거나 파내라고 지시한다. 흙은 주변 산에서 얼마든지 구할 수 있으므로 절대로 부족하지 않다.
 *      태상이는 각 조교의 지시를 각각 수행하면, 다른 조교의 지시로 흙을 덮어둔 칸을 다시 파내기도 하는 비효율적인 일이 발생하는 것을 깨달았다. 그래서 태상이는 각 조교의 지시를 모아 연병장 각 칸의 최종 높이를 미리 구해 한 번에 일을 수행하려고 한다.
 *      불쌍한 태상이를 위해 조교들의 지시를 모두 수행한 뒤 연병장 각 칸의 높이를 구하자.
 *
 *  입력
 *      첫 줄에 연병장의 크기 N과 조교의 수 M이 주어진다.
 *      둘째 줄에 연병장 각 칸의 높이 Hi가 순서대로 N개 주어진다.
 *      셋째 줄부터 M개의 줄에 각 조교의 지시가 주어진다.
 *      각 조교의 지시는 세 개의 정수 a, b, k로 이루어져 있다.
 *
 *      k ≥ 0인 경우 a번 칸부터 b번 칸까지 높이가 각각 |k| 만큼 늘어나도록 흙을 덮어야 한다.
 *      k < 0인 경우 a번 칸부터 b번 칸까지 높이가 각각 |k| 만큼 줄어들도록 흙을 파내야 한다.
 *
 *  출력
 *      모든 지시를 수행한 뒤 연병장 각 칸의 높이를 공백을 사이에 두고 출력한다.
 *
 *  제한
 *      1 ≤ N ≤ 100,000
 *      1 ≤ M ≤ 100,000
 *      -10,000 ≤ Hi ≤ 10,000
 *      N, M, Hi는 정수
 *      조교의 지시
 *      1 ≤ a ≤ b ≤ N
 *      |k| ≤ 100
 *
 *
 *  예제 1.   10 3                        -2 1 2 3 4 6 5 2 1 0
 *          1 2 3 4 5 -1 -2 -3 -4 -5
 *          1 5 -3
 *          6 10 5
 *          2 7 2
 */
public class D18_19951 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            String[] firstLine = br.readLine().split(" ");
//            int n = Integer.parseInt(firstLine[0]);
//            int m = Integer.parseInt(firstLine[1]);
//
//            int[] ground = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//
//            String[] q = new String[m];
//            for (int i = 0; i < m; i++) {
//                q[i] = br.readLine();
//            }

            String[] firstLine = ("2 4").split(" ");
            int[] ground = Arrays.stream(("1 2 3 4 5 -1 -2 -3 -4 -5").split(" ")).mapToInt(Integer::parseInt).toArray();
            String[] q = ("1 5 -3\n" +
                    "6 10 5\n" +
                    "2 7 2").split("\n");

            String result = mySolution02(ground, q);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  1. 조교를 돌면서 gound 수정
     *  시간초과
     */
    private static String mySolution(int[] ground, String[] q) {
        for (String s : q) {
            String[] split = s.split(" ");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);
            int num = Integer.parseInt(split[2]);

            while (start <= end) {
                ground[start-1] += num;
                start++;
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int i : ground) {
            sb.append(i).append(" ");
        }
        return sb.toString();
    }

    /**
     *  ground 배열에 각 칸마다 흙의 높이가 들어가 있다.
     *  조교가 범위(start ~ end)를 지정하고 특정 높이(num)만큼 더하거나 빼라고 한다.
     *
     *  효율적으로 계산하기 위해.
     *  1. delta라는 배열을 만들어 변화량을 적는다.
     *      - 변화량은 start 마지막까지 num만큼 더하고,
     *      - end+1부터 마지막까지 num만큼 빼서 범위가 아닌 부분은 상쇄시킨다.
     *
     *  2. delta의 모든 범위의 누적합 accDelta을 구한다.
     *      - delta의 각 칸별로 변화량이 적혀있다.
     *      - 칸별 누적합(accDelta[i-1] + acc[i])이 칸별 변화량이 된다.
     *
     *  3. 칸별 변화량 + 기존 ground의 value가 태상이가 해야하는 삽질의 횟수이다.
     */

    private static String mySolution02(int[] ground, String[] q) {
        int[] delta = new int[ground.length+2];
        for (String s : q) {
            String[] split = s.split(" ");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);
            int num = Integer.parseInt(split[2]);

            delta[start] += num;
            delta[end+1] += -num;
        }

        int[] accDelta = new int[ground.length+1];
        for (int i = 1; i <= ground.length; i++) {
            accDelta[i] = accDelta[i-1] + delta[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= ground.length; i++) {
            sb.append(ground[i-1] + accDelta[i]).append(" ");
        }

        return sb.toString();
    }
}