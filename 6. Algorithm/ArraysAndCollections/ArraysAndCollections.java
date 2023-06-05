package ArraysAndCollections;


/**
 * 배열 및 콜렉션 관련 내용
 *
 * @author dk
 * @since 2023.06.06
 */
public class ArraysAndCollections {

    /**
     * 소수 판별 함수
     *
     * @param value
     * @return boolean
     */
    public boolean is_prime(int value) {
        // 0,1 은 소수가 아님.
        if (value < 2) {
            return false;
        }
        // 2는 소수 (고정)
        if (value == 2) {
            return true;
        }
        int cnt = 0;
        // 2 이상 부터는 제곱근으로 나누어 떨어지는 지 체크 .
        for (int i = 2; i <= Math.sqrt(value); i++) {
            if (value % i == 0) {
                cnt++;
            }
        }
        if (cnt > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 숫자 뒤집는 함수
     *
     * @param value
     * @return int
     */
    public int flipNumber(int value) {
        int result = 0;
        while (value != 0) {
            // 10으로 나눈 나머지를 더해 나간다.
            // 10으로 나눈 나머지는 자릿수이다.
            // 1의 자리가 실행되고 나면 10으로 나누어 자동으로 자릿수를 바꿔준다.
            result = result * 10 + value % 10;
            value /= 10;
        }
        return result;
    }

    /**
     * 격자 판 방향 별 합 구하기
     *
     * @param boardWidth
     * @param boardHeight
     * @return void
     */
    public void boardDirectionSum(int boardWidth, int boardHeight) {
        int[][] arr = new int[boardWidth][boardHeight];
        int[] column = new int[boardWidth];
        int[] row = new int[boardHeight];
        int[] cross = new int[boardWidth];
        for (int i = 0; i < boardWidth; i++) {
            int sum = 0;
            int sum2 = 0;
            int sum3 = 0;
            for (int j = 0; j < boardHeight; j++) {
                // 행의 합, 열의 합. 거꾸로 변수 줘서 구하기.
                sum += arr[i][j];
                sum2 += arr[j][i];
                if (i == j) {
                    sum3 += arr[i][j];
                }
            }
            column[i] = sum;
            row[i] = sum2;
            if (i < 2)
                cross[i] = sum3;
        }
    }

    /**
     * 격자 판 상,하,좌,우 중 최대 값 체크 함수
     *
     * @return answer
     */
    public int directionCheck() {
        int length = 10; // 격자 판 가로,세로 길이
        int[][] arr = new int[length][length];
        int answer = 0;
        // 시계방향 기준으로 셋트.
        // 12시 : (0,-1)
        // 3시  : (1,0)
        // 6시  : (0,1)
        // 9시  : (-1,0)
        int[] mx = {0, 1, 0, -1};
        int[] my = {-1, 0, 1, 0};
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int max = 0;
                for (int k = 0; k < 4; k++) {
                    // 선언된 상하좌우배열 크기만큼 좌표구하기
                    int xx = (i + mx[k]);
                    int yy = (j + my[k]);
                    // 원래 인덱스 벗어나면 예외처리(여기서는 0)
                    if ((xx < 0 || yy < 0) || (xx >= length || yy >= length)) {
                    } else {
                        // 구한 좌표들 중에서 최대값 구하기.
                        if (arr[xx][yy] > max) max = arr[xx][yy];
                    }
                }
                // 그 최대값 보다 원래 원소 값의 크기가 크면, 봉우리.
                if (arr[i][j] > max) answer += 1;
            }
        }
        return answer;
    }

    /**
     * combine 활용, 숫자 N개 중 R개 뽑는 경우의 수
     *
     * @param arr    뽑고자 하는 배열 리스트
     * @param picked 뽑았는지 체크 하는 boolean 배열
     * @param start  시작 초기 값
     * @param length 배열 길이
     * @param count  뽑고자 하는 숫자의 개수
     */
    public static void combine(int[] arr, boolean[] picked, int start, int length, int count) {
        if (count == 0) {
            for (int i = 0; i < length; i++) {
                if (picked[i]) {
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println("");
        }
        for (int i = start; i < length; i++) {
            picked[i] = true;
            combine(arr, picked, i + 1, length, count - 1);
            picked[i] = false;
        }
    }
}


