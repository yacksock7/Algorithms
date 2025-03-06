package list01;

import java.io.*;

/**
 *  TODO Day-4 : 공 바꾸기
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1 초	    256 MB	    89790	56358	50173	63.131%
 *  문제
 *  도현이는 바구니를 총 N개 가지고 있고, 각각의 바구니에는 1번부터 N번까지 번호가 매겨져 있다. 바구니에는 공이 1개씩 들어있고, 처음에는 바구니에 적혀있는 번호와 같은 번호가 적힌 공이 들어있다.
 *  도현이는 앞으로 M번 공을 바꾸려고 한다. 도현이는 공을 바꿀 바구니 2개를 선택하고, 두 바구니에 들어있는 공을 서로 교환한다.
 *  공을 어떻게 바꿀지가 주어졌을 때, M번 공을 바꾼 이후에 각 바구니에 어떤 공이 들어있는지 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 N (1 ≤ N ≤ 100)과 M (1 ≤ M ≤ 100)이 주어진다.
 *  둘째 줄부터 M개의 줄에 걸쳐서 공을 교환할 방법이 주어진다. 각 방법은 두 정수 i j로 이루어져 있으며, i번 바구니와 j번 바구니에 들어있는 공을 교환한다는 뜻이다. (1 ≤ i ≤ j ≤ N)
 *  도현이는 입력으로 주어진 순서대로 공을 교환한다.
 *
 *  출력
 *  1번 바구니부터 N번 바구니에 들어있는 공의 번호를 공백으로 구분해 출력한다.
 *
 *  예제 1.   5 4        3 1 4 2 5
 *          1 2
 *          3 4
 *          1 4
 *          2 2
 */
public class D4_10813 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String input = "5 4"; // br.readLine();
            String[] source = (
                    "1 2\n" +
                    "3 4\n" +
                    "1 4\n" +
                    "2 2").split("\n"); // br.readLine();


//            String input = br.readLine();
//            int length = Integer.parseInt(input.split(" ")[1]);
//
//            String[] source = new String[length];
//            for (int i = 0; i < length; i++) {
//                source[i] = br.readLine();
//            }

            String result = mySolution(input, source);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static String mySolution(String input, String[] sources) {

        String[] inputs = input.split(" ");
        int[] baskets = new int[Integer.parseInt(inputs[0])];
        for (int i = 0; i < baskets.length; i++) {
            baskets[i] = i+1;
        }

        for (int i = 0; i < sources.length; i++) {
            String[] source = sources[i].split(" ");
            int start = Integer.parseInt(source[0]);
            int end = Integer.parseInt(source[1]);

            int temp = baskets[start-1];
            baskets[start-1] = baskets[end-1];
            baskets[end-1] = temp;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < baskets.length; i++) {
            sb.append(baskets[i] + " ");
        }

        return sb.toString().trim();
    }
}