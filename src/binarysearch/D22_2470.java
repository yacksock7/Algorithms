package binarysearch;

import java.io.*;
import java.util.Arrays;
import java.util.TreeSet;

/**
 *  TODO Day-22 : 두 용액
 *
 *  시간 제한	            메모리 제한	제출	    정답	    맞힌 사람	정답 비율
 *  1 초 (추가 시간 없음)	128 MB	    72800	23999	17311	31.866%
 *
 *  문제
 *  KOI 부설 과학연구소에서는 많은 종류의 산성 용액과 알칼리성 용액을 보유하고 있다.
 *  각 용액에는 그 용액의 특성을 나타내는 하나의 정수가 주어져있다.
 *  산성 용액의 특성값은 1부터 1,000,000,000까지의 양의 정수로 나타내고,
 *  알칼리성 용액의 특성값은 -1부터 -1,000,000,000까지의 음의 정수로 나타낸다.
 *
 *  같은 양의 두 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다.
 *  이 연구소에서는 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.
 *
 *  예를 들어, 주어진 용액들의 특성값이 [-2, 4, -99, -1, 98]인 경우에는
 *  특성값이 -99인 용액과 특성값이 98인 용액을 혼합하면 특성값이 -1인 용액을 만들 수 있고,
 *  이 용액이 특성값이 0에 가장 가까운 용액이다.
 *  참고로, 두 종류의 알칼리성 용액만으로나 혹은 두 종류의 산성 용액만으로 특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재할 수 있다.
 *
 *  산성 용액과 알칼리성 용액의 특성값이 주어졌을 때,
 *  이 중 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾는 프로그램을 작성하시오.
 *
 *  입력
 *  첫째 줄에는 전체 용액의 수 N이 입력된다.
 *  N은 2 이상 100,000 이하이다.
 *  둘째 줄에는 용액의 특성값을 나타내는 N개의 정수가 빈칸을 사이에 두고 주어진다.
 *  이 수들은 모두 -1,000,000,000 이상 1,000,000,000 이하이다.
 *  N개의 용액들의 특성값은 모두 다르고, 산성 용액만으로나 알칼리성 용액만으로 입력이 주어지는 경우도 있을 수 있다.
 *
 *  출력
 *  첫째 줄에 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값을 출력한다.
 *  출력해야 하는 두 용액은 특성값의 오름차순으로 출력한다.
 *  특성값이 0에 가장 가까운 용액을 만들어내는 경우가 두 개 이상일 경우에는 그 중 아무것이나 하나를 출력한다.
 *
 *  예제 1.   5                   -99 98
 *          -2 4 -99 -1 98
 */
public class D22_2470 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

//            int n = Integer.parseInt(br.readLine());
//            int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[] numbers = Arrays.stream("-99 -80 -11 -10 -2 -1 6 75 89".split(" ")).mapToInt(Integer::parseInt).toArray();

            String result = tSolution02(numbers);
            bw.write(result);

            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  numbers 정열 0과 같거나 작은 숫자의 index 찾기 -> 없으면 = 0;
     *
     * 3개 경우의 수 비교해서 가장 0에 가까운 수 = 절대값이 가장 작은 수 찾기
     * 1+2
     * -1+1
     * -1+-2
     *
     * -------------> 실패
     */
    private static String mySolution(int[] arr) {
        Arrays.sort(arr);

        int idx = findLowerBoundIndex(arr, 0);

        StringBuilder sb = new StringBuilder();
        if (idx == 0) {
            sb.append(arr[idx] + " " + arr[idx+1]);

        } else if (idx == 1) {
            int a = Math.abs(arr[idx-1] + arr[idx]);
            int b = Math.abs(arr[idx] + arr[idx+1]);
            if (Math.min(a, b) == a) {
                sb.append(arr[idx-1] + " " + arr[idx]);
            } else {
                sb.append(arr[idx] + " " + arr[idx+1]);
            }

        } else if (idx == arr.length-1) {
            sb.append(arr[idx-1] + " " + arr[idx]);

        } else if (idx == arr.length-2) {
            int a = Math.abs(arr[idx-1] + arr[idx]);
            int b = Math.abs(arr[idx] + arr[idx+1]);
            if (Math.min(a, b) == a) {
                sb.append(arr[idx-1] + " " + arr[idx]);
            } else {
                sb.append(arr[idx] + " " + arr[idx+1]);
            }

        } else {
            int a = Math.abs(arr[idx] + arr[idx+1]);
            int b = Math.abs(arr[idx-1] + arr[idx]);
            int c = Math.abs(arr[idx-2] + arr[idx-1]);
            int min = Math.min(a, Math.min(b, c));

            if (min == a) {
                sb.append(arr[idx] + " " + arr[idx+1]);
            } else if (min == b) {
                sb.append(arr[idx-1] + " " + arr[idx]);
            } else {
                sb.append(arr[idx-2] + " " + arr[idx-1]);
            }
        }

        return sb.toString();
    }
    private static int findLowerBoundIndex(int[] arr, int x) {
        int l = 0;
        int r = arr.length -1;

        while (l < r) {
            int m = (l+r) /2;
            if (arr[m] < x) l = m+1;
            else {
                r = m;
            }
        }
        return r;
    }


    /**
     *  TreeSet을 이용한 절대값이 가장 작은 두 숫자 조합 구하기.
     */
    private static String tSolution01(int[] numbers) {
        Arrays.sort(numbers);

        int ansAbs = 2_000_000_001;
        int a = 0;
        int b = 0;
        TreeSet<Integer> numberSet = new TreeSet();

        for (int number : numbers) {
            Integer[] pairValues = {numberSet.floor(-number), numberSet.ceiling(-number)};
            for (Integer pairValue : pairValues) {
                if (pairValue == null) continue;

                int sumAbs = Math.abs(number + pairValue);
                if (sumAbs < ansAbs) {
                    ansAbs = sumAbs;
                    a = number;
                    b = pairValue;
                }
            }
            numberSet.add(number);
        }

        return a + " " + b;
    }


    /**
     *  Binary Search을 이용한 절대값이 가장 작은 두 숫자 조합 구하기.
     */
    private static String tSolution02(int[] numbers) {
        Arrays.sort(numbers);

        int minAbs = 2_000_000_001;
        int a = 0;
        int b = 0;
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            int pairValue = findPairValue(numbers, i+1, numbers.length-1, number);
            int sumAbs = Math.abs(number + pairValue);
            if (sumAbs < minAbs) {
                minAbs = sumAbs;
                a = number;
                b = pairValue;
            }

            if (minAbs == 0 ) break;
        }

        return a + " " + b;
    }

    private static int findPairValue(int[] numbers, int l, int r, int number) {
        int minAbs = 2_000_000_001;
        int pairValue = 0;

        while (l <= r) {
            int m = (l+r) / 2;
            int sum = numbers[m] + number;
            int sumAbs = Math.abs(sum);
            if (sumAbs < minAbs) {
                minAbs = sumAbs;
                pairValue = numbers[m];
            }

            if (sum > 0) r = m-1;
            else if (sum < 0) l = m+1;
            else return numbers[m];
        }

        return pairValue;
    }

}