import java.util.*;

/**
 * DFS 4방향 좌표 탐색
 * main -> 7*7 2차원 배열에서 출발 -> 도착까지 갈 수 있는 경우의 수를 구하기
 * @author dk
 * @since 2023.05.27
 * @ref https://chiptune93.github.io/posts/algorithm77/
 */
class DFS_CoordinatesSearch {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static int answer = 0;

    public void DFS(int x, int y) {
        // 도착하면 정답+1
        if (x == 7 && y == 7) answer++;
        else {
            // 현재 위치에서 4방향을 반복 탐색 하기 위해, 반복하면서 계속 깊게 탐색한다.
            for (int i = 0; i < 4; i++) {
                // 방향 이동 위치
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 각종 조건
                if (nx >= 1 && nx <= 7 && ny >= 1 && ny <= 7 && board[nx][ny] == 0) {
                    // 방문 체크
                    board[nx][ny] = 1;
                    // 다음 탐색
                    DFS(nx, ny);
                    // 방문 해제
                    board[nx][ny] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        DFS_CoordinatesSearch T = new DFS_CoordinatesSearch();
        Scanner kb = new Scanner(System.in);
        board = new int[8][8];
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                board[i][j] = kb.nextInt();
            }
        }
        // 시작 지점 방문으로 체크
        board[1][1] = 1;
        // (1,1) 부터 시작
        T.DFS(1, 1);
        System.out.print(answer);
    }
}
