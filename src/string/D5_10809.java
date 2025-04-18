package string;

import java.io.*;

/**
 *  TODO Day-5 : 알파벳 찾기
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1초	    256 MB	    256684	138377	113703	53.531%
 *
 *  문제
 *  알파벳 소문자로만 이루어진 단어 S가 주어진다. 각각의 알파벳에 대해서, 단어에 포함되어 있는 경우에는 처음 등장하는 위치를, 포함되어 있지 않은 경우에는 -1을 출력하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 단어 S가 주어진다. 단어의 길이는 100을 넘지 않으며, 알파벳 소문자로만 이루어져 있다.
 *
 *  출력
 *  각각의 알파벳에 대해서, a가 처음 등장하는 위치, b가 처음 등장하는 위치, ... z가 처음 등장하는 위치를 공백으로 구분해서 출력한다.
 *  만약, 어떤 알파벳이 단어에 포함되어 있지 않다면 -1을 출력한다. 단어의 첫 번째 글자는 0번째 위치이고, 두 번째 글자는 1번째 위치이다.
 *
 *  예제 1.   baekjoon  /  1 0 -1 -1 2 -1 -1 -1 -1 4 3 -1 -1 7 5 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
 *
 */
public class D5_10809 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String source = "baekjoon"; // br.readLine();

            String result = mySolution(source);

            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  1. new int[26] -> -1로 초기화
     *  2. sources의 각 글자를 순회한다.
     *  3. 순회하며, 각 알파벳이 최초로 등장하는 index를 int[{알파벳 순서}]에 저장
     */
    private static String mySolution(String sources) {

        int[] list = new int[26];
        for (int i = 0; i < list.length; i++) {
            list[i] = -1;
        }

        for (int i = 0; i < sources.length(); i++) {
            int item = sources.charAt(i) - 'a';

            if (list[item] == -1) {
                list[item] = i;
            };
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            sb.append(" ").append(list[i]);
        }

        return sb.toString().trim();
    }
}