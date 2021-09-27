import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.adt.ACaaS.core.lib.MyMap;

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
    public static List<MyMap> getDiffList(String start_date, String end_date) throws ParseException {
        List<MyMap> list = new ArrayList<MyMap>();
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        Date date1 = new Date();
        date1 = sdf.parse(start_date);
        cal.setTime(date1);
        int cnt = DateUtil.diffDate(start_date, end_date, "d");
        for (int i = 0; i < cnt; i++) {
            MyMap map = new MyMap();
            map.put("date", sdf.format(cal.getTime()));
            list.add(map);
            cal.add(Calendar.DATE, 1);
        }
        return list;
    }

}
