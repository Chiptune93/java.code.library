import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import com.ibm.icu.util.ChineseCalendar; // for solart to lunar

public class DateUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 넘어온 날짜 기준 주 시작일 반환
     * 
     * @param now_date
     * @return
     */
    public static String weekStart(String now_date, int pastCnt) {
        Date date = new Date();
        try {
            date = sdf.parse(now_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        cal.setTime(date);
        if (pastCnt > 0) {
            cal.add(Calendar.DAY_OF_MONTH, (-7 * pastCnt));
        }
        cal.add(Calendar.DATE, 2 - cal.get(Calendar.DAY_OF_WEEK));
        return sdf.format(cal.getTime());
    }

    /**
     * 넘어온 날짜 기준 주 종료일 반환
     * 
     * @param now_date
     * @return
     */
    public static String weekEnd(String now_date, int pastCnt) {
        Date date = new Date();
        try {
            date = sdf.parse(now_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        cal.setTime(date);
        if (pastCnt > 0) {
            cal.add(Calendar.DAY_OF_MONTH, (-7 * pastCnt));
        }
        cal.add(Calendar.DATE, 8 - cal.get(Calendar.DAY_OF_WEEK));
        return sdf.format(cal.getTime());
    }

    /**
     * 넘어온 날짜 기준 월 시작일 반환
     * 
     * @param now_date
     * @return
     */
    public static String monthStart(String now_date, int pastCnt) {
        Date date = new Date();
        try {
            date = sdf.parse(now_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        cal.setTime(date);
        if (pastCnt > 0) {
            cal.add(Calendar.MONTH, (-1 * pastCnt));
        }
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
        return sdf.format(cal.getTime());
    }

    /**
     * 넘어온 날짜 기준 월 종료일 반환
     * 
     * @param now_date
     * @return
     */
    public static String monthEnd(String now_date, int pastCnt) {
        Date date = new Date();
        try {
            date = sdf.parse(now_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        cal.setTime(date);
        if (pastCnt > 0) {
            cal.add(Calendar.MONTH, (-1 * pastCnt));
        }
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(cal.getTime());
    }

    /**
     * 넘어온 날짜 기준 요일 반환
     * 
     * @param now_date
     * @return
     */
    public static String getDateString(String now_date) {
        String dateString = "";
        Date date = new Date();
        try {
            date = sdf.parse(now_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        cal.setTime(date);
        int dateNumber = cal.get(Calendar.DAY_OF_WEEK);
        if (dateNumber == 1) {
            dateString = "일";
        } else if (dateNumber == 2) {
            dateString = "월";
        } else if (dateNumber == 3) {
            dateString = "화";
        } else if (dateNumber == 4) {
            dateString = "수";
        } else if (dateNumber == 5) {
            dateString = "목";
        } else if (dateNumber == 6) {
            dateString = "금";
        } else if (dateNumber == 7) {
            dateString = "토";
        } else {
            dateString = "";
        }
        return dateString;
    }

    /**
     * 넘어온 날짜를 넘어온 포맷 형식으로 바꾸어주는 메소드
     * 
     * @param now_date
     * @param format
     * @return
     */
    public static String changeFormat(String now_date, String format) {
        if (now_date == null || now_date.equals(""))
            return "";
        Date date = new Date();
        try {
            date = sdf.parse(now_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat(format);
        String new_date = sdf2.format(date);
        return new_date;
    }

    /**
     * 넘어온 날짜를 넘어온 포맷 형식으로 바꾸어주는 메소드
     * 
     * @param now_date
     * @param format
     * @return
     */
    public static String changeDtFormat(String now_date, String format) {
        System.out.println(now_date);
        System.out.println(now_date);
        if (now_date == null || now_date.equals(""))
            return "";
        Date date = new Date();
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            date = sdf3.parse(now_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat(format);
        String new_date = sdf2.format(date);
        return new_date;
    }

    /**
     * 날짜 두개를 받아 사이 일/수 반주/월 환
     * 
     * @param start_date
     * @param finish_date
     * @param type        [ d : 일수 , w : 주수 , m : 월수]
     * @return
     */
    public static int diffDate(String start_date, String finish_date, String type) {
        Calendar sd = Calendar.getInstance();
        Calendar ed = Calendar.getInstance();
        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = sdf.parse(start_date);
            date2 = sdf.parse(finish_date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sd.setTime(date1);
        ed.setTime(date2);
        long d1, d2;
        d1 = sd.getTime().getTime();
        d2 = ed.getTime().getTime();
        long result = (d2 - d1) / (1000 * 60 * 60 * 24);
        System.out.println(result);
        if (type.equals("w")) {
            if (result / 7 < 1)
                result = 1;
            // 주 계산 예외처리 ( +1 )
            result += 1;
            result /= 7;
        } else if (type.equals("m")) {
            sd.setTime(date1);
            ed.setTime(date2);
            int sy = sd.get(Calendar.YEAR);
            int ey = ed.get(Calendar.YEAR);

            int sm = sd.get(Calendar.MONTH) + 1;
            int em = ed.get(Calendar.MONTH) + 1;

            int diff = (ey - sy) * 12 + (em - sm);
            // 월 수 계산 예외처리
            result = diff + 1;
        } else {
            // nothing to do.
        }
        return (int) result;

    }

    /**
     * 포맷에 맞게 현재일자 반환.
     * 
     * @param format
     * @return
     */
    public static String getNow(String format) {
        Date time = new Date();
        String result = "";
        if (format.isEmpty()) {
            result = sdf.format(time);
        } else {
            SimpleDateFormat format_ = new SimpleDateFormat(format);
            result = format_.format(time);
        }
        return result;
    }

    /**
     * 포맷에 맞게 현재일자에서 특정날짜 뺀 날짜를 반환.
     * 
     * @param format
     * @param diff
     * @return
     */
    public static String getBefore(String format, int diff) {
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        cal.setTime(new Date());
        System.out.println(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, diff);
        System.out.println(cal.getTime());
        String result = "";
        if (format.isEmpty()) {
            result = sdf.format(cal.getTime());
        } else {
            SimpleDateFormat format_ = new SimpleDateFormat(format);
            result = format_.format(cal.getTime());
        }
        return result;
    }

    /**
     * 두 날짜를 입력 받아 사이 일을 리스트로 반환
     * 
     * @param start_date
     * @param end_date
     * @return
     * @throws ParseException
     */
    public static List<LinkedHashMap<String, String>> getDiffList(String start_date, String end_date)
            throws ParseException {
        List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        Date date1 = new Date();
        date1 = sdf.parse(start_date);
        cal.setTime(date1);
        int cnt = DateUtil.diffDate(start_date, end_date, "d");
        for (int i = 0; i < cnt; i++) {
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
            map.put("date", sdf.format(cal.getTime()));
            int dateNumber = cal.get(Calendar.DAY_OF_WEEK);
            if (dateNumber == 1) {
                map.put("day", "일");
                map.put("dow", "7");
            } else if (dateNumber == 2) {
                map.put("day", "월");
                map.put("dow", "1");
            } else if (dateNumber == 3) {
                map.put("day", "화");
                map.put("dow", "2");
            } else if (dateNumber == 4) {
                map.put("day", "수");
                map.put("dow", "3");
            } else if (dateNumber == 5) {
                map.put("day", "목");
                map.put("dow", "4");
            } else if (dateNumber == 6) {
                map.put("day", "금");
                map.put("dow", "5");
            } else if (dateNumber == 7) {
                map.put("day", "토");
                map.put("dow", "6");
            } else {
                map.put("day", "");
                map.put("dow", "");
            }
            list.add(map);
            cal.add(Calendar.DATE, 1);
        }
        return list;
    }

    /**
     * 해당 월을 1일 ~ 마지막 일 까지 리스트로 반환
     * 
     * @param year
     * @param month
     * @return
     * @throws ParseException
     */
    public static List<LinkedHashMap<String, String>> getDateListInYearMonth(int year, int month)
            throws ParseException {
        List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        cal.set(year, month - 1, 1);
        int cnt = cal.getMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < cnt; i++) {
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
            map.put("date", sdf.format(cal.getTime()));
            int date = cal.get(Calendar.DAY_OF_MONTH);
            map.put("dt", Integer.toString(date));
            int dateNumber = cal.get(Calendar.DAY_OF_WEEK);
            if (dateNumber == 1) {
                map.put("day", "일");
            } else if (dateNumber == 2) {
                map.put("day", "월");
            } else if (dateNumber == 3) {
                map.put("day", "화");
            } else if (dateNumber == 4) {
                map.put("day", "수");
            } else if (dateNumber == 5) {
                map.put("day", "목");
            } else if (dateNumber == 6) {
                map.put("day", "금");
            } else if (dateNumber == 7) {
                map.put("day", "토");
            } else {
                map.put("day", "");
            }
            list.add(map);
            cal.add(Calendar.DATE, 1);
        }
        return list;
    }

    /**
     * 현재 날짜:시간을 원하는 정보로 파싱하여 리턴
     * 
     * @param type
     * @return
     */
    public static String getNowOfType(String type) {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String datestr = sdf.format(cal.getTime());
        // type
        // year : 연도
        // month : 달
        // day : 날짜
        // time : 시간
        if (type.equals("year")) {
            return datestr.substring(0, 4);
        } else if (type.equals("month")) {
            return datestr.substring(5, 7);
        } else if (type.equals("day")) {
            return datestr.substring(8, 10);
        } else if (type.equals("time")) {
            return datestr.substring(11, 18);
        } else {
            return "";
        }
    }

    /**
     * 현재 날짜:시간을 int형태로 리턴
     * 
     * @param type
     * @return
     */
    public static int getNowOfTypeInt(String type) {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal);
        // type
        // year : 연도
        // month : 달
        // day : 날짜
        // time : 시간
        if (type.equals("year")) {
            return cal.get(Calendar.YEAR);
        } else if (type.equals("month")) {
            return cal.get(Calendar.MONTH) + 1;
        } else if (type.equals("day")) {
            return cal.get(Calendar.DAY_OF_MONTH);
        } else {
            return 0;
        }
    }

    /**
     * 양력을 음력으로 바꾸어 줌.
     * 
     * @param date
     * @return
     */
    public static String converSolarToLunar(String date) {
        ChineseCalendar cc = new ChineseCalendar();
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));

        cc.setTimeInMillis(cal.getTimeInMillis());

        int y = cc.get(ChineseCalendar.EXTENDED_YEAR) - 2637;
        int m = cc.get(ChineseCalendar.MONTH) + 1;
        int d = cc.get(ChineseCalendar.DAY_OF_MONTH);

        StringBuffer ret = new StringBuffer();
        ret.append(String.format("%04d", y));
        ret.append(String.format("%02d", m));
        ret.append(String.format("%02d", d));

        return ret.toString();
    }

    /**
     * 입력받은 시간부터 현재까지 얼마나 지났는지 구함.
     * 
     * @param dateTime
     * @param type
     * @return
     */
    public static String passTime(String dateTime) {
        LocalDateTime ldc = TimeUtil.stringToLocalDateTime(dateTime);
        LocalDateTime ldc_ = TimeUtil.convertTimeZoneFromUtc(ldc, "Asia/Seoul");
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        int diff = Long.valueOf(ChronoUnit.SECONDS.between(ldc_, now)).intValue();

        int hour = diff / 3600;
        int minute = (diff % 3600) / 60;
        int second = diff % 60;

        String h = (hour < 10 ? "0" : "") + Integer.toString(hour);
        String m = (minute < 10 ? "0" : "") + Integer.toString(minute);
        String s = (second < 10 ? "0" : "") + Integer.toString(second);
        String passTime = h + ":" + m + ":" + s;

        return passTime;
    }

    /**
     * timezone convert
     * 
     * @param time
     * @param fromZone
     * @param toZone
     * @param dateFormat
     * @return
     */
    public static String timeZoneConvert(String time, String fromZone, String toZone, String dateFormat) {
        String dateInString = time;
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(dateFormat));

        ZoneId fromZoneId = ZoneId.of(fromZone);

        ZonedDateTime fromTime = ldt.atZone(fromZoneId);

        ZoneId toZoneId = ZoneId.of(toZone);

        ZonedDateTime toZoneTime = fromTime.withZoneSameInstant(toZoneId);

        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat);
        return format.format(toZoneTime);
    }

    /**
     * 입력받은 날짜의 연/월/일/주차/요일 구하기
     * 
     * @param time
     * @param format
     * @param kind
     * @return
     * @throws ParseException
     */
    public static String getCalendarVal(String time, String format, String kind) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date date = df.parse(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (kind.equals("y")) {
            return Integer.toString(cal.get(Calendar.YEAR));
        } else if (kind.equals("m")) {
            int m = cal.get(Calendar.MONTH) + 1;
            if (m < 10) {
                return "0" + Integer.toString(cal.get(Calendar.MONTH) + 1);
            } else {
                return Integer.toString(cal.get(Calendar.MONTH) + 1);
            }
        } else if (kind.equals("d")) {
            int d = cal.get(Calendar.DATE);
            if (d < 10) {
                return "0" + Integer.toString(cal.get(Calendar.DATE));
            } else {
                return Integer.toString(cal.get(Calendar.DATE));
            }
        } else if (kind.equals("wc")) {
            return DateUtil.getCurrentWeekOfMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.DATE));
        } else if (kind.equals("dow")) {
            int dow = cal.get(Calendar.DAY_OF_WEEK);
            return Integer.toString(dow);
        } else {
            return "";
        }
    }

    /**
     * 해당 날짜의 주차를 반환
     * 
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getCurrentWeekOfMonth(int year, int month, int day) {
        int subtractFirstWeekNumber = subWeekNumberIsFirstDayAfterThursday(year, month, day);
        int subtractLastWeekNumber = addMonthIsLastDayBeforeThursday(year, month, day);

        // 마지막 주차에서 다음 달로 넘어갈 경우 다음달의 1일을 기준으로 정해준다.
        // 추가로 다음 달 첫째주는 목요일부터 시작하는 과반수의 일자를 포함하기 때문에 한주를 빼지 않는다.
        if (subtractLastWeekNumber > 0) {
            day = 1;
            subtractFirstWeekNumber = 0;
        }

        if (subtractFirstWeekNumber < 0) {
            Calendar calendar = Calendar.getInstance(Locale.KOREA);
            calendar.set(year, month - 1, day);
            calendar.add(Calendar.DATE, -1);

            return getCurrentWeekOfMonth(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH) + 1),
                    calendar.get(Calendar.DATE));
        }

        Calendar calendar = Calendar.getInstance(Locale.KOREA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(1);
        calendar.set(year, month - (1 - subtractLastWeekNumber), day);

        int dayOfWeekForFirstDayOfMonth = calendar.get(Calendar.WEEK_OF_MONTH) - subtractFirstWeekNumber;

        return Integer.toString(dayOfWeekForFirstDayOfMonth);
    }

    /**
     * 파라미터로 전달 된 날짜의 1일의 주차 계산 1일이 목요일(5) 보다 클 경우 첫째 주 이므로 0을 반환 1일이 월 ~ 목 이외의 날짜 일
     * 경우 -1 을 반환. 1일이 목요일(5) 보다 작으면 첫째 주가 아니므로 1을 반환
     * 
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int subWeekNumberIsFirstDayAfterThursday(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance(Locale.KOREA);
        calendar.set(year, month - 1, day);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        int weekOfDay = calendar.get(Calendar.DAY_OF_WEEK);

        if ((weekOfDay >= Calendar.MONDAY) && (weekOfDay <= Calendar.THURSDAY)) {
            return 0;
        } else if (day == 1 && (weekOfDay < Calendar.MONDAY || weekOfDay > Calendar.TUESDAY)) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * 해당 날짜가 마지막 주에 해당하고 마지막주의 마지막날짜가 목요일보다 작으면 다음달로 넘겨야 한다 +1 목요일보다 크거나 같을 경우 이번달로
     * 결정한다 +0
     * 
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int addMonthIsLastDayBeforeThursday(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance(Locale.KOREA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(year, month - 1, day);

        int currentWeekNumber = calendar.get(Calendar.WEEK_OF_MONTH);
        int maximumWeekNumber = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);

        if (currentWeekNumber == maximumWeekNumber) {
            calendar.set(year, month - 1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            int maximumDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            if (maximumDayOfWeek < Calendar.THURSDAY && maximumDayOfWeek > Calendar.SUNDAY) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * 특정 날짜가 기간 내에 포함 되는지 리턴
     * 
     * @param date
     * @param startDate
     * @param finishDate
     * @return
     */
    public static boolean includePeriod(String date, String startDate, String finishDate) {
        LocalDate st = TimeUtil.stringToLocalDate(startDate);
        LocalDate ed = TimeUtil.stringToLocalDate(finishDate);
        LocalDate dd = TimeUtil.stringToLocalDate(date);

        if ((dd.isAfter(st) || dd.equals(st)) && (dd.isBefore(ed) || dd.isEqual(ed))) {
            return true;
        } else {
            return false;
        }
    }

}
