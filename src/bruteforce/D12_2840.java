package bruteforce;

import java.io.*;
import java.util.Arrays;

/**
 *  TODO Day-12 : 행운의 바퀴
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1 초	    128 MB	    9804	2575	1821	24.174%
 *
 *  문제
 *  상덕이는 최근에 행운의 바퀴를 구매했다. 상덕이는 바퀴의 각 칸에 알파벳 대문자를 아래 그림과 같이 적었다.
 *
 *  바퀴에 같은 글자는 두 번 이상 등장하지 않는다. 또, 바퀴는 시계방향으로만 돌아간다. 바퀴 옆에는 화살표가 있는데, 이 화살표는 항상 한 곳을 가리키고 있으며, 돌아가는 동안 가리키는 글자는 바뀌게 된다. 위의 그림에서는 H를 가리키고 있다.
 *  상덕이는 바퀴를 연속해서 K번 돌릴 것이다. 매번 바퀴를 돌릴 때 마다, 상덕이는 화살표가 가리키는 글자가 변하는 횟수와 어떤 글자에서 회전을 멈추었는지를 종이에 적는다.
 *  희원이는 상덕이가 적어놓은 종이를 발견했다. 그 종이를 바탕으로 상덕이가 바퀴에 적은 알파벳을 알아내려고 한다.
 *  상덕이가 종이에 적어놓은 내용과 바퀴의 칸의 수가 주어졌을 때, 바퀴에 적어놓은 알파벳을 알아내는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 바퀴의 칸의 수 N과 상덕이가 바퀴를 돌리는 횟수 K가 주어진다. (2 ≤ N ≤ 25, 1 ≤ K ≤ 100)
 *  다음 줄부터 K줄에는 바퀴를 회전시켰을 때 화살표가 가리키는 글자가 몇 번 바뀌었는지를 나타내는 S와 회전을 멈추었을 때 가리키던 글자가 주어진다. (1 ≤ S ≤ 100)
 *
 *  출력
 *  첫째 줄에 마지막 회전에서 화살표가 가리키는 문자부터 시계방향으로 바퀴에 적어놓은 알파벳을 출력한다. 이때, 어떤 글자인지 결정하지 못하는 칸은 '?'를 출력한다. 만약, 상덕이가 적어놓은 종이에 해당하는 행운의 바퀴가 없다면 "!"를 출력한다.
 *
 *  예제 1.   3 3         !
 *           1 A
 *           2 B
 *           3 C
 *
 *  예제 2.   5 6         B?A?C
 *           1 A
 *           2 B
 *           5 B
 *           1 C
 *           2 A
 *           2 B
 *
 *  예제 2.   8 8         HONITAVR
 *           4 V
 *           3 I
 *           7 T
 *           7 A
 *           6 R
 *           5 N
 *           1 O
 *           9 H
 *
 */
public class D12_2840 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int[] firstLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//            String[] sources = new String[firstLine[1]];
//            for (int i = 0; i < sources.length; i++) {
//                sources[i] = br.readLine();
//            }

            int[] firstLine = Arrays.stream("5 6".split(" ")).mapToInt(Integer::parseInt).toArray();
            String[] sources = ("1 A\n" +
                    "2 B\n" +
                    "5 B\n" +
                    "1 C\n" +
                    "2 A\n" +
                    "2 B").split("\n");

            String result = mySolution(firstLine, sources);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  n칸 알파벳, k번 회전
     *
     *  String[] board = new String[n];
     *
     *  for (int i=0 ; i < k; i++)
     *  int count = Integer.parsint(source.split(" ")[0]);
     *  String str = source.split(" ")[1];
     *
     *  현재 좌표가 필요해. -> int point = 0;
     *
     *      if board[point] != null && !board[point].equals(str) -> return "!";
     *      else
     *          board[point] = str;
     *          point = (point + count) % n;
     *
     *      return result;
     *
     *  회전판에 같은 알파벳이 2개이상 존재할 수 없다는 제약조건 (중복제거. 대문자 알파벳 중복확인.)
     *      int[] duplicated = new int[26];
     *      => 이번칸에 새로 알파벳이 쓰여진다. 그러나 알파벳은 이미 사용된 적이 있다.
     *
     *
     *  추가가 될껀데 중복을 제거해야해
     *      * board[point] 입력되는 경우
     *      1. board[point]가 null일 경우
     *      2. board[point]가 str과 같을 경우
     *  그럼 제약 조건은 1번 케이스에서 다른 칸에 str이 사용된 경우.
     */
    private static String mySolution(int[] firstLine, String[] sources) {
        int n = firstLine[0];
        int k = firstLine[1];

        int point = 0;
        String[] board = new String[n];
        int[] duplicated = new int[26];
        for (int i = sources.length-1; i >= 0 ; i--) {
            String[] source = sources[i].split(" ");
            int count = Integer.parseInt(source[0]);
            char str = source[1].charAt(0);

            if (board[point] != null && !board[point].equals(String.valueOf(str))) {
                return "!";
            } else {

                if (board[point] == null && duplicated[str-'A'] != 0) {
                    return "!";
                } else {
                    board[point] = String.valueOf(str);
                    duplicated[str-'A']++;
                    point = (point+count) % n;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String s : board) {
            sb.append(s == null ? "?" : s);
        }

        return sb.toString();
    }
}