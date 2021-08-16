
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

public class JavaUtil {

    /**
     * <pre>
     * 문자형이 null값이거나 공백일시 지정된 값으로 리턴
     * </pre>
     *
     * @param 문자형 본래값,조건불일치 문자 def
     * @return String
     */
    public static String NVL(String str, String def) {
        str = str == null ? "" : str;
        return (str.equals("") ? def : str.trim());
    }

    public static String NVL(Object object, String def) {
        String str = object == null ? "" : object.toString();
        return (str.equals("") ? def : str.trim());
    }

    public static String NVL(int object, String def) {
        return object == 0 ? def : JavaUtil.toString(object);
    }

    public static Object NVL(Object object, Object def) {
        return object == null ? def : object;
    }

    /**
     * <pre>
     * 숫자형이 i가 0 일때 def 값을 리턴함
     * </pre>
     *
     * @param int 본래값 i,int 기본값 def
     * @return int
     */
    public static int NVL(String i, int def) {
        return (i == null ? def : JavaUtil.toInt(i));
    }

    public static int NVL(Object i, int def) {
        return (i == null ? def : JavaUtil.toInt(i));
    }

    public static int NVL(int i, int def) {
        return (i == 0 ? def : i);
    }

    public static long NVL(long i, long def) {
        return (i == 0 ? def : i);
    }

    /**
     * <pre>
     * 인트형을 문자형으로 변화
     * </pre>
     *
     * @param int i
     * @return String i
     */
    public static String toString(Object i) {
        try {
            return String.valueOf(i);
        } catch (Exception e) {
            return "";
        }
    }

    public static String toString(Object i, String def) {
        try {
            return String.valueOf(i);
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * <pre>
     * 문자형을 인트형으로 변화
     * </pre>
     *
     * @param int i
     * @return String i
     */
    public static int toInt(String s) {
        try {
            s = JavaUtil.NVL(s, "0");
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }

    public static int toInt(Long s) {
        return Integer.parseInt(JavaUtil.toString(Math.round(s)));
    }

    public static int toInt(Double s) {
        return Integer.parseInt(JavaUtil.toString(Math.round(s)));
    }

    public static int toInt(Object s) {
        s = JavaUtil.NVL(s, "0");
        return toInt(s.toString());
    }

    public static int toInt(Object s, int def) {
        s = JavaUtil.NVL(s, def);
        return toInt(s.toString());
    }

    public static String[] toStrArray(Object s) {
        String[] arr = null;
        String val = null;
        try {
            arr = (String[]) s;
        } catch (Exception e) {
            try {
                val = (String) s;
                arr = new String[1];
                arr[0] = val;
            } catch (Exception e2) {
                arr = new String[0];
            }
        }
        return arr == null ? new String[0] : arr;
    }

    public static int[] toIntArray(Object s) {
        int[] arr = null;
        try {
            arr = (int[]) s;
        } catch (Exception e) {
            arr = new int[0];
        }
        return arr == null ? new int[0] : arr;
    }

    public static long toLong(String s) {
        try {
            s = JavaUtil.NVL(s, "0");
            if (!empty(s) && isDigit(s))
                return Long.parseLong(s);
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    public static double toDouble(String s) {
        try {
            if (!empty(s))
                return Double.parseDouble(s);
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

    /*
     * public static double toFloat(String s) { try { if (!StringUtils.isEmpty(s))
     * return Float.valueOf(s); } catch (Exception e) { return 0; } return 0; }
     */

    /*
     * public static boolean toBoolean(String s) { try { if
     * (!StringUtils.isEmpty(s)) return Boolean.valueOf(s); } catch (Exception e) {
     * return false; } return false; }
     */

    /**
     * String 이 유효한지 체크한다.
     *
     * @param str 체크할 문자열
     * @return <code>true</code> 유효한 문자열; <code>false</code> 유효하지 않은 문자열
     */
    public static boolean empty(String str) {
        if (str == null || str.length() == 0)
            return true;
        return false;
    }

    public static String camelCaseConverter(String key) {
        String newColumnName = null;
        if (key.indexOf("_") == -1) {
            if (key.matches("(.+)?[A-Z](.+)?") && key.matches("(.+)?[a-z](.+)?"))
                return key;
            return key.toLowerCase();
        } else {
            StringBuffer sb = new StringBuffer();
            boolean isFirst = true;
            StringTokenizer tokenizer = new StringTokenizer(key, "_");
            while (tokenizer.hasMoreTokens()) {
                if (isFirst)
                    sb.append(tokenizer.nextToken().toLowerCase());
                else {
                    sb.append(StringUtils.capitalize(tokenizer.nextToken().toLowerCase()));
                }
                isFirst = false;
            }
            newColumnName = sb.toString();
        }
        return newColumnName;
    }

    public static String unescapeHTML(String values) {
        if (values == null)
            return "";
        values = values.replaceAll("&lt;", "<");
        values = values.replaceAll("&gt;", ">");
        values = values.replaceAll("&amp;", "&");
        values = values.replaceAll("&#38;", "&");
        values = values.replaceAll("&quot;", "\"");
        values = values.replaceAll("&#34;", "\"");
        values = values.replaceAll("&#39;", "'");
        values = values.replaceAll("&#36;", "\\$");
        return values;
    }

    public static String escapeHtml(String values) {
        if (values == null)
            return "";
        values = values.replaceAll("<(no)?script[^>]*>.*?</(no)?script>", "");
        values = values.replaceAll("<style[^>]*>.*</style>", "");
        values = values.replaceAll("&(?![#]?[a-z0-9]+;)", "&#38;");
        values = values.replaceAll("<", "&lt;");
        values = values.replaceAll(">", "&gt;");
        values = values.replaceAll("\"", "&#34;");
        values = values.replaceAll("'", "&#39;");
        values = values.replaceAll("\\$", "&#36;");
        return values;
    }

    public static String removeTags(String str) {
        str = str.replaceAll("(<([^>]+)>)", "");
        str = str.replaceAll("<(no)?script[^>]*>.*?</(no)?script>", "");
        str = str.replaceAll("<style[^>]*>.*</style>", "");
        str = str.replaceAll("\\&nbsp\\;", " ");
        str = str.replaceAll("\r\n|\n|\r|\t", " ");
        str = str.replaceAll("[\\s]+", " ");
        return str.trim();
    }

    /**
     * 넘어온 문자열이 숫자로만 이루어져있는지 체크한다.
     *
     * @param s 체크할 문자열
     * @return <code>true</code> 숫자로만 구성된 경우; <code>false</code> 숫자이외의 문자가 포함된 경우
     */
    public static boolean isDigit(String s) {
        int i, Length;
        if (s == null)
            return false;
        Length = s.length();
        for (i = 0; i < Length; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0')
                return false;
        }
        return true;
    }
}
