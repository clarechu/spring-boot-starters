package cn.com.siss.spring.boot.util.other;

import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @author HJ
 */
public class StringUtil extends StringUtils {
    private static char ch[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private static char numCh[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotEmpty(Object str) {
        return !StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return null != str && !"".equals(str.trim());
    }

    /**
     * <p>Checks if the CharSequence contains only Unicode digits.
     * A decimal point is not a Unicode digit and returns false.</p>
     * <p>
     * <p>{@code null} will return {@code false}.
     * An empty CharSequence (length()=0) will return {@code false}.</p>
     * <p>
     * <p>Note that the method does not allow for a leading sign, either positive or negative.
     * Also, if a String passes the numeric test, it may still generate a NumberFormatException
     * when parsed by Integer.parseInt or Long.parseLong, e.g. if the value is outside the range
     * for int or long respectively.</p>
     * <p>
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric("")     = false
     * StringUtils.isNumeric("  ")   = false
     * StringUtils.isNumeric("123")  = true
     * StringUtils.isNumeric("12 3") = false
     * StringUtils.isNumeric("ab2c") = false
     * StringUtils.isNumeric("12-3") = false
     * StringUtils.isNumeric("12.3") = false
     * StringUtils.isNumeric("-123") = false
     * StringUtils.isNumeric("+123") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if only contains digits, and is non-null
     * @since 3.0 Changed "" to return false and not true
     */
    public static boolean isNumeric(final CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取数字与字母组成的随机字符串
     *
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String getNonceStr(Integer length) {
        return random(ch, length);
    }

    /**
     * 获取字符数组中随机组成的随机字符串
     *
     * @param charArr 字符数组
     * @param strLen  字符串长度
     * @return 随机字符串
     */
    public static String random(char[] charArr, int strLen) {
        if (strLen > 0) {
            // 随机数
            Random random = new Random();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < strLen; i++) {
                int num = random.nextInt(charArr.length);
                result.append(charArr[num]);
            }
            return result.toString();
        } else if (strLen == 0) {
            return "";
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 获取由数字组成的随机字符串
     *
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String getNumberNonceStr(Integer length) {
        StringBuilder result = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int num = r.nextInt(numCh.length);
            result.append(numCh[num]);
        }
        return result.toString();
    }

    /**
     * 验证是否为email
     *
     * @param email 需要验证的字符
     * @return boolean
     */
    public static boolean isEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        String emReg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        return email.matches(emReg);
    }

    //首字母小写
    public static String captureName(String name) {
        //     name = name.substring(0, 1).toUpperCase() + name.substring(1);
//        return  name;
        char[] cs = name.toCharArray();
        cs[0] += 32;
        return String.valueOf(cs);

    }


    /**
     * 验证是否为手机号码
     *
     * @param phoneNo 需要验证的字符
     * @return boolean
     */
    public static boolean isMobilePhone(String phoneNo) {
        if (isEmpty(phoneNo)) {
            return false;
        }
        String phReg = "^[1][34578]\\d{9}$";
        return phoneNo.matches(phReg);
    }
}
