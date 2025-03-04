package string;

import java.io.*;

/**
 *  대소문자 바꾸기
 */

public class D1_2744 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String alphabet = "WrongAnswer";

            StringBuilder sb = new StringBuilder();
            for (char c : alphabet.toCharArray()) {
                if ('A' <= c && c <= 'Z') {
                    sb.append((char)( c + 'a' - 'A'));
                } else {
                    sb.append((char)(c + 'A' - 'a'));
                }
            }
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
