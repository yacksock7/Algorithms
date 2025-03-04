package string;

import java.io.*;

/**
 *  TODO Day-2
 *  문제
 *  세준이는 영어로만 이루어진 어떤 문서를 검색하는 함수를 만들려고 한다.
 *  이 함수는 어떤 단어가 총 몇 번 등장하는지 세려고 한다.
 *  그러나, 세준이의 함수는 중복되어 세는 것은 빼고 세야 한다.
 *  예를 들어, 문서가 abababa이고, 그리고 찾으려는 단어가 ababa라면,
 *  세준이의 이 함수는 이 단어를 0번부터 찾을 수 있고, 2번부터도 찾을 수 있다.
 *  그러나 동시에 셀 수는 없다.
 *
 *  세준이는 문서와 검색하려는 단어가 주어졌을 때, 그 단어가 최대 몇 번 중복되지 않게 등장하는지 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 문서가 주어진다. 문서의 길이는 최대 2500이다. 둘째 줄에 검색하고 싶은 단어가 주어진다. 이 길이는 최대 50이다. 문서와 단어는 알파벳 소문자와 공백으로 이루어져 있다.
 *
 *  출력
 *  첫째 줄에 중복되지 않게 최대 몇 번 등장하는지 출력한다.
 *
 *  예제 1. ababababa / aba / 2
 *  예제 2. a a a a a / a a / 2
 *  예제 3. ababababa / ababa / 1
 *  예제 4. aaaaaaa / aa / 3
 *
 */


public class _1543 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String doc = "ababababa"; // br.readLine();
            String word = "ababa"; // br.readLine();

            int result = tSolutionV2(doc, word);
            bw.write("" + result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  a의 길이 = i - word.length()만큼 반복하며,
     *  word와 일치하는지 확인한다.
     *  word와 일치하면 i에 word.length()-1 만큼 더해준다. (for문에 i++이 존재헤서 -1을 해준다.)
     *
     *  a의 길이 = i - word.length() 만큼 반복하며 (단어와 길이 조건을 추가하여 성능고려)
     *  word와 일치하면 i에 word.length()-1 (동시에 카운트 할 수 없다는 조건 고려)
     */
    private static int mySolution(String doc, String word) {

        int result = 0;
        for (int i = 0; i <= doc.length()-word.length(); i++) {
            String target = doc.substring(i, i + word.length());
            if (target.equals(word)) {
                result++;
                i += word.length()-1;
            }
        }

        return result;
    }


    /**
     *  1. 문서의 첫 글자부터 순회한다.
     *  2. 문서의 지금 글자부터 주어진 단어와 한글자씩 비교한다.
     *  3.
     *      3-1. 단어와 완전히 일치하면 (i + word.length())개수를 올린다. 해당 단어가 등장한 이후부터 2를 반복한다.
     *      3-2. 단어와 매치되지 않았다면 다음 글자(i++)에서 2를 반복한다.
     *
     *
     */
    private static int tSolutionV0(String doc, String word) {

        int count = 0;
        for (int i = 0; i < doc.length(); i++) {
            boolean isMatch = true;
            for (int j = 0; j < word.length(); j++) {
                // >> i가 doc.length() - 1이고 j가 1인 경우, index bound 오류 발생. doc.length()만큼 반복하는 반복문에 조건을 doc.length() - word.length()라고 해도 됨.
                // index를 다룰떼는 범위 체크를 함께 해야한다. 몇가지 케이스를 한번씩 대입해보아야한다.
                if ( (i + j) >= doc.length()
                        // doc의 i부터 word.length()까지 한글자씩 ++하며 비교.
                        || doc.charAt(i+j) != word.charAt(j)) {
                    // 한글자라도 다른 경우의 케이스
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                count++;
                i += word.length() -1;
                // 중복되지 않는 것을 찾아야해서 i += work.length()
                // word.length() -1은 for (int i = 0; i < doc.length(); i++) 에 i++이 있기 때문.

            }
        }
        return count;
    }

    // String.indexOf() 활용.
    private static int tSolutionV1(String doc, String word) {

        int startIndex = 0;
        int count = 0;

        while(true) {
            int findIndex = doc.indexOf(word, startIndex);
            if (findIndex < 0) {
                break;
            }
            startIndex = findIndex+word.length();
            count++;
            return count;
        }

        return count;
    }

    // String.replace() 활용.
    private static int tSolutionV2(String doc, String word) {
        String target = doc.replace(word, "");
        int count = (doc.length() - target.length()) / word.length();

        return count;
    }
}