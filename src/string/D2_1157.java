package string;

import java.io.*;

/**
 *  TODO Day-2
 *  문제
 *  알파벳 대소문자로 된 단어가 주어지면, 이 단어에서 가장 많이 사용된 알파벳이 무엇인지 알아내는 프로그램을 작성하시오. 단, 대문자와 소문자를 구분하지 않는다.
 *
 *  입력
 *  첫째 줄에 알파벳 대소문자로 이루어진 단어가 주어진다. 주어지는 단어의 길이는 1,000,000을 넘지 않는다.
 *
 *  출력
 *  첫째 줄에 이 단어에서 가장 많이 사용된 알파벳을 대문자로 출력한다. 단, 가장 많이 사용된 알파벳이 여러 개 존재하는 경우에는 ?를 출력한다.
 *
 *  예제 1. Mississipi / ?
 *  예제 2. zZa / Z
 *  예제 3. z / Z
 *  예제 4. baaa / A
 *
 */


public class D2_1157 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String a = "z"; // br.readLine();

            String result = tSolution(a);
            bw.write("" + result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  단어의 대소문자느 상관없으니 모두 대문자로 변환한다.
     *  단어의 알파벳 별로 개수를 확인한다.
     *  단어의 알파벳 별로 맥스 값을 확인한다.
     *  맥스값이 여러 개인지 한번 더 확인한다.
     *  어러 개면 ?를 반환한다.
     */
    private static String mySolution(String a) {

        a = a.toUpperCase();

        int[] count = getAlphabetCountV2(a);

        int maxCount = 0;
        for (int i = 0; i < 26; i++) {
            maxCount = Math.max(maxCount, count[i]);
        }

        String result = null;
        for (int i = 0; i < 26; i++) {
            if (count[i] == maxCount) {
                if (result != null) {
                    result = "?";
                    break;
                } else {
                    result = String.valueOf((char) (i + 'A'));
                }
            }
        }

        return String.valueOf(result);
    }


    /**
     *  3개의 버전으로 나눠짐
     *  1. 대소문자가를 조건문으로 분류하여 알파벳 개수를 count하는 방법
     *  2. String을 toUpperCase()로 모두 대무자로 변환하여 알파벳 개수를 count하는 방법.
     *  3. list로 모든 알파벳의 개수를 count하는게 아니라 그때그때 하나의 알파벳의 개수를 count하는 경우.
     *
     *  풀이 순서
     *  1. 알파벳의 개수를 count한다.
     *  2. 알파벳의 max count를 확인한다.
     *      - 현재 count가 max count보다 크면, max count 수정 및 max alphabet 수정 .
     *      - 현재 count가 max count와 같으면 max alphabet = '?';
     */
    private static String tSolution(String a) {

        int[] count = getAlphabetCountV1(a);

//        a = a.toUpperCase();
//        int[] count = getAlphabetCountV2(a);

        int maxCount = -1;
        char maxAlphabet = '?';

        for (int i = 0; i < 26; i++) {
            if (count[i] > maxCount) {
                maxCount = count[i];
                maxAlphabet = (char) ('A' + i);
            } else if (count[i] == maxCount){
                maxAlphabet = '?';
            }
        }


        /*
        a = a.toUpperCase();

        int maxCount = -1;
        char maxAlphabet = '?';

        for (char alp = 'A'; alp <= 'Z'; alp++)  {
            int count = getAlphabetCountV3(a, alp);
            if (count > maxCount) {
                maxCount = count;
                maxAlphabet = alp;
            } else if (count == maxCount){
                maxAlphabet = '?';
            }
        }
        */

        return String.valueOf(maxAlphabet);
    }

    private static int[] getAlphabetCountV1(String a) {
        int[] count = new int[26];
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) < 'a') {
                count[a.charAt(i) - 'A']++;
            } else {
                count[a.charAt(i) - 'a']++;
            }
        }

        return count;
    }
    private static int[] getAlphabetCountV2(String a) {
        int[] count = new int[26];
        for (int i = 0; i < a.length(); i++) {
            count[a.charAt(i) - 'A']++;
        }

        return count;
    }
    private static int getAlphabetCountV3(String a, char c) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == c) {
                count++;
            }
        }

        return count;
    }
}