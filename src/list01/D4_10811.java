package list01;

import java.io.*;

/**
 *  TODO Day-4 : 바구니 뒤집기
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1초	    256 MB	    96727	50637	44174	52.646%
 *
 *  문제
 *  도현이는 바구니를 총 N개 가지고 있고, 각각의 바구니에는 1번부터 N번까지 번호가 순서대로 적혀져 있다. 바구니는 일렬로 놓여져 있고, 가장 왼쪽 바구니를 1번째 바구니, 그 다음 바구니를 2번째 바구니, ..., 가장 오른쪽 바구니를 N번째 바구니라고 부른다.
 *  도현이는 앞으로 M번 바구니의 순서를 역순으로 만들려고 한다. 도현이는 한 번 순서를 역순으로 바꿀 때, 순서를 역순으로 만들 범위를 정하고, 그 범위에 들어있는 바구니의 순서를 역순으로 만든다.
 *  바구니의 순서를 어떻게 바꿀지 주어졌을 때, M번 바구니의 순서를 역순으로 만든 다음, 바구니에 적혀있는 번호를 가장 왼쪽 바구니부터 출력하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 N (1 ≤ N ≤ 100)과 M (1 ≤ M ≤ 100)이 주어진다.
 *  둘째 줄부터 M개의 줄에는 바구니의 순서를 역순으로 만드는 방법이 주어진다. 방법은 i j로 나타내고, 왼쪽으로부터 i번째 바구니부터 j번째 바구니의 순서를 역순으로 만든다는 뜻이다. (1 ≤ i ≤ j ≤ N)
 *  도현이는 입력으로 주어진 순서대로 바구니의 순서를 바꾼다.
 *
 *  출력
 *  모든 순서를 바꾼 다음에, 가장 왼쪽에 있는 바구니부터 바구니에 적혀있는 순서를 공백으로 구분해 출력한다.
 *
 *  예제 01   5 4     3 4 1 2 5
 *          1 2
 *          3 4
 *          1 4
 *          2 2
 */
public class D4_10811 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            String[] firstLine = br.readLine().split(" ");
//            String[] otherLines = new String[Integer.parseInt(firstLine[1])];

//            for (int i = 0; i < otherLines.length; i++) {
//                otherLines[i] = br.readLine();
//            }


            String[] firstLine = "5 4".split(" ");
            String[] otherLines = ("1 2\n" +
                    "3 4\n" +
                    "1 4\n" +
                    "2 2").split("\n");

            String result = mySolution(firstLine, otherLines);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  reverse() 함수를 구현할 수 있는지 묻는 문제라고 판단.
     *
     *  int 배열에 숫자를 채운다.
     *  otherLines (a,b)를 순회하면서 a~b까지의 value를 역순으로 정렬한다.
     *      역순으로 정렬하는 방법은 a과 b를 바꾼다.
     */
    private static String mySolution(String[] firstLineToken, String[] otherLines) {
        int length = Integer.parseInt(firstLineToken[0]);
        int[] list = new int[length];
        for (int i = 0; i < length; i++) {
            list[i] = i+1;
        }

        for (int i = 0; i < otherLines.length; i++) {
            String[] lines = otherLines[i].split(" ");
            int a = Integer.parseInt(lines[0]) -1;
            int b = Integer.parseInt(lines[1]) -1;

            while(a < b) {
                int temp = list[a];
                list[a++] = list[b];
                list[b--] = temp;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            sb.append(" ").append(list[i]);
        }

        return sb.toString().trim();
    }
}