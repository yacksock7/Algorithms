package list01;

import java.io.*;

/**
 *  TODO Day-4 : 과제 안 내신 분...?
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1초	    128 MB	    158042	81855	70452	51.637%
 *
 *  문제
 *  X대학 M교수님은 프로그래밍 수업을 맡고 있다. 교실엔 학생이 30명이 있는데, 학생 명부엔 각 학생별로 1번부터 30번까지 출석번호가 붙어 있다.
 *
 *  교수님이 내준 특별과제를 28명이 제출했는데, 그 중에서 제출 안 한 학생 2명의 출석번호를 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  입력은 총 28줄로 각 제출자(학생)의 출석번호 n(1 ≤ n ≤ 30)가 한 줄에 하나씩 주어진다. 출석번호에 중복은 없다.
 *
 *  출력
 *  출력은 2줄이다. 1번째 줄엔 제출하지 않은 학생의 출석번호 중 가장 작은 것을 출력하고, 2번째 줄에선 그 다음 출석번호를 출력한다.
 *
 *  예제 01. 3                2
 *          1                8
 *          4
 *          5
 *          7
 *          9
 *          6
 *          10
 *          11
 *          12
 *          13
 *          14
 *          15
 *          16
 *          17
 *          18
 *          19
 *          20
 *          21
 *          22
 *          23
 *          24
 *          25
 *          26
 *          27
 *          28
 *          29
 *          30
 */
public class D4_5597 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringBuilder source = new StringBuilder();
            String str = null;
            while ((str = br.readLine()) != null) {
                source.append("\n").append(str);
            }

            source.replace(0,1, "");
            String[] sources = source.toString().split("\n");


            String result = mySolution(sources);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  length가 30인 list를 만들고, sources의 item에 맞는 index의 value를 1로 바꿔 줄꺼야.
     *  1이 아닌 초기값 0으로 남아있는 length+1은 과제를 제출하지 않은 번호다.
     */
    private static String mySolution(String[] sources) {

        int[] list = new int[30];
        for (int i = 0; i < sources.length; i++) {
            list[Integer.parseInt(sources[i])-1] = 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            if (list[i] == 0) sb.append("\n").append(i+1);
        }
        sb.replace(0, 1, "");

        return sb.toString();
    }
}