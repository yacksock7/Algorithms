package list01;

import java.io.*;

/**
 *  TODO Day-8 : 성 지키기
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  2초	    128 MB	    20505	8701	7308	44.374%
 *
 *  문제
 *  영식이는 직사각형 모양의 성을 가지고 있다. 성의 1층은 몇 명의 경비원에 의해서 보호되고 있다. 영식이는 모든 행과 모든 열에 한 명 이상의 경비원이 있으면 좋겠다고 생각했다.
 *  성의 크기와 경비원이 어디있는지 주어졌을 때, 몇 명의 경비원을 최소로 추가해야 영식이를 만족시키는지 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 성의 세로 크기 N과 가로 크기 M이 주어진다. N과 M은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에는 성의 상태가 주어진다. 성의 상태는 .은 빈칸, X는 경비원이 있는 칸이다.
 *
 *  출력
 *  첫째 줄에 추가해야 하는 경비원의 최솟값을 출력한다.
 *
 *  예제 1.  4 4          4
 *          ....
 *          ....
 *          ....
 *          ....
 *
 *  예제 2.  3 5          0
 *          XX...
 *          .XX..
 *          ...XX
 *
 *  예제 3.  5 8          3
 *          ....XXXX
 *          ........
 *          XX.X.XX.
 *          ........
 *          ........
 */
public class D8_1236 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            String firstLine = "5 8";// br.readLine();
//
//            String[] source = ("..X...X.\n" +
//                    "........\n" +
//                    "........\n" +
//                    "..X...X.\n" +
//                    "........").split("\n");// br.readLine();


            String firstLine = br.readLine();
            int length = Integer.parseInt(firstLine.split(" ")[0]);

            String[] source = new String[length];
            for (int i = 0; i < length; i++) {
                source[i] = br.readLine();
            }

            String result = mySolution(firstLine, source);

            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  - source를 순회하며, String[] -> String[][]로 만들어
     *
     *  1. x와 y를 돌면서 경비원이 한명도 없는 곳을 찾는다.
     *      x와 y에서 경비원이 있는 곳을 찾는다.
     *
     *      x에서 찾는 방법
     *          String contains() 함수 활용. -> contains()가 -> false 이면 +1
     *
     *      y에서 찾는 방법
     *          y.length 길이의 int 배열을 하나 만들자.
     *          그리고 경비원이 있으면 1로 바꿔주자.
     *          int value가 0인 index에 +1;
     *
     *  2. x와 y 중 더 큰 숫자가 답이다.
     */
    private static String mySolution(String firstLine, String[] source) {


        int yCount = 0;
        int xCount = 0;

        int[] xState = new int[Integer.parseInt(firstLine.split(" ")[1])];
        for (int i = 0; i < source.length; i++) {
            if (!source[i].contains("X")) {
                yCount++;
            } else {
                int xIndex = source[i].indexOf("X");
                while (xIndex > -1) {
                    xState[xIndex] = 1;
                    source[i] = source[i].replaceFirst("X", " ");
                    xIndex = source[i].indexOf("X");
                }
            }
        }

        for (int value : xState) {
            if (value == 0){
                xCount++;
            }
        }

        int result = Math.max(xCount, yCount);


        return String.valueOf(result);
    }

}