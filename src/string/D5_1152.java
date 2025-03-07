package string;

import java.io.*;

/**
 *  TODO Day-5 : 단어의 개수
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  2초	    128 MB	    443378	146397	117458	33.345%
 *
 *  문제
 *  영어 대소문자와 공백으로 이루어진 문자열이 주어진다. 이 문자열에는 몇 개의 단어가 있을까? 이를 구하는 프로그램을 작성하시오. 단, 한 단어가 여러 번 등장하면 등장한 횟수만큼 모두 세어야 한다.
 *
 *  입력
 *  첫 줄에 영어 대소문자와 공백으로 이루어진 문자열이 주어진다. 이 문자열의 길이는 1,000,000을 넘지 않는다. 단어는 공백 한 개로 구분되며, 공백이 연속해서 나오는 경우는 없다. 또한 문자열은 공백으로 시작하거나 끝날 수 있다.
 *
 *  출력
 *  첫째 줄에 단어의 개수를 출력한다.
 *
 *  예제 1. The Curious Case of Benjamin Button  /  6
 *  예제 2. The first character is a blank qq / 6
 *  예제 3. The last character is a blank / 6
 *
 */
public class D5_1152 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String source = ""; // br.readLine();

            String result = mySolution(source);

            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  *** 문자열은 공백으로 시작해서 공백으로 끝날 수 있다.
     *  1. 문자열의 양끝 공백을 trim()으로 제거한다.
     *  2. 문자열을 공백 기준으로 split() 한다.
     *  3. length = 단어의 개수
     *  4. 문자열에 공백만 들어왔을 경우 고려해서 source.trim()이 ""인 경우, length를 1이 아닌 0으로 수정해야한다.
     */
    private static String mySolution(String source) {
        String target = source.trim();
        int length = target.split(" ").length;
        if (source.equals("")) {
            length = 0;
        }
        return String.valueOf(length);
    }
}