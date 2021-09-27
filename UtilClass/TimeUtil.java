import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import matcher.FormattedDateMatcher;

/**
 * @since 2021.09.09
 * @author DK
 * @version 0.1
 * @apiNote for convert time
 */
public class TimeUtil {

    // mathcer 사용
    // m.matchedFormat(time.toString()))
    // 입력된 String date 값의 포맷을 판단하여 String format 리턴.
    private static FormattedDateMatcher m = new FormattedDateMatcher();

    // 변환 시, 기본으로 사용할 포맷
    // date를 받는 것이 아닌 경우(다른형태에서 변환) 사용할 포맷정의
    private static String DF_DATE = "yyyy.MM.dd HH:mm:ss";
    private static String DF_TIME = "HH:mm:ss";
    private static SimpleDateFormat df_d = new SimpleDateFormat(DF_DATE);
    private static DateTimeFormatter dtf_d = DateTimeFormatter.ofPattern(DF_DATE);
    private static SimpleDateFormat df_t = new SimpleDateFormat(DF_TIME);

    /**
     * date -> millisecond
     * 
     * @param date
     * @return
     */
    public static long dateToMill(String date) {
        long mill = 12345678910L;
        try {
            Date d = df_d.parse(date);
            mill = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mill;
    }

    /**
     * millisecond -> date
     * 
     * @param mill
     * @return
     */
    public static String millToDate(long mill) {
        String date;
        Date d = new Date(mill);
        date = df_d.format(d);
        return date;
    }

    /**
     * time -> millisecond
     * 
     * @param time
     * @return
     */
    public static long timeToMill(String time) {
        long mill = 12345678910L;
        try {
            Date d = df_t.parse(time);
            mill = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mill;
    }

    /**
     * millisecond -> date
     * 
     * @param mill
     * @return
     */
    public static String millToTime(long mill) {
        String date;
        Date d = new Date(mill);
        date = df_t.format(d);
        return date;
    }

    /**
     * String --> LocalDateTime
     * 
     * @param date
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String date) {
        LocalDateTime ldt = LocalDateTime.parse(date, dtf_d);
        return ldt;
    }

    /**
     * LocalDateTime --> String
     * 
     * @param ldt
     * @return
     */
    public static String localDateTimeToString(LocalDateTime ldt) {
        return ldt.format(dtf_d);
    }

    /**
     * LocalDateTime --> milliseconds
     * 
     * @param ldt
     * @return
     */
    public static long localDateTimeToMill(LocalDateTime ldt) {
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.of("UTC"));
        long date = zdt.toInstant().toEpochMilli();
        return date;
    }

    /**
     * millisecond --> LocalDateTime
     * 
     * @param mill
     * @return
     */
    public static LocalDateTime millToLocalDateTime(long mill) {
        Instant instant = Instant.ofEpochMilli(mill);
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return date;
    }

    /**
     * HHmm --> LocalTime
     * 
     * @param hhmm
     * @return
     */
    public static LocalTime hhmmToLocalTime(String hhmm) {
        return LocalTime.parse(hhmm);
    }

    /**
     * LocalTime --> HHmm
     * 
     * @param time
     * @return
     */
    public static String LocalTimeToHhmm(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern(m.matchedFormat(time.toString())));
    }

    /**
     * LocalDateTime Convert TimeZone --> new TimeZone
     * 
     * @param ldc
     * @param timezone
     * @return
     */
    public static LocalDateTime convertTimeZone(LocalDateTime ldc, String timezone) {
        return ldc.atZone(ZoneId.of("Asia/Seoul")).withZoneSameInstant(ZoneId.of(timezone)).toLocalDateTime();
    }

}
