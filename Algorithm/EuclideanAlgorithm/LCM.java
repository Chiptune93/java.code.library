package EuclideanAlgorithm;

/**
 * 최소 공배수 구하기
 * 두 수의 배수가 되는 값 중, 최소 값을 구하는 방법.
 * 유클리드 호제법에 따라 최대 공약수를 구한 후, 두 수를 곱한 값에서 나눈다.
 *
 * @author chiptune
 * @since 2023.09.01
 */
public class LCM {

    // 최소공배수 = 두 수의 곱 / 최대공약수
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private static int gcd(int a, int b) {
        // 재귀
        if (b == 0) return a;
        return gcd(b, a % b);
        // 반복
        //        while (b != 0) {
        //            int temp = b;
        //            b = a % b;
        //            a = temp;
        //        }
        //        return a;
    }
}
