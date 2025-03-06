package list01;

import java.io.*;

/**
 *  TODO Day-4 : 나머지
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  2초	    128 MB	    320762	163265	131666	50.346%
 *
 *  문제
 *  세준이는 기말고사를 망쳤다. 세준이는 점수를 조작해서 집에 가져가기로 했다. 일단 세준이는 자기 점수 중에 최댓값을 골랐다. 이 값을 M이라고 한다. 그리고 나서 모든 점수를 점수/M*100으로 고쳤다.
 *  예를 들어, 세준이의 최고점이 70이고, 수학점수가 50이었으면 수학점수는 50/70*100이 되어 71.43점이 된다.
 *  세준이의 성적을 위의 방법대로 새로 계산했을 때, 새로운 평균을 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 시험 본 과목의 개수 N이 주어진다. 이 값은 1000보다 작거나 같다. 둘째 줄에 세준이의 현재 성적이 주어진다. 이 값은 100보다 작거나 같은 음이 아닌 정수이고, 적어도 하나의 값은 0보다 크다.
 *
 *  출력
 *  첫째 줄에 새로운 평균을 출력한다. 실제 정답과 출력값의 절대오차 또는 상대오차가 10-2 이하이면 정답이다.
 *
 *  예제 01   3           75.0
 *          40 80 60
 *  예제 02   3           66.666667
 *          10 20 30
 *  예제 03   4           75.25
 *          1 100 100 100
 *  예제 04   5           38.75
 *          1 2 4 8 16
 *  예제 05   2           65.0
 *          3 10
 *  예제 06   4           32.5
 *          10 20 0 100
 *  예제 07   1           100.0
 *          50
 *  예제 08   9           55.55555555555556
 *          10 20 30 40 50 60 70 80 90
 */
public class D4_1546 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            String firstLine = br.readLine();
//            String[] scores = br.readLine().split(" ");
            String[] scores = "10 20 30 40 50 60 70 80 90".split(" ");


            String result = mySolution(scores);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 최댓값을 구한다.
     * scores를 순회하며, 가짜 점수를 구한다. (점수/최댓값 * 100)
     * 모든 가짜 점수의 평균을 구한다.
     * 단 double로 계산하자.(이거는 float으로 해도 될듯)
     */
    private static String mySolution(String[] scores) {

        double max = 0;
        double total = 0;

        for (int i = 0; i < scores.length; i++) {
            max = Double.max(max, Double.valueOf(scores[i]));
        }
        for (int i = 0; i < scores.length; i++) {
            total += Double.valueOf(scores[i])/max * 100;
        }

        double result = total / scores.length;

        return String.valueOf(result);
    }
}