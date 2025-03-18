package bruteforce;

import java.io.*;

/**
 *  TODO Day-13 : 사탕 게임
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1 초	    128 MB	    54590	19525	13365	34.481%
 *
 *  문제
 *  상근이는 어렸을 적에 "봄보니 (Bomboni)" 게임을 즐겨했다.
 *  가장 처음에 N×N크기에 사탕을 채워 놓는다. 사탕의 색은 모두 같지 않을 수도 있다. 상근이는 사탕의 색이 다른 인접한 두 칸을 고른다. 그 다음 고른 칸에 들어있는 사탕을 서로 교환한다. 이제, 모두 같은 색으로 이루어져 있는 가장 긴 연속 부분(행 또는 열)을 고른 다음 그 사탕을 모두 먹는다.
 *  사탕이 채워진 상태가 주어졌을 때, 상근이가 먹을 수 있는 사탕의 최대 개수를 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에 보드의 크기 N이 주어진다. (3 ≤ N ≤ 50)
 *  다음 N개 줄에는 보드에 채워져 있는 사탕의 색상이 주어진다. 빨간색은 C, 파란색은 P, 초록색은 Z, 노란색은 Y로 주어진다.
 *  사탕의 색이 다른 인접한 두 칸이 존재하는 입력만 주어진다.
 *
 *  출력
 *  첫째 줄에 상근이가 먹을 수 있는 사탕의 최대 개수를 출력한다.
 *
 *  예제 1.  3        3
 *          CCP
 *          CCP
 *          PPC
 *
 *  예제 2.  4        4
 *          PPPP
 *          CYZY
 *          CCPY
 *          PPCC
 *
 *  예제 2.  5        4
 *          YCPZY
 *          CYZZP
 *          CCPPP
 *          YCYZC
 *          CPPZZ
 *
 */
public class D13_3058 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int length = Integer.parseInt(br.readLine());
//            String[] sources = new String[length];
//            for (int i = 0; i < sources.length; i++) {
//                sources[i] = br.readLine();
//            }

            int length = Integer.parseInt("4");
            String[] sources = ("ZCPZ\n" +
                    "ZZYC\n" +
                    "ZCPZ\n" +
                    "ZZZY").split("\n");


            String result = mySolution(length, sources);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  완전 탐색
     *
     *  1. 현재 칸과 맞닿아 있는 가로 새로의 연속된 칸 count
     *      --> max count by map -> 전체 1번만 하면 되겠다.
     *  2. x가 x+1과 swap 되었을 때, 맞닿아 있는 가로, 세로의 연속된 칸 count
     *      swap
     *      가로 : 연속된 칸의 수가 최대값과 비교하여 더 크면 최댓값 수정
     *      세로 : 연속된 칸의 수가 최대값과 비교하여 더 크면 최댓값 수정
     *      --> max count by map
     *      rollback
     *  3. y가 y+1과 swap 되었을 때, 맞닿아 있는 가로, 세로의 연속된 칸 count
     *      swap
     *      가로 : 연속된 칸의 수가 최대값과 비교하여 더 크면 최댓값 수정
     *      세로 : 연속된 칸의 수가 최대값과 비교하여 더 크면 최댓값 수정
     *      --> max count by map
     *      rollback
     */
    private static String mySolution(int n, String[] sources) {

        String[][] board = new String[n][];
        for (int i = 0; i < sources.length; i++) {
            board[i] = sources[i].split("");
        }

        int max = checkMax(board);
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (x+1 < n) {
                    swap(board, x, x+1, y, y);
                    max = Math.max(max, checkMax(board));
                    swap(board, x, x+1, y, y);
                }

                if (y+1 < n) {
                    swap(board, x, x, y, y+1);
                    max = Math.max(max, checkMax(board));
                    swap(board, x, x, y, y+1);
                }
            }
        }
        return String.valueOf(max);
    }

    private static void swap(String[][] board, int beforeX, int afterX, int beforeY, int afterY) {
        String temp = board[beforeY][beforeX];
        board[beforeY][beforeX] = board[afterY][afterX];
        board[afterY][afterX] = temp;
    }

    private static int checkMax(String[][] board) {
        return Math.max(checkRow(board), checkCol(board));
    }

    /**
     * y를 돌면서, x의 최대 연속되는 수를 구한다.
     * 각 x행의 최댓값을 구하기 위해선
     *  1. 반복하며 현재 좌표값이 이전 좌표갑과 연속되는지 확인한다.
     *  2. 연속되면 rowMax 값을 1증가 한다.
     *  3. 연속되지 않으면, rowMax과 max값을 비교하여, 더 큰 수가 max 값이 된다.
     *  4. 연속되지 않으면, max값을 비교하고, rowMax를 초기화한다.
     */
    private static int checkRow(String[][] board) {
        int max = 1;
        for (int i = 0; i < board.length; i++) {
            int rowMax = 1;
            for (int j = 1; j < board.length; j++) {
                if (board[i][j].equals(board[i][j-1])) {
                    rowMax++;
                } else {
                    max = Math.max(max, rowMax);
                    rowMax = 1;
                }
            }
            max = Math.max(max, rowMax);
        }

        return max;
    }
    private static int checkCol(String[][] board) {
        int max = 1;
        for (int i = 0; i < board.length; i++) {
            int rowMax = 1;
            for (int j = 1; j < board.length; j++) {
                if (board[j][i].equals(board[j-1][i])) {
                    rowMax++;
                } else {
                    max = Math.max(max, rowMax);
                    rowMax = 1;
                }
            }
            max = Math.max(max, rowMax);
        }
        return max;
    }
}