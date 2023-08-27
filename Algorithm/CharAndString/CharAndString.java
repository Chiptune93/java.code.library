package CharAndString;

/**
 * 문자열 관련된 내용
 *
 * @author dk
 * @since 2023.06.06
 */
public class CharAndString {
    public static void main(String[] args) {
        int answer = 0;
        String s = "Hello World";
        char[] c1 = "character".toCharArray();
        char c2 = 'A';
        char c3 = 'a';

        // 문자 찾기
        char s1 = s.charAt(0);

        // 대소문자 변환
        boolean s2 = Character.isUpperCase(c3);
        boolean s3 = Character.isLowerCase(c2);

        // 문장 속 단어 구하기, 한 단어씩 자르기
        String s4 = s.substring(0, s.indexOf(' '));

        // 단어 뒤집기
        StringBuilder sb = new StringBuilder();
        sb.append(s).reverse();

        // 특정 단어만 뒤집기
        {
            char[] c = s.toCharArray();
            int lt = 0;
            int rt = s.length() - 1;
            // lt랑 rt는 배열 내 위치를 가리키는 포인터 역할.
            // 서로 증가 / 감소하면서 위치를 가리킴.
            // 포인터가 서로 교차되는 경우 끝나야 하므로
            // lt < rt 조건 걸어놔야 함(같으면 안됨)
            while (lt < rt) {
                if (!Character.isAlphabetic(c[lt]))
                    lt++;
                else if (!Character.isAlphabetic(c[rt]))
                    rt--;
                else {
                    char temp = c[lt];
                    c[lt] = c[rt];
                    c[rt] = temp;
                    lt++;
                    rt--;
                }
            }
        }

        // 중복 문자 제거 -> 문자열이 처음 발견되는 위치 값 구하기
        for (int i = 0; i < s.length(); i++) {
            // index 자리에 있는 문자열이 처음 발견되는 위치가 index와 같다면, 중복없는 문자열이다.
            if (s.indexOf(s.charAt(i)) == i) answer += s.charAt(i);
        }

        // 회문 문자열 (팰린드롬 : 앞에서 읽어도 뒤에서 읽어도 똑같은 문자열)
        // > 입력받은 문자열을 거꾸로 나열. 이후, 비교한다.
        String r = sb.append(s).reverse().toString();

        // 회문 문자열 (팰린드롬 : 앞에서 읽어도 뒤에서 읽어도 똑같은 문자열)
        // 알파벳(대소문자) 아닌 것을 제거
        String t = s.replaceAll("[^A-Za-z]", "");

        // 숫자 또는 문자만 제거 할 때, 판단하기
        boolean b1 = Character.isDigit(c2);
        boolean b2 = Character.isAlphabetic(c2);

        // ASCII Number를 문자로 변환 할 땐 그냥 형변환 사용
        int ascii = 64;
        char ascii_char = (char) ascii;

        // 진법 변환
        int decimal = 10;

        String binary = Integer.toBinaryString(decimal); // 10진수 -> 2진수
        String octal = Integer.toOctalString(decimal); // 10진수 -> 8진수
        String hexaDecimal = Integer.toHexString(decimal); // 10진수 -> 16진수

        System.out.println("10진수 : " + decimal);
        System.out.println("2진수 : " + binary);
        System.out.println("8진수 : " + octal);
        System.out.println("16진수 : " + hexaDecimal);
    }

}



}
