package string;

import java.io.*;

/**
 *  TODO Day-3
 *  시간 제한	  메모리    제한     제출	  정답	 맞힌 사람 정답 비율
 *  2 초	    512 MB	 4006	 1497	  1167	    36.030%
 *
 *  문제
 *  철수는 화학 시험을 망치고, 애꿎은 화학 선생님에게 복수를하기로 한다.
 *
 *  철수는 집에서 만든 자동 로봇팔을 선생님의 책상에 숨겨, 선생님이 수업을 시작하려 들어온 순간 숨겨놓은 로봇팔을 이용해 선생님을 혼내주려고한다. 철수는 선생님이 늘 애용하는 물컵에 시간이 되면 로봇팔이 소금을 잔뜩 집어넣도록 프로그램을 짜려고한다.
 *
 *  철수는 현재시각과 선생님이 언제 컵을 사용할지 시간을 알고있지만, 수 계산에 정말 약해서 로봇팔에 입력해야할 시간 계산을 못한다. 철수가 로봇팔에 알맞은 시간을 입력할수 있도록 도와주자.
 *
 *  입력
 *  첫째 줄에는 현재 시각이 hh:mm:ss로 주어진다. 시간의 경우 0≤h≤23 이며, 분과 초는 각각 0≤m≤59, 0≤s≤59 이다.
 *  두 번째 줄에는 소금 투하의 시간이 hh:mm:ss로 주어진다.
 *
 *  출력
 *  로봇팔이 소금을 투하할때까지 필요한 시간을 hh:mm:ss로 출력한다. 이 시간은 1초보다 크거나 같고, 24시간보다 작거나 같다.
 *
 *  예제 1. 20:00:00 04:00:00 / 08:00:00
 *  예제 2. 12:34:56 14:36:22 / 02:01:26
 */
public class D3_13223 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String nowT = "00:00:00"; // br.readLine();
            String saltT = "00:59:59"; // br.readLine();

            String result = mySolution(nowT, saltT);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *  현재 시각과 소금 시각을 초단위로 바꾼다.
     *  현재 시각이 소금 시각보다 작으면 현재시각에 24시간을 더하여 계산한다.
     *
     */
    private static String mySolution(String nowT, String saltT) {
        long nowS = convertToSec(nowT);
        long saltS = convertToSec(saltT);

        if (nowS > saltS) {
            saltS += (24 * 60 * 60);
        }

        long resultS = saltS - nowS;

        // 출력
        // 로봇팔이 소금을 투하할때까지 필요한 시간을 hh:mm:ss로 출력한다. 이 시간은 1초보다 크거나 같고, 24시간보다 작거나 같다.
        if (resultS == 0) {
            return "24:00:00";
        }

        long s = resultS % 60;
        long m = (resultS / 60) % 60;
        long t = (resultS / 60) / 60;

//        return String.format("%s:%s:%s", convertToString(t), convertToString(m), convertToString(s));
        return String.format("%02d:%02d:%02d", t, m, s);
    }

    private static String convertToString(long time) {
        if (time < 10) {
            return "0" + time;
        } else{
            return String.valueOf(time);
        }
    }

    private static long convertToSec(String time) {
        String[] times = time.split(":");

        long second = 0;
        second += Integer.parseInt(times[0]) * 60 * 60;
        second += Integer.parseInt(times[1]) * 60;
        second += Integer.parseInt(times[2]);

        return second;
    }
}
