package string;

import java.io.*;

/**
 *  TODO Day-5 : 알파벳 찾기
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	    정답 비율
 *  1초	    128 MB	    266424	134316	113537	    50.111%
 *
 *  문제
 *  문자열 S를 입력받은 후에, 각 문자를 R번 반복해 새 문자열 P를 만든 후 출력하는 프로그램을 작성하시오. 즉, 첫 번째 문자를 R번 반복하고, 두 번째 문자를 R번 반복하는 식으로 P를 만들면 된다. S에는 QR Code "alphanumeric" 문자만 들어있다.
 *  QR Code "alphanumeric" 문자는 0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ\$%*+-./: 이다.
 *
 *  입력
 *  첫째 줄에 테스트 케이스의 개수 T(1 ≤ T ≤ 1,000)가 주어진다. 각 테스트 케이스는 반복 횟수 R(1 ≤ R ≤ 8), 문자열 S가 공백으로 구분되어 주어진다. S의 길이는 적어도 1이며, 20글자를 넘지 않는다.
 *
 *  출력
 *  각 테스트 케이스에 대해 P를 출력한다.
 *
 *  예제 1.   2               AAABBBCCC
 *           3 ABC           /////HHHHHTTTTTPPPPP
 *           5 /HTP
 *
 */
public class D5_2675 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int length = Integer.parseInt(br.readLine());
//            String[] sources = new String[length];
//            for (int i = 0; i < length; i++) {
//                sources[i] = br.readLine();
//            }

            int length = 2;
            String[] sources = "3 ABC\n5 /HTP".split("\n");




            String result = mySolution(sources);

            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  1. sources를 순회하며, 반복 횟수(n)와 word을 분리한다.
     *  2. word의 각 알파벳을 순회하며, n번 반복하여 write한다.
     */
    private static String mySolution(String[] sources) {

        StringBuilder sb = new StringBuilder();
        for (String source : sources) {
            String[] split = source.split(" ");
            int n = Integer.parseInt(split[0]);
            String word = split[1];

            for (int i = 0; i < word.length(); i++) {
                String alphabet = String.valueOf(word.charAt(i)).repeat(n);
                sb.append(alphabet);
            }
            sb.append("\n");
        }


        return sb.toString();
    }
}