package EuclideanAlgorithm;

/**
 * 최대 공약수 구하기.
 * 유클리드 호제법을 이용한 최대 공약수 구하기
 * 큰 수를 작은 수로 나눈 나머지를 반복적으로 취하여 나머지가 0이 될때까지 작동하여 최대공약수를 구한다.
 *
 * @author chiptune
 * @since 2023.09.01
 */
public class GCD {

    /* 재귀 방식
     * 큰 수를 작은 수로 나눈 나머지가 0이 될 때까지 반복 하여 큰 수를 리턴한다.
     */
    public static int recursion(int a, int b) {
        if (b == 0) return a;
        return recursion(b, a % b);
    }

    /* 반복문 방식
     * b에 a에서 b를 나눈 나머지를 계속 반복 저장하여 0이 될 때 까지 수행 후, 큰 수를 리턴한다.
     */
    public static int repeat(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
