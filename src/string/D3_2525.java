package string;

import java.io.*;

/**
 *  TODO Day-3 : 오븐 시계
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	    정답 비율
 *  1 초	    128 MB	    348475	134070	112611	    38.156%
 *
 *  문제
 *  KOI 전자에서는 건강에 좋고 맛있는 훈제오리구이 요리를 간편하게 만드는 인공지능 오븐을 개발하려고 한다. 인공지능 오븐을 사용하는 방법은 적당한 양의 오리 훈제 재료를 인공지능 오븐에 넣으면 된다. 그러면 인공지능 오븐은 오븐구이가 끝나는 시간을 분 단위로 자동적으로 계산한다.
 *
 *  또한, KOI 전자의 인공지능 오븐 앞면에는 사용자에게 훈제오리구이 요리가 끝나는 시각을 알려 주는 디지털 시계가 있다.
 *
 *  훈제오리구이를 시작하는 시각과 오븐구이를 하는 데 필요한 시간이 분단위로 주어졌을 때, 오븐구이가 끝나는 시각을 계산하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에는 현재 시각이 나온다. 현재 시각은 시 A (0 ≤ A ≤ 23) 와 분 B (0 ≤ B ≤ 59)가 정수로 빈칸을 사이에 두고 순서대로 주어진다. 두 번째 줄에는 요리하는 데 필요한 시간 C (0 ≤ C ≤ 1,000)가 분 단위로 주어진다.
 *
 *  출력
 *  첫째 줄에 종료되는 시각의 시와 분을 공백을 사이에 두고 출력한다. (단, 시는 0부터 23까지의 정수, 분은 0부터 59까지의 정수이다. 디지털 시계는 23시 59분에서 1분이 지나면 0시 0분이 된다.)
 *
 *  예제 1. 14 30 \n 20 / 14 50
 *  예제 2. 17 40 \n 80 / 19 0
 *  예제 3. 23 48 \n 25 / 0 13
 *
 */

public class D3_2525 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String current = "23 59"; // br.readLine();
            String needT = "1"; // br.readLine();

            String result = mySolution(current, needT);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String mySolution(String current, String needT) {
        String[] currents = current.split(" ");
        int currentM = (Integer.parseInt(currents[0]) * 60) + Integer.parseInt(currents[1]);
        int finish = currentM + Integer.parseInt(needT);

        if (finish >= (24 * 60)) {
            finish -= (24 * 60);
        }

        return String.format("%d %d", finish/60, finish%60);
    }
}
