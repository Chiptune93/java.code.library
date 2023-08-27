package BFS;

import java.util.*;

/**
 * DFS Basic Structure
 *
 * @author dk
 * @since 2023.05.27
 */
class BFS_Basic {

    Queue<Object> queue = new LinkedList<>(); // Queue

    boolean[] chk;

    static int n; // number of chk arr

    public void BFS(int n) {
        // chk arr init
        chk = new boolean[n];
        // when visit -> chk
        chk[n] = true;
        // while queue is empry
        while (!queue.isEmpty()) {
            Object o = queue.poll();

            // logic ...

            // visit -> true -> add queue
            chk[n] = true;
            queue.add(o);
        }
    }
}
