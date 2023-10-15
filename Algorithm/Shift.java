

class Shift {

    public String rightShift(String string) {
        String[] temp = string.split("");
        String last = temp[temp.length - 1];
        for (int i = string.length() - 1; i >= 1; i--) {
            temp[i] = temp[i - 1];
        }
        temp[0] = last;
        return String.join("", temp);
    }

}
