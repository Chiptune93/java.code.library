import java.util.*;

/**
 * 조합 : n개 중 r개를 뽑는 경우의 수
 *
 * @author dk
 * @since 2023.05.27
 */
class Combination {
    // 경우의 수를 보관할 2차원 배열 선언
    static int[][] arr = new int[35][35];

    /**
     * int 형 수 2개를 받아 n개 중 r 개를 뽑는다. (재귀)
     *
     * @param n
     * @param r
     * @return Integer
     */
    public int init(int n, int r) {
        if (arr[n][r] > 0) return arr[n][r];
        if (n == r || r == 0) return 1;
        else return arr[n][r] = init(n - 1, r - 1) + init(n - 1, r);
    }

}
