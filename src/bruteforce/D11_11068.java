package bruteforce;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  TODO Day-11 : 회문인 수
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1 초	    256 MB	    5007	2542	2108	54.232%
 *
 *  문제
 *  어떤 수를 왼쪽부터 읽어도, 오른쪽부터 읽어도 같을 때 이 수를 회문인 수라고 한다. 예를 들어, 747은 회문인 수이다. 255도 회문인 수인데, 16진수로 표현하면 FF이기 때문이다. 양의 정수를 입력받았을 때, 이 수가 어떤 B진법 (2 ≤ B ≤ 64)으로 표현하면 회문이 되는 경우가 있는지 알려주는 프로그램을 작성하시오. B진법이란, 한 자리에서 수를 표현할 때 쓸 수 있는 수의 가짓수가 B라는 뜻이다. 예를 들어, 십진법에서 B는 10이다.
 *
 *  입력
 *  입력 데이터는 표준입력을 사용한다. 입력은 T개의 테스트 데이터로 구성된다. 입력의 첫 번째 줄에는 테스트 데이터의 수를 나타내는 정수 T가 주어진다. 각 테스트 데이터는 64 이상 1,000,000 이하인 하나의 정수로 주어진다.
 *
 *  출력
 *  출력은 표준출력을 사용한다. 하나의 테스트 데이터에 대한 답을 하나의 줄에 출력한다. 각 테스트 데이터에 대해, 주어진 수가 어떤 B진법 (2 ≤ B ≤ 64)으로 표현하여 회문이 될 수 있다면 1을, 그렇지 않다면 0을 출력한다.
 *
 *  예제 1.  3               1
 *          747             1
 *          255             0
 *          946734
 *
 */
public class D11_11068 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int length = Integer.parseInt(br.readLine());
//            int[] sources = new int[length];
//            for (int i = 0; i < sources.length; i++) {
//                sources[i] = Integer.parseInt(br.readLine());
//            }
            int length = Integer.parseInt("3");
            int[] sources = Arrays.stream(("747\n" +
                    "255\n" +
                    "946734").split("\n"))
                    .mapToInt(Integer::parseInt)
                    .toArray();


            String result = mySolution(sources);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     */
    private static String mySolution(int[] sources) {


        StringBuilder result = new StringBuilder();
        for (int source : sources) {

            boolean isIt = false;
            for (int i = 2; i <= 64 ; i++) {
                List<String> list = convert(source, i);
                isIt = validate(list);
                if (isIt) {
                    break;
                }
            }

            result.append(isIt ? 1 : 0).append("\n");
        }

        return result.toString();
    }

    private static List<String> convert(int n, int b) {
        List<String> result = new ArrayList<>();
        if (n == 0) result.add("0");
        while (n > 0) {
            result.add(String.valueOf(n%b));
            n /= b;
        }

        return result;
    }

    private static boolean validate(List<String> list) {

        int a = 0;
        int b = list.size()-1;
        while (a<b) {
            if (!list.get(a++).equals(list.get(b--))) return false;
        }

        return true;
    }
}