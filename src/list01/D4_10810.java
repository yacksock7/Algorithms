package list01;

import java.io.*;

/**
 *  TODO Day-4 : 공 넣기. solution02도 오류.... 뭐야....
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	    정답 비율
 *  1 초	    256 MB	    113819	58939	51418	    52.080%
 *
 *  문제
 *  도현이는 바구니를 총 N개 가지고 있고, 각각의 바구니에는 1번부터 N번까지 번호가 매겨져 있다. 또, 1번부터 N번까지 번호가 적혀있는 공을 매우 많이 가지고 있다. 가장 처음 바구니에는 공이 들어있지 않으며, 바구니에는 공을 1개만 넣을 수 있다.
 *
 *  도현이는 앞으로 M번 공을 넣으려고 한다. 도현이는 한 번 공을 넣을 때, 공을 넣을 바구니 범위를 정하고, 정한 바구니에 모두 같은 번호가 적혀있는 공을 넣는다. 만약, 바구니에 공이 이미 있는 경우에는 들어있는 공을 빼고, 새로 공을 넣는다. 공을 넣을 바구니는 연속되어 있어야 한다.
 *
 *  공을 어떻게 넣을지가 주어졌을 때, M번 공을 넣은 이후에 각 바구니에 어떤 공이 들어 있는지 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 N (1 ≤ N ≤ 100)과 M (1 ≤ M ≤ 100)이 주어진다.
 *
 *  둘째 줄부터 M개의 줄에 걸쳐서 공을 넣는 방법이 주어진다. 각 방법은 세 정수 i j k로 이루어져 있으며, i번 바구니부터 j번 바구니까지에 k번 번호가 적혀져 있는 공을 넣는다는 뜻이다. 예를 들어, 2 5 6은 2번 바구니부터 5번 바구니까지에 6번 공을 넣는다는 뜻이다. (1 ≤ i ≤ j ≤ N, 1 ≤ k ≤ N)
 *
 *  도현이는 입력으로 주어진 순서대로 공을 넣는다.
 *
 *  출력
 *  1번 바구니부터 N번 바구니에 들어있는 공의 번호를 공백으로 구분해 출력한다. 공이 들어있지 않은 바구니는 0을 출력한다.
 *
 *  예제 1.   5 4         1 2 1 1 0
 *          1 2 3
 *          3 4 4
 *          1 4 1
 *          2 2 2
 */
public class D4_10810 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String input = "10 2"; // br.readLine();
            String[] source = ("1 5 1\n" +
                    "6 10 2\n").split("\n"); // br.readLine();


//            String input = br.readLine();
//            int length = Integer.parseInt(input.split(" ")[1]);
//
//            String[] source = new String[length];
//            for (int i = 0; i < length; i++) {
//                source[i] = br.readLine();
//            }

            String result = mySolution01(input, source);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  방법 01
     *  바구니 레인지와 동일한 Length의 배열 생성 -> 초기값은 0
     *  source(i, j, k)의 길이만큼 반복하여, i부터 j까지 k로 수정
     *  
     *  방법 02
     *  바구니 레인지와 동일한 Length의 String 생성 -> 초기값은 0
     *  source(i, j, k)의 길이만큼 반복하여, i부터 j까지 k로 수정 -> substring() 활용
     */
    private static String mySolution01(String input, String[] sources) {

        String[] inputs = input.split(" ");
        
        int[] baskets = new int[Integer.parseInt(inputs[0])];
        System.out.println("baskets.length = " + baskets.length);
        for (int i = 0; i < sources.length; i++) {
            String[] source = sources[i].split(" ");
            int start = Integer.parseInt(source[0]);
            int end = Integer.parseInt(source[1]);
            int value = Integer.parseInt(source[2]);
            for (int j = start-1; j < end ; j++) {
                baskets[j] = value;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < baskets.length; i++) {
            sb.append(baskets[i] + " ");
        }

        return sb.toString().trim();
    }


    /**
     *  방법 02
     *  바구니 레인지와 동일한 Length의 String 생성 -> 초기값은 0
     *  source(i, j, k)의 길이만큼 반복하여, i부터 j까지 k로 수정 -> substring() 활용
     */
    private static String mySolution02(String input, String[] sources) {

        String[] inputs = input.split(" ");
        StringBuilder baskets = new StringBuilder("0".repeat(Integer.parseInt(inputs[0])));

        for (int i = 0; i < sources.length; i++) {
            String[] source = sources[i].split(" ");
            int start = Integer.parseInt(source[0]);
            int end = Integer.parseInt(source[1]);
            int value = Integer.parseInt(source[2]);
            baskets.replace(start-1, end, String.valueOf(value).repeat(end-start+1));
        }

        return baskets.toString();
    }
}