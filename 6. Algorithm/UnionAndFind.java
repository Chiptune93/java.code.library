import java.util.*;

/**
 * Union & Find
 * @author dk
 * @since 2023.05.30
 */
class UnionAndFind {

    static int[] unf;

    public static int Find(int v) {
        if (v == unf[v]) return v;
        else return unf[v] = Find(unf[v]); // 경로를 압축하는 과정을 위한 리턴 문.
    }

    // 집합을 만들 때, 같은 집합인지 확인하면서 넣음.
    public static void Union(int a, int b) {
        int fa = Find(a);
        int fb = Find(b);
        if (fa != fb) unf[fa] = fb;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        // input example -> Find Two Student is Friend ?
        // (1,2) -> same union

        //        9 7
        //        1 2
        //        2 3
        //        3 4
        //        1 5
        //        6 7
        //        7 8
        //        8 9
        //        3 8
        int n = kb.nextInt();
        int m = kb.nextInt();
        unf = new int[n + 1];
        for (int i = 1; i <= n; i++) unf[i] = i;
        for (int i = 1; i <= m; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            Union(a, b);
        }
        int a = kb.nextInt();
        int b = kb.nextInt();
        int fa = Find(a);
        int fb = Find(b);
        if (fa == fb) System.out.println("YES");
        else System.out.println("NO");
    }
}
