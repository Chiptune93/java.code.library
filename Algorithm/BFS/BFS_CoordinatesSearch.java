package BFS;

import java.util.*;

/**
 * BFS 8방향 좌표 탐색
 * main -> 주어진 배열 내에서 조건에 맞게 찾는다. 큐를 활용한다.
 *
 * @author dk
 * @ref https://chiptune93.github.io/posts/algorithm80/
 * @since 2023.05.27
 */
class BFS_CoordinatesSearch {
    // 좌표 클래스
    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int answer = 0, n;
    // 8방향 좌표 배열
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    Queue<Point> queue = new LinkedList<>();

    public void BFS(int x, int y, int[][] board) {
        // 파라미터 좌표를 큐에 넣고 시작.
        queue.add(new Point(x, y));
        // 큐가 빌 때까지 탐색한다.
        while (!queue.isEmpty()) {
            // 큐에서 꺼낸 좌표
            Point pos = queue.poll();
            // 8방향 탐색
            for (int i = 0; i < 8; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 1) {
                    // 0은 바다을 의미 -> 1은 지면를 의미 -> 육지 몇개인지 찾는 문제라서.
                    board[nx][ny] = 0;
                    // 지면이니까 다시 탐색 가능 -> 큐에 좌표를 넣는다.
                    queue.add(new Point(nx, ny));
                }
            }
        }
    }

    public void solution(int[][] board) {
        // 2차원 배열만큼 반복한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 육지인 경우에는
                if (board[i][j] == 1) {
                    // 섬의 개수 1증가.
                    answer++;
                    // 탐색했으니 바다로 변경한다.
                    board[i][j] = 0;
                    // 그리고 8방향 탐색을 시작한다.
                    BFS(i, j, board);
                }
            }
        }
    }

    public static void main(String[] args) {
        BFS_CoordinatesSearch T = new BFS_CoordinatesSearch();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = kb.nextInt();
            }
        }
        T.solution(arr);
        System.out.println(answer);
    }
}
