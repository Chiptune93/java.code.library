package DFS;

import java.util.*;

/**
 * DFS Basic Structure
 *
 * @author dk
 * @since 2023.05.27
 */
class DFS_Basic {

    static int n; // return condition

    public void DFS(int Level) {
        if (Level == n) {
            // return condition
        } else {
            //... logic ...
            DFS(Level + 1);
            //... logic ...
        }
    }
}
