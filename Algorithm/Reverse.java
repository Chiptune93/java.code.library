public class Reverse {

    /**
     * int[] reverse
     *
     * @param input -> int[]
     * @return int[]
     */
    public static int[] reverse(int[] input) {
        int last = input.length - 1;
        int middle = input.length / 2;
        for (int i = 0; i <= middle; i++) {
            int temp = input[i];
            input[i] = input[last - i];
            input[last - i] = temp;
        }
        return input;
    }
}
