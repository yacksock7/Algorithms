package prefixsum;

import java.io.*;
import java.util.Arrays;

/**
 *
 *  TODO Day-17 : 구간 합 구하기 5
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1초	    256 MB	    90899	41818	30869	44.042%
 *
 *  문제
 *  N×N개의 수가 N×N 크기의 표에 채워져 있다. (x1, y1)부터 (x2, y2)까지 합을 구하는 프로그램을 작성하시오. (x, y)는 x행 y열을 의미한다.
 *  예를 들어, N = 4이고, 표가 아래와 같이 채워져 있는 경우를 살펴보자.
 *  1	2	3	4
 *  2	3	4	5
 *  3	4	5	6
 *  4	5	6	7
 *  여기서 (2, 2)부터 (3, 4)까지 합을 구하면 3+4+5+4+5+6 = 27이고, (4, 4)부터 (4, 4)까지 합을 구하면 7이다.
 *  표에 채워져 있는 수와 합을 구하는 연산이 주어졌을 때, 이를 처리하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 표의 크기 N과 합을 구해야 하는 횟수 M이 주어진다. (1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000) 둘째 줄부터 N개의 줄에는 표에 채워져 있는 수가 1행부터 차례대로 주어진다. 다음 M개의 줄에는 네 개의 정수 x1, y1, x2, y2 가 주어지며, (x1, y1)부터 (x2, y2)의 합을 구해 출력해야 한다. 표에 채워져 있는 수는 1,000보다 작거나 같은 자연수이다. (x1 ≤ x2, y1 ≤ y2)
 *
 *  출력
 *  총 M줄에 걸쳐 (x1, y1)부터 (x2, y2)까지 합을 구해 출력한다.
 *
 *  예제 1.   4 3            27
 *           1 2 3 4        6
 *           2 3 4 5        64
 *           3 4 5 6
 *           4 5 6 7
 *           2 2 3 4
 *           3 4 3 4
 *           1 1 4 4
 *
 *  예제 2.   2 4            1
 *           1 2            2
 *           3 4            3
 *           1 1 1 1        4
 *           1 2 1 2
 *           2 1 2 1
 *           2 2 2 2
 *
 *
 */
public class D17_11660 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] split = br.readLine().split(" ");
            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);

            int[][] board = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < line.length; j++) {
                    board[i][j] = Integer.parseInt(line[j]);
                }
            }

            String[] q = new String[m];
            for (int i = 0; i < m; i++) {
                q[i] = br.readLine();
            }

//            String[] first = ("2 4").split(" ");
//            int[][] board = {{1, 2}, {3, 4}};
//            String[] q = ("1 1 1 1\n" +
//                    "1 2 1 2\n" +
//                    "2 1 2 1\n" +
//                    "2 2 2 2").split("\n");

            String result = mySolution03(board, q);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String mySolution(int[][] board, String[] q) {
        StringBuilder sb = new StringBuilder();
        for (String s : q) {
            String[] split = s.split(" ");
            int x1 = Integer.parseInt(split[0]);
            int y1 = Integer.parseInt(split[1]);

            int x2 = Integer.parseInt(split[2]);
            int y2 = Integer.parseInt(split[3]);

            int sum = 0;
            for (int i = x1-1; i < x2 ; i++) {
                for (int j = y1-1; j < y2 ; j++) {
                    sum += board[i][j];
                }
            }
            sb.append(sum).append("\n");
        }
        return sb.toString();
    }

    private static String mySolution02(int[][] board, String[] q) {
        StringBuilder sb = new StringBuilder();

        int[][] accRow = new int[board.length+1][board.length+1];
        for (int i = 1; i <= board.length; i++) {
            int sum = 0;
            for (int j = 1; j <= board.length; j++) {
                accRow[i][j] += accRow[i][j-1] + board[i-1][j-1];
            }
        }
        
        for (String s : q) {
            String[] split = s.split(" ");
            int x1 = Integer.parseInt(split[0]);
            int y1 = Integer.parseInt(split[1]);

            int x2 = Integer.parseInt(split[2]);
            int y2 = Integer.parseInt(split[3]);

            int sum = 0;
            for (int i = x1; i <= x2 ; i++) {
                sum += (accRow[i][y2] - accRow[i][y1-1]);
            }

            sb.append(sum).append("\n");
        }

        return sb.toString();
    }


    /**
     *  1. 2차원 배열 구간합을 배열로 만들어 두기
     *   - x-1, y의 값과 x, y-1값을 더하고,
     *   - 중복으로 더해진 x-1, y-1값을 빼고,
     *   - 아직 안 더해진 x, y 값을 더해준다.
     *
     *  2. 구간합 공식을 사용해 범위별 구간합 구하기.
     */
    private static String mySolution03(int[][] board, String[] q) {
        int[][] accRow = new int[board.length+1][board.length+1];
        for (int x = 1; x <= board.length; x++) {
            for (int  y= 1; y <= board.length; y++) {
                accRow[x][y] = accRow[x][y-1] + accRow[x-1][y] - accRow[x-1][y-1] + board[x-1][y-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String s : q) {
            String[] split = s.split(" ");
            int x1 = Integer.parseInt(split[0]);
            int y1 = Integer.parseInt(split[1]);

            int x2 = Integer.parseInt(split[2]);
            int y2 = Integer.parseInt(split[3]);

            int sum = accRow[x2][y2] - accRow[x1-1][y2] - accRow[x2][y1-1] + accRow[x1-1][y1-1];
            sb.append(sum).append("\n");
        }
//
//        for (String s : q) {
//            String[] split = s.split(" ");
//            int x1 = Integer.parseInt(split[0]);
//            int y1 = Integer.parseInt(split[1]);
//
//            int x2 = Integer.parseInt(split[2]);
//            int y2 = Integer.parseInt(split[3]);
//
//            int sum = 0;
//            for (int i = x1; i <= x2 ; i++) {
//                sum += (accRow[i][y2] - accRow[i][y1-1]);
//            }
//
//            sb.append(sum).append("\n");
//        }

        return sb.toString();
    }


}