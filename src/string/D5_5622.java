package string;

import java.io.*;

/**
 *  TODO Day-5 : 다이얼
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1초	    128 MB	    155564	91146	79052	58.406%
 *
 *  문제
 *  상근이의 할머니는 아래 그림과 같이 오래된 다이얼 전화기를 사용한다.
 *
 *  전화를 걸고 싶은 번호가 있다면, 숫자를 하나를 누른 다음에 금속 핀이 있는 곳 까지 시계방향으로 돌려야 한다. 숫자를 하나 누르면 다이얼이 처음 위치로 돌아가고, 다음 숫자를 누르려면 다이얼을 처음 위치에서 다시 돌려야 한다.
 *  숫자 1을 걸려면 총 2초가 필요하다. 1보다 큰 수를 거는데 걸리는 시간은 이보다 더 걸리며, 한 칸 옆에 있는 숫자를 걸기 위해선 1초씩 더 걸린다.
 *  상근이의 할머니는 전화 번호를 각 숫자에 해당하는 문자로 외운다. 즉, 어떤 단어를 걸 때, 각 알파벳에 해당하는 숫자를 걸면 된다. 예를 들어, UNUCIC는 868242와 같다.
 *  할머니가 외운 단어가 주어졌을 때, 이 전화를 걸기 위해서 필요한 최소 시간을 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 알파벳 대문자로 이루어진 단어가 주어진다. 단어의 길이는 2보다 크거나 같고, 15보다 작거나 같다.
 *
 *  출력
 *  첫째 줄에 다이얼을 걸기 위해서 필요한 최소 시간을 출력한다.
 *
 *  예제 1. WA / 13
 *  예제 2. UNUCIC / 36
 *
 */
public class D5_5622 {



    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String source = "WA"; // br.readLine();

            String result = mySolution(source);

            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  source의 각 문자를 숫자로 변환한다.
     *  숫자 + 1을 해서 다이얼조작 시간을 구한다.
     *  다이얼 조작 시간을 모두 더하여 리턴한다.
     */
    private static String mySolution(String source) {
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "22233344455566677778889999";

        int result = 0;
        String[] sources = source.split("");
        for (String s : sources) {
            int idx = alphabets.indexOf(s);
            int num = Integer.parseInt(String.valueOf(numbers.charAt(idx)));
            result += (num+1);
        }

        return String.valueOf(result);
    }
}