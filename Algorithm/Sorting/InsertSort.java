package Sorting;

import java.util.*;

/**
 * Sort Algorithm
 * @since 2023.06.06
 * @author dk
 */

/**
 * Insert Sort Algorithm
 * 삽입 정렬 알고리즘
 *
 * @author dk
 * @since 2023.06.06
 */
class InsertSort {
    public int[] solution(int n, int[] arr) {
        for (int i = 1; i < n; i++) {
            int tmp = arr[i], j;
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > tmp) arr[j + 1] = arr[j];
                else break;
            }
            arr[j + 1] = tmp;
        }
        return arr;
    }

    public static void main(String[] args) {
        InsertSort T = new InsertSort();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = kb.nextInt();
        for (int x : T.solution(n, arr)) System.out.print(x + " ");
    }
}

