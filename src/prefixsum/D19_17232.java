package prefixsum;

import java.io.*;
import java.util.Arrays;

/**
 *
 *  TODO Day-19 : 생명 게임
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  2 초	    512 MB	    870	    320	    247	    45.826%
 *
 *  문제
 *  생명 게임은 수학자 콘웨이(John Horton Conway)가 창안한 게임으로, 바둑판 모양의 격자에 '생명'을 배치하고 그 변화를 관찰하는 게임이다.
 *
 *  <그림1> 자리를 바꾸면서 무한히 움직이는 패턴의 한 예. 출처) 네이버 지식백과
 *  준표는 콘웨이가 창안한 생명 게임에서 사소한 조건을 바꿔 새로운 규칙의 생명 게임을 제안 해보았다.
 *
 *  <그림2>
 *      바둑판의 각 칸은 주위의 영향을 받는데, 주위란 <그림2>에서 색칠한 영역과 같이 현재 칸을 중심으로 둔 한 변의 길이가 (2K + 1) 인 정사각형에서 현재 칸을 제외한 영역을 말한다.
 *      바둑판의 각 칸은 주위에 몇 개의 생명이 존재하는지에 따라 다음 상황이 아래와 같이 결정된다.
 *
 *      생존 : 만약 현재 칸에 생명이 있고, 주위에 a개 이상 b개 이하의 생명이 있다면 현재 칸의 생명은 다음 단계에 살아남는다.
 *      고독 : 만약 현재 칸에 생명이 있고, 주위에 a개 미만의 생명이 있다면 현재 칸의 생명은 외로워서 죽는다.
 *      과밀 : 만약 현재 칸에 생명이 있고, 주위에 b개 초과의 생명이 있다면 현재 칸의 생명은 과밀로 죽는다.
 *      탄생 : 만약 현재 칸에 생명이 없고, 주위에 a개 초과 b개 이하의 생명이 있다면 다음 단계에서 현재 칸에 생명이 생긴다.
 *      생명은 바둑판을 벗어난 영역에서는 생존할 수 없다.
 *
 *      준표는 N×M 크기의 바둑판에 생명을 뿌리고, T시간 뒤의 생명을 관찰하고자 한다.
 *
 *  입력
 *      첫줄에는 바둑판의 세로길이, 가로길이를 나타내는 두 정수 N과 M, 준표가 바둑판을 관찰하고자 하는 시간 T가 주어진다.
 *      두번째 줄에는 주위의 기준이 되는 정수 K, 각 칸의 다음 상황을 결정하는 정수 a, b가 주어진다.
 *      다음 N개의 줄에 걸쳐 바둑판의 처음 상태가 주어진다. 각 줄은 길이 M의 문자열로 생명이 있는 칸은 '*', 빈칸은 '.'로 주어진다.
 *
 *  출력
 *      N개의 줄에 걸쳐 바둑판의 상태를 출력한다. 각 줄은 길이 M의 문자열로 생명이 있는 칸은 '*', 빈칸은 '.'로 출력한다.
 *
 *  제한
 *      1 ≤ N, M ≤ 100
 *      1 ≤ T ≤ 300
 *      0 ≤ a, b < (2×K+1)2
 *      서브태스크 1 (100점)
 *      K = 1
 *      서브태스크 2 (40점)
 *      1 ≤ K ≤ max(N, M)
 *
 *
 *  예제 1.   6 6 7           ......
 *           1 2 3          ......
 *           .*....         ..*...
 *           ..*...         ...**.
 *           ***...         ..**..
 *           ......         ......
 *           ......
 *           ......
 */
public class D19_17232 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            String[] firstLine = br.readLine().split(" ");
//            int n = Integer.parseInt(firstLine[0]);
//            int m = Integer.parseInt(firstLine[1]);
//            int t = Integer.parseInt(firstLine[2]);
//
//            String kab = br.readLine();
//            String[] board = new String[n];
//            for (int i = 0; i < n; i++) {
//                board[i] = br.readLine();
//            }

            int t = 7;
            String kab = "1 2 3";
            String[] board = (".*....\n" +
                    "..*...\n" +
                    "***...\n" +
                    "......\n" +
                    "......\n" +
                    "......").split("\n");

            String result = tSolution(t, kab, board);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * (2K + 1)
     *
     * 생존 : 만약 현재 칸에 생명이 있고, 주위에 a개 이상 b개 이하의 생명이 있다면 현재 칸의 생명은 다음 단계에 살아남는다.
     * 탄생 : 만약 현재 칸에 생명이 없고, 주위에 a개 초과 b개 이하의 생명이 있다면 다음 단계에서 현재 칸에 생명이 생긴다.
     * 고독 : 만약 현재 칸에 생명이 있고, 주위에 a개 미만의 생명이 있다면 현재 칸의 생명은 외로워서 죽는다.
     * 과밀 : 만약 현재 칸에 생명이 있고, 주위에 b개 초과의 생명이 있다면 현재 칸의 생명은 과밀로 죽는다.
     *
     *
     *  1. temp[board.length][board.length]
     *  t만큼 반복한다.
     *      2. board[0][0] ~ board[board.length][board.length]까지 순회한다.
     *          - board[2k+1][2k+1] 만큼의 영역에서 위 조건을 확인한다.
     *              - board[0] + board[1] + board[2] - board[0][0](나 자신)의 *개수 확인
     *          - temp[k][k]에 값을 넣는다.
     *      3. board[board.length][board.length]까지 순회를 완료하면, board를 temp로 교체한다.
     *
     */
