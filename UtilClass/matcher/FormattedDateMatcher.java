import java.util.regex.Pattern;

public class FormattedDateMatcher implements DateMatcher {

    // yyyy.MM.dd
    // ^(19[0-9]{2}|2[0-9]{3})-(0[1-9]|1[012])-([123]0|[012][1-9]|31)$

    // yyyy.MM.dd HH:mm:ss
    // ^(19[0-9]{2}|2[0-9]{3})-(0[1-9]|1[012])-([123]0|[012][1-9]|31)
    // ([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$

    // yyyy.MM.dd HH:mm:ss
    // ^(19[0-9]{2}|2[0-9]{3}).(0[1-9]|1[012]).([123]0|[012][1-9]|31)
    // ([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$

    // yyMMdd HHmmss
    // ^([0-9]{2})(0[1-9]|1[012])([123]0|[012][1-9]|31)
    // ([01][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$

    // HH:mm:ss
    // ^([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$

    // HH:mm
    // ^([0-1][0-9]|2[0-3]):[0-5][0-9]$

    private static Pattern DATE_PATTERN1 = Pattern
            .compile("^(19[0-9]{2}|2[0-9]{3})-(0[1-9]|1[012])-([123]0|[012][1-9]|31)$");
    private static Pattern DATE_PATTERN2 = Pattern.compile(
            "^(19[0-9]{2}|2[0-9]{3})-(0[1-9]|1[012])-([123]0|[012][1-9]|31) ([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$");
    private static Pattern DATE_PATTERN3 = Pattern.compile(
            "^(19[0-9]{2}|2[0-9]{3}).(0[1-9]|1[012]).([123]0|[012][1-9]|31) ([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$");
    private static Pattern DATE_PATTERN4 = Pattern
            .compile("^([0-9]{2})(0[1-9]|1[012])([123]0|[012][1-9]|31) ([01][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$");
    private static Pattern DATE_PATTERN5 = Pattern
            .compile("^([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$");
    private static Pattern DATE_PATTERN6 = Pattern
            .compile("^([0-1][0-9]|2[0-3]):[0-5][0-9]$");

    private static String format1 = "yyyy.MM.dd";
    private static String format2 = "yyyy.MM.dd HH:mm:ss";
    private static String format3 = "yyyy.MM.dd HH:mm:ss";
    private static String format4 = "yyMMdd HHmmss";
    private static String format5 = "HH:mm:ss";
    private static String format6 = "HH:mm";

    /**
     * 입력된 날짜의 형식을 검사하여 formatter String 을 리턴한다.
     */
    @Override
    public String matchedFormat(String date) {
        String result_format = "";

        if (DATE_PATTERN1.matcher(date).matches()) {
            result_format = format1;
        } else if (DATE_PATTERN2.matcher(date).matches()) {
            result_format = format2;
        } else if (DATE_PATTERN3.matcher(date).matches()) {
            result_format = format3;
        } else if (DATE_PATTERN4.matcher(date).matches()) {
            result_format = format4;
        } else if (DATE_PATTERN5.matcher(date).matches()) {
            result_format = format5;
        } else if (DATE_PATTERN6.matcher(date).matches()) {
            result_format = format6;
        }
        else {
            // exception
            result_format = "";
        }
        return result_format;
    }

}
