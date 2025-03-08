package timecomplexity;

import java.io.*;

/**
 *  TODO Day-6 : 개미
 *
 *  시간 제한	                메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  0.15 초 (추가 시간 없음)	256 MB	    25370	7207	5764	31.297%
 *
 *  문제
 *  가로 길이가 w이고 세로 길이가 h인 2차원 격자 공간이 있다. 이 격자는 아래 그림처럼 왼쪽 아래가 (0,0)이고 오른쪽 위가 (w,h)이다. 이 공간 안의 좌표 (p,q)에 개미 한 마리가 놓여있다. 개미는 오른쪽 위 45도 방향으로 일정한 속력으로 움직이기 시작한다. 처음에 (p,q)에서 출발한 개미는 1시간 후에는 (p+1,q+1)로 옮겨간다. 단, 이 속력으로 움직이다가 경계면에 부딪치면 같은 속력으로 반사되어 움직인다.
 *
 *  위 그림은 6×4 격자에서 처음에 (4,1)에서 출발한 개미가 움직인 길을 보여주고 있다. 처음에 (4,1)에 있는 개미는 2시간 후에 (6,3)에 있으며 8시간 후에 (0,1)에 있다. 만일 그 개미가 처음에 (5,3)에 있었다면 매 시간마다 (6,4), (5,3), (4,2), (3,1)로 움직인다.
 *  여러분은 크기 w×h인 격자 공간에서 처음에 (p,q)에서 출발하는 개미의 t시간 후의 위치 (x,y)를 계산하여 출력해야 한다. 개미는 절대 지치지 않고 같은 속력으로 이동한다고 가정한다.
 *  문제에서 w와 h는 자연수이며 범위는 2 ≤ w,h ≤ 40,000이다. 그리고 개미의 초기 위치 p와 q도 자연수이며 범위는 각각 0 < p < w과 0 < q < h이다. 그리고 계산할 시간 t의 범위는 1 ≤ t ≤ 200,000,000이다.
 *
 *  입력
 *  첫줄에는 w와 h가 공백을 사이에 두고 주어진다. 그 다음 줄에는 초기 위치의 좌표값 p와 q가 공백을 사이에 두고 주어진다. 3번째 줄에는 개미가 움직일 시간 t가 주어진다.
 *
 *  출력
 *  출력은 t 시간 후에 개미의 위치 좌표 (x,y)의 값 x와 y를 공백을 사이에 두고 출력한다.
 *
 *  예제 1.  6 4      0 1
 *          4 1
 *          8
 *
 *  예제 2.  6 4      3 1
 *          5 3
 *          4
 *
 */
public class D6_10158 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String wh = "6 4";// br.readLine();
            String xy = "4 1";// br.readLine();
            String time = "8";// br.readLine();

            String result = mySolution02(wh, xy, time);

            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  방법 01. for문으로 따라가보자. -> 시간 초과
     *
     *  1. t 만큼 순회한다.
     *
     *  2. x가 w랑 같을 때까지 ++연산을 하고, x가 w와 같아지면 --연산으로 전환한다.
     *  3. x가 0이랑 같을 때까지 --연산을 하고, x가 0이랑 같아지면 ++연산으로 전환한다.
     *
     *  4. y가 h랑 같을 때까지 ++연산을 하고, y가 h와 같아지면 --연산으로 전환한다.
     *  5. y가 0랑 같을 때까지 ++연산을 하고, y가 0이랑 같아지면 --연산으로 전환한다.
     */
    private static String mySolution01(String wh, String xy, String time) {
        int w = Integer.parseInt(wh.split(" ")[0]);
        int h = Integer.parseInt(wh.split(" ")[1]);
        int x = Integer.parseInt(xy.split(" ")[0]);
        int y = Integer.parseInt(xy.split(" ")[1]);

        Integer t = Integer.parseInt(time);
        boolean xFlag = w != x;
        boolean yFlag = h != y;
        for (int i = 0; i < t; i++) {
            if (xFlag) {
                x++;
            } else {
                x--;
            }

            if (x == w || x == 0) {
                xFlag = !xFlag;
            }

            if (yFlag) {
                y++;
            } else {
                y--;
            }

            if (y == h || y == 0) {
                yFlag = !yFlag;
            }
        }

        return x + " " + y;
    }

    /**
     *  방법 02. 계산으로할 수 있지 않을까? -> 시간 초과
     *
     *  1. x만 먼저 구해보자
     *      t / h는 왔다갔다 하는 횟수 n,
     *          n = 짝수던 홀수던 위치 그대로
     *          짝수면 앞으로 홀수면 뒤로 가야한다.
     *
     *      (t % h) + x 는,
     *          t % h는 앞으로 몇칸을 더 가야하는지
     *          x는 현재 위치 어디에서 출발해야하는지,
     *
     *          (t % h) + x가 h보다 커? n++;
     *          ((t % h) + x - h)의 절대값을 구한다.
     *
     *      n이 짝수면 w-x, n이 홀수면 0+x
     *
     *
     *
     *      앞에껀 너무 복잡해.....
     *      0부터 시작했다고 가정하자.
     *
     *      (t+x)가 0부터 시작해서 움직인 횟수(시간)
     *      (t+x) / h가 0-w까지 혹은 w-0까지 이동한 횟수(시간)
     *      (t+x) / h가 짝수라면 현재 위치 0, 이동할 방향 ++; -> 0 + (t+x) % w
     *      (t+x) / h가 홀수라면 현재 위치 w, 이동할 방향 --; -> w - (t+x) % w
     *      이동할 횟수(시간) -> (t+x) % w
     *
     */
    private static String mySolution02(String wh, String xy, String time) {
        int w = Integer.parseInt(wh.split(" ")[0]);
        int h = Integer.parseInt(wh.split(" ")[1]);
        int x = Integer.parseInt(xy.split(" ")[0]);
        int y = Integer.parseInt(xy.split(" ")[1]);
        int t = Integer.parseInt(time);

        x = culc(w, x, t);
        y = culc(h, y, t);

        return x + " " + y;
    }

    private static int culc(int w, int x, int t) {
        int myT = t + x;
        if ((myT / w) % 2 == 0) {
            x = 0 + myT % w;
        } else {
            x = w - myT % w;
        }
        return x;
    }

}