//    private static String mySolution(int t, String kab, String[] board) {
//        String[] split = kab.split(" ");
//        int k = Integer.parseInt(split[0]);
//        int a = Integer.parseInt(split[1]);
//        int b = Integer.parseInt(split[2]);
//
//        String[] temp = new String[board.length];
//        String[] counts = new String[board.length];
//        for (int i = 0; i < t; i++) {
//            System.out.println("i = " + i);
//            Arrays.stream(board).forEach(System.out::println);
//
//            for (int x = 0; x < board.length; x++) {
//                for (int y = 0; y < board.length; y++) {
//
//                    int startX = (x - k) < 0 ? 0 : (x - k);
//                    int endX = (x + k) > board.length-1 ? board.length - 1 : (x + k);
//                    int startY = (y - k) < 0 ? 0 : (y - k);
//                    int endY = (y + k) > board.length-1 ? board.length - 1 : (y + k);
//
//
//                    StringBuilder sb = new StringBuilder();
//                    for (int j = startX; j < endX; j++) {
//                        sb.append(board[j].substring(startY, endY+1));
//                    }
//
//                    String around = sb.toString();
//                    int current = board[x].substring(y, y+1).equals("*") ? -1 : 0;
//                    int before = around.length();
//                    int after  = around.replace("*", "").length();
//                    int count = before - after + current;
//
//                    String result;
//                    if (current == 0) {
//                        if (count >= a && count <= b) {
//                            result = "*";
//                        } else {
//                            result = ".";
//                        }
//                    } else {
//                        if (count > a && count <= b) {
//                            result = "*";
//                        } else {
//                            result = ".";
//                        }
//                    }
//
//                    if (temp[x] == null) {
//                        temp[x] = "";
//                        counts[x] = "";
//                    }
//                    temp[x] += result;
//                    counts[x] += count;
//
//                }
//            }
//            board = temp;
//            temp = new String[board.length];
//            System.out.println("============================");
//            Arrays.stream(counts).forEach(System.out::println);
//        }
//        return null;
//    }


    /**
     *  t 만큼 반복한다.
     *      2차 acc 배열을 만들어.
     *      int[][] acc = new int[arr.length][arr[0].length]
     *      acc[x][y] = acc[x-1][y] + acc[x][y-1] + acc[x-1][y-1]
     *                  + arr[x-1][y-1] == "*" ? 1 : 0;
     *
     *      그리고 범위 내 *의 개수를 구해야지
     *      int startX = Math.max(x-k, 0);
     *      int endX = Math.max(x+k, arr.length-1);
     *      int startY = Math.max(y-k, 0);
     *      int endY = Math.max(y+k, arr.length-1);
     *
     *      around = acc[endX][endY] - acc[startX-1][endY] - acc[endX][startY-1] + acc[startX-1][endX-1];
     *      if (arr[x][y] == "*") around--;
     *
     *      그리고 around에 따라 "."일지, "*"일지... 확인.
     *
     */

    private static String tSolution(int t, String kab, String[] board) {
        String[] split = kab.split(" ");
        int k = Integer.parseInt(split[0]);
        int a = Integer.parseInt(split[1]);
        int b = Integer.parseInt(split[2]);

        int n = board.length;
        int m = board[0].length();

        char[][] arr = new char[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = board[i-1].charAt(j-1);
            }
        }
//        Arrays.stream(arr).forEach(v -> System.out.println(Arrays.toString(v)));

        for (int i = 0; i < t; i++) {
//        for (int i = 0; i < 1; i++) {
//            Arrays.stream(arr).forEach(v -> System.out.println(Arrays.toString(v)));
//            System.out.println();

            int[][] acc = getAcc(arr);
//            System.out.println("== acc == ");
//            Arrays.stream(acc).forEach(v -> System.out.println(Arrays.toString(v)));
            int[][] count = new int[n+1][m+1];

            // 구간 합을 구해야지.
            for (int x = 1; x <= n; x++) {
                for (int y = 1; y <= m ; y++) {
                    int around = getAround(acc, x, y, k);


                    if (arr[x][y] == '*') {
                        around--;
                        if (around < a || around > b) {
                            arr[x][y] = '.';
                        }
                    } else {
                        if (around > a && around <= b) {
                            arr[x][y] = '*';
                        }
                    }
                    count[x][y] = around;
                }
            }

//            System.out.println("== count ==");
//            Arrays.stream(count).forEach(v -> System.out.println(Arrays.toString(v)));
//            System.out.println("===========");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= m; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private static int getAround(int[][] acc, int x, int y, int k) {
        int startX = Math.max(x-k, 1);
        int endX = Math.min(x+k, acc.length-1);
        int startY = Math.max(y-k, 1);
        int endY = Math.min(y+k, acc[0].length-1);

        return acc[endX][endY] - acc[startX-1][endY] - acc[endX][startY-1] + acc[startX-1][startY-1];
    }

    /**
     *      2차 acc 배열을 만들어.
     *      int[][] acc = new int[arr.length][arr[0].length]
     *      acc[x][y] = acc[x-1][y] + acc[x][y-1] + acc[x-1][y-1]
     *                  + arr[x-1][y-1] == "*" ? 1 : 0;
     */

    private static int[][] getAcc(char[][] arr) {
        int[][] acc = new int[arr.length][arr[0].length];
        for (int x = 1; x < acc.length; x++) {
            for (int y = 1; y < acc[0].length; y++) {

                acc[x][y] = acc[x-1][y] + acc[x][y-1] - acc[x-1][y-1]
                            + (arr[x][y] == '*' ? 1 : 0);
            }
        }

        return acc;
    }
}