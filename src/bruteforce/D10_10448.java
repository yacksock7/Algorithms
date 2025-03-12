package bruteforce;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  TODO Day-10 : 유레카 이론
 *
 *  시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
 *  1 초	256 MB	17534	10236	7948	57.603%
 *
 *  문제
 *  삼각수 Tn(n ≥ 1)는 [그림]에서와 같이 기하학적으로 일정한 모양의 규칙을 갖는 점들의 모음으로 표현될 수 있다.
 *
 *  [그림]
 *
 *  자연수 n에 대해 n ≥ 1의 삼각수 Tn는 명백한 공식이 있다.
 *      Tn = 1 + 2 + 3 + ... + n = n(n+1)/2
 *
 *  1796년, 가우스는 모든 자연수가 최대 3개의 삼각수의 합으로 표현될 수 있다고 증명하였다. 예를 들어,
 *      4 = T1 + T2
 *      5 = T1 + T1 + T2
 *      6 = T2 + T2 or 6 = T3
 *      10 = T1 + T2 + T3 or 10 = T4
 *  이 결과는 증명을 기념하기 위해 그의 다이어리에 “Eureka! num = Δ + Δ + Δ” 라고 적은것에서 유레카 이론으로 알려졌다. 꿍은 몇몇 자연수가 정확히 3개의 삼각수의 합으로 표현될 수 있는지 궁금해졌다. 위의 예시에서, 5와 10은 정확히 3개의 삼각수의 합으로 표현될 수 있지만 4와 6은 그렇지 않다.
 *  자연수가 주어졌을 때, 그 정수가 정확히 3개의 삼각수의 합으로 표현될 수 있는지 없는지를 판단해주는 프로그램을 만들어라. 단, 3개의 삼각수가 모두 달라야 할 필요는 없다.
 *
 *  입력
 *  프로그램은 표준입력을 사용한다. 테스트케이스의 개수는 입력의 첫 번째 줄에 주어진다. 각 테스트케이스는 한 줄에 자연수 K (3 ≤ K ≤ 1,000)가 하나씩 포함되어있는 T개의 라인으로 구성되어있다.
 *
 *  출력
 *  프로그램은 표준출력을 사용한다. 각 테스트케이스에대해 정확히 한 라인을 출력한다. 만약 K가 정확히 3개의 삼각수의 합으로 표현될수 있다면 1을, 그렇지 않다면 0을 출력한다.
 *
 *  예제 1.  3        1
 *          10       0
 *          20       1
 *          1000
 *
 */
public class D10_10448 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int length = Integer.parseInt("5");
            int[] sources = Arrays.stream(("4\n" +
                    "5\n" +
                    "6\n" +
                    "10\n" +
                    "1000").split("\n")).mapToInt(Integer::parseInt).toArray();

            int[] sumNumbers = preprocess();
//            int length = Integer.parseInt(br.readLine());
            for (int i = 0; i < length; i++) {
//                int source = Integer.parseInt(br.readLine());
                int source = sources[i];
                bw.write(String.valueOf(sumNumbers[source]));
                bw.write("\n");
            }

//            for (int i = 0; i < source.length; i++) {
//                boolean result = mySolution(source[i]);
//                if (result) {
//                    bw.write("1\n");
//                } else {
//                    bw.write("0\n");
//                }
//            }

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  1000이하인 삼각수를 배열에 담는다.
     *  삼각수 배열을 순회하며, 3수의 합이 target number인 경우를 찾는다. 
     *  찾으면 true, 없으면 false를 리턴한다.
     */
    private static boolean mySolution(int number) {
        List<Integer> triangular = new ArrayList<>();
        triangular.add(1);
        for (int i = 1; (i * (i+1) / 2) <= 1000; i++) {
            int value = triangular.get(i-1) + (i+1);
            triangular.add(value);
        }
        System.out.println("triangular = " + triangular);


        for (int i1 = 0; i1 < triangular.size(); i1++) {
            for (int i2 = i1; i2 < triangular.size() ; i2++) {
                for (int i3 = i2; i3 < triangular.size() ; i3++) {
                    if (triangular.get(i1) + triangular.get(i2) + triangular.get(i3) == number) {
                        return true;
                    } else if (triangular.get(i1) + triangular.get(i2) + triangular.get(i3) > number) {
                        break;
                    }
                }
            }
        }

        return false;
    }

    /**
     *  1. 문제에 주어지는 target number의 개수만큼 삼각수 배열을 세팅한다.
     *      -> 별도로 분리해서 늘 결과가 같은 삼각수는 1번만 구해서 시간복잡도를 줄여보자.
     *
     *  2. 1 ~ 1000까지 삼각수의 경우의 수를 모두 저장하여, target number가 들어올때마다 확인하던 행위를 줄여보자.
     */
    private static int[] preprocess() {
        int[] triangleNumbers = new int[50];
        for (int i = 1; i < triangleNumbers.length; i++) {
            triangleNumbers[i] = i * (i+1) / 2;
        }


        int[] sumNumbers = new int[1000];
        for (int i1 = 1; i1 < triangleNumbers.length; i1++) {
            for (int i2 = i1; i2 < triangleNumbers.length ; i2++) {
                for (int i3 = i2; i3 < triangleNumbers.length ; i3++) {
                    int sumNumber = triangleNumbers[i1] + triangleNumbers[i2] + triangleNumbers[i3];
                    if (sumNumber <= 1000) {
                        sumNumbers[sumNumber] = 1;
                    } else if (sumNumber > 1000) {
                        break;
                    }
                }
            }
        }

        return sumNumbers;
    }
}