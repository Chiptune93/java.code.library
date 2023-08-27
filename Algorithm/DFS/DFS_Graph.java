package DFS;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 그래프 탐색 방법 2가지, 인접행렬과 인접리스트
 *
 * @author dk
 * @since 2023.06.06
 */
public class DFS_Graph {

    /**
     * 인접행렬을 통한 그래프 탐색
     */
    private static class graph {
        static int n, m, answer = 0;
        static int[][] graph;
        static int[] ch;

        public void DFS(int v) {
            if (v == n)
                answer++;
            else {
                for (int i = 1; i <= n; i++) {
                    if (graph[v][i] == 1 && ch[i] == 0) {
                        ch[i] = 1;
                        DFS(i);     // 갔다가 돌아오면
                        ch[i] = 0;  // 방문 해제 시켜준다.
                    }
                }
            }
        }
    }

    /**
     * 인접 리스트를 통한 그래프 탐색
     * > 인접 리스트에서는 특정 정점에서 갈 수 있는 정점만 저장해 놓고 탐색한다.
     */
    private static class graph2 {
        static int n, m, answer = 0;
        static ArrayList<ArrayList<Integer>> graph;
        static int[] ch;

        public void DFS(int v) {
            if (v == n) {
                answer++;
            } else {
                for (int nv : graph.get(v)) {
                    if (ch[nv] == 0) {
                        ch[nv] = 1;
                        DFS(nv);
                        ch[nv] = 0;
                    }
                }
            }
        }

    }

    static int n, m, answer = 0;
    static ArrayList<ArrayList<Integer>> graph2;
    static int[][] graph1;
    static int[] ch;

    public static void main(String[] args) {
        if (true) {
            graph G = new graph();
            Scanner kb = new Scanner(System.in);
            n = kb.nextInt(); // 정점의 개수
            m = kb.nextInt(); // 간선의 개수
            graph1 = new int[n + 1][m + 1]; // 그래프 2차원 배열
            ch = new int[n + 1]; // 방문 체크 배열
            for (int i = 0; i < m; i++) {
                int a = kb.nextInt();
                int b = kb.nextInt();
                graph1[a][b] = 1;
            }
            ch[1] = 1;
            G.DFS(1);
            System.out.println(answer);
        } else {
            graph2 G = new graph2();
            Scanner kb = new Scanner(System.in);
            n = kb.nextInt(); // 정점의 개수
            m = kb.nextInt(); // 간선의 개수
            graph2 = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i <= n; i++)
                graph2.add(new ArrayList<Integer>());
            ch = new int[n + 1]; // 방문 체크 배열
            for (int i = 0; i < m; i++) {
                int a = kb.nextInt();
                int b = kb.nextInt();
                graph2.get(a).add(b);
            }
            ch[1] = 1;
            G.DFS(1);
            System.out.println(answer);
        }
    }


}
