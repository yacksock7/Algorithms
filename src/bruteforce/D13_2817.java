package bruteforce;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  TODO Day-13 : ALPS식 투표
 *
 *  시간 제한	메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1 초	    128 MB	    1540	546	    416	    37.011%
 *
 *  문제
 *  전대프연(전국 대학생 프로그래밍 대회 동아리 연합)에서는 매년 프로그래밍 대회를 연다. 올해도 무사히 대회를 개최한 전대프연 회장 성진은 수고해준 스태프들에게 수고비를 주기로 하였다. 하지만 몇몇 스태프는 일을 열심히하지 않았기 때문에 성진은 일을 열심히 한 사람에게만 주기로했다. 하지만 일을 무진장 열심히 한 사람과 덜 열심히 한 사람에게 수고비를 똑같이 주는 것은 불공평하다.
 *  고민을 한 성진은 수고비를 받을 사람을 선출하는 방식으로 ALPS(Allegro Leader Picking System) 을 사용하기로 결심했다. ALPS는 이름에서 보이듯이, 아주 유쾌하고 빠르게 사람들을 선별하는 방법이다.
 *  우선 대회 참가자들은  "수고비를 받을 가치가 있는 스태프" 한 명을 선택해 투표를 한다. (참가자가 투표를 하지 않을 수도 있다.) 이 투표결과, 전체 대회 참가자의 5% 미만의 득표를 얻은 사람은 열심히 일을 하지 않은 스태프이므로 후보에서 제외해버린다. 이제 남은 스태프마다, 받은 득표수를 1로 나눈 값, 2로 나눈 값... 14로 나눈 값을 구한다. 이렇게 구한 14개의 실수가 그 스태프의 '점수'들이 된다.
 *  이렇게 14 * (후보 스태프의 명수) 개의 실수를 가진 점수집합을 얻을 수 있다.  이 점수집합에서의 값에 따라 각 스태프들에게 14개의 칩을 나눠주는데,  집합 내에서 가장 큰 점수를 가진 후보 스태프에게 1개의 칩을 주고, 집합 내에서 두 번째로 점수가 큰 후보 스태프에게 1개의 칩을, ... 14번째로 점수가 큰 후보 스태프에게 1개의 칩을 준다. 최종적으로 스태프마다 득표수에 따라 칩의 개수가 다르게 지급될 것이다. 이것이 바로 ALPS식 투표이다. 성진은 스태프가 가진 칩의 개수에 비례해서 수고비를 지급하기로 했다. 신비롭게도, 점수집합에 있는 실수들은 항상 서로 다르도록 투표결과가 나온다고 한다.
 *  우리는 각 스태프마다 몇개의 표를 얻었는지를 알고있다. 이 득표수를 토대로, ALPS식 투표를 수행하게 된 후, 각 스태프가 받을 칩의 개수를 구하는 프로그램을 작성하시오.
 *
 *  입력
 *  첫 번째 줄에는 전대프연 대회에 참가한 참가자들의 수 X( 1 ≤ X ≤ 2,500,000) 이 주어진다. 두 번째 줄에는 전대프연에 참가한 스태프의 수 N (0 ≤ N ≤ 10) 이 주어진다.
 *  다음 N개의 줄에 걸쳐 각 스태프의 정보 -스태프의 이름(항상 대문자 알파벳이다.)과 그 스태프가 받은 득표수- 가 공백을 사이에 두고 주어진다.
 *
 *  출력
 *  득표율이 전체의 5% 이상인 스태프에 대해, 스태프의 이름과 그 스태프가 받은 칩의 개수를 한줄에 하나씩 출력한다. 출력하는 순서는 스태프 이름의 사전순이여야한다.
 *
 *  예제 1.  235217           A 9
 *          3                B 4
 *          A 107382         C 1
 *          C 18059
 *          B 43265
 *
 *  예제 2.  245143           A 8
 *          4                B 4
 *          F 14845          C 1
 *          A 104516
 *          B 52652
 *          C 14161
 *
 *  예제 2.  206278           A 6
 *          5                B 4
 *          D 44687          D 4
 *          A 68188
 *          C 7008
 *          B 48377
 *          G 9665
 */
public class D13_2817 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int total = Integer.parseInt(br.readLine());
//            int length = Integer.parseInt(br.readLine());
//            String[] sources = new String[length];
//            for (int i = 0; i < sources.length; i++) {
//                sources[i] = br.readLine();
//            }

            int total = Integer.parseInt("100");
            int length = Integer.parseInt("6");
            String[] sources = ("A 20\n" +
                    "B 19\n" +
                    "C 18\n" +
                    "D 17\n" +
                    "E 16\n" +
                    "F 5").split("\n");

            String result = mySolution(total, sources);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  total : 전체 득표 수
     *  sources : 후보자의 이름과 후보자의 득표 수
     *
     *  1. 후보자 중 득표율이 5%인 사람은 삭제한다.
     *  2. result라는 후보자별 hashMap을 만든다.
     *  1~14번까지 순회하며
     *      sources를 순회 ->  i+1로 나눈 나머지 "실수"가 가장 큰 사람을 구한다.
     *  나머지 실수가 가장 큰 사람을 result.put("후보자이름", get++);에 입력한다.
     *
     *  3. 후보자의 이름의 사전 순으로 출력한다.
     */
    private static String mySolution(int total, String[] sources) {

        Map<String, Double> voteMap = new HashMap<>();
        for (String source : sources) {
            String[] split = source.split(" ");
            String name = split[0];
            int vote = Integer.parseInt(split[1]);
            if (vote >= (total * 0.05)) { voteMap.put(name, (double) vote); }
        }


        List<Score> scoreObj = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            for (Map.Entry<String, Double> entry : voteMap.entrySet()) {
                double score = entry.getValue() / (i + 1);
                scoreObj.add(new Score(entry.getKey(), score, i+1));
            }
        }

        sorted(scoreObj);

        List<String> sorted = voteMap.keySet().stream().sorted().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (String key : sorted) {
            int count = 0;
            for (int i = 0; i < 14; i++) {
                if (key.equals(scoreObj.get(i).getName())) {
                    count++;
                }
            }
            String result = String.format("%s %d\n", key, count);
            sb.append(result);
        }

        return sb.toString();
    }

    public static void sorted(List<Score> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                int idx = i-j;

                if (list.get(idx).score > list.get(idx-1).score) {
                    Score temp = list.get(idx);
                    list.set(idx, list.get(idx-1));
                    list.set(idx-1, temp);
                } else {
                    break;
                }
            }
        }
    }

    static class Score {
        private String name;
        private double score;
        private int i;

        public Score(String name, double score, int i) {
            this.name = name;
            this.score = score;
            this.i = i;
        }

        public String getName() {
            return name;
        }

        public double getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Score{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    ", i=" + i +
                    '}';
        }
    }

}