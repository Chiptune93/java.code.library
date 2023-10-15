public class Sqrt {
    // 제곱수 판단
    public boolean sqrtValueIsSame(int n) {
        // sqrt -> 제곱근 구하기.
        // 소수점이 없다면 정수 변환시 같다는걸 이용한 판단.
        return Math.sqrt(n) == (int) Math.sqrt(n);
    }
}
