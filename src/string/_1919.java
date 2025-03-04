import java.io.*;

/**
 *  TODO Day-2
 *  두 영어 단어가 철자의 순서를 뒤바꾸어 같아질 수 있을 때, 그러한 두 단어를 서로 애너그램 관계에 있다고 한다. 예를 들면 occurs 라는 영어 단어와 succor 는 서로 애너그램 관계에 있는데, occurs의 각 문자들의 순서를 잘 바꾸면 succor이 되기 때문이다.
 *  한 편, dared와 bread는 서로 애너그램 관계에 있지 않다. 하지만 dared에서 맨 앞의 d를 제거하고, bread에서 제일 앞의 b를 제거하면, ared와 read라는 서로 애너그램 관계에 있는 단어가 남게 된다.
 *  두 개의 영어 단어가 주어졌을 때, 두 단어가 서로 애너그램 관계에 있도록 만들기 위해서 제거해야 하는 최소 개수의 문자 수를 구하는 프로그램을 작성하시오. 문자를 제거할 때에는 아무 위치에 있는 문자든지 제거할 수 있다.
 *
 *  sample => aabbcc xxyybb
 *  result => 8
 */


public class _1919 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String a = "aabbcc"; // br.readLine();
            String b = "xxyybb"; // br.readLine();

            int result = tSolution(a, b);

            bw.write("" + result);
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  indexOf는 내부에서 for 실행한다.
     *  즉, 결국 indexOf를 사용하면 이중 for문을 사용하는 것이다.
     */
    private static int mySolution(String input1, String input2) {

        StringBuilder sb = new StringBuilder();
        for (String s : input1.split("")) {
            if (input2.contains(s)) {
                input2 = input2.substring(0, input2.indexOf(s)) + input2.substring(input2.indexOf(s)+1);
            } else {
                sb.append(s);
            }
        }
        sb.append(input2);

        return sb.length();
    }


    /**
     *  a의 알파벳별 개수를 구한다
     *  b의 알파벳별 개수를 구한다.
     *  a와 b의 알파벳별 개수의 차를 구한다.
     *  a와 b의 알파벳별 개수의 차를 모두 더한다.
     */
    private static int tSolution(String a, String b) {

        int[] countA = getAlphabetCount(a);
        int[] countB = getAlphabetCount(b);

        int result = 0;
        for (int i = 0; i < 26; i++) {
            // 비교군의 대소를 비교한다.
            /*
            if (countA[i] > countB[i]) {
                result += countA[i] - countB[i];
            } else {
                result +=  countB[i] - countA[i];
            }
            */

            result += Math.abs(countA[i] - countB[i]);
        }

        return result;
    }

    private static int[] getAlphabetCount(String a) {
        int[] countA = new int[26];
        for (int i = 0; i < a.length(); i++) {

            // 알파뱃의 index를 맞춰준다 [소문자 - a]
            countA[a.charAt(i) - 'a']++;
        }
        return countA;
    }
}