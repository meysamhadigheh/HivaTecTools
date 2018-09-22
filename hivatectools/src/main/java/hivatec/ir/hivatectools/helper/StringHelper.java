package hivatec.ir.hivatectools.helper;

import android.util.Patterns;

import java.util.StringTokenizer;
import java.util.regex.Pattern;


public class StringHelper {

    static char[] farsiChars = {'۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'};
    static char[] arabicChars = {1632, 1633, 1634, 1635, 1636, 1637, 1638, 1639, 1640, 1641};

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    public static final Pattern Phone_REGEX =
            Pattern.compile("^0[1-9][0-9]{9}$");
    public static final Pattern Mobile_REGEX =
            Pattern.compile("^0[9][0-9]{9}$");

    public static final Pattern Farsi_REGEX =
            Pattern.compile("^.*[آ-ی].*$");

    /**
     * validate Email
     *
     * @param emailStr
     * @return
     */
    public static boolean validateEmail(String emailStr) {

        return Patterns.EMAIL_ADDRESS.matcher(emailStr).matches();

    }

    /**
     * validate URL
     *
     * @param urlStr
     * @return
     */
    public static boolean validateURL(String urlStr) {

        return Patterns.WEB_URL.matcher(urlStr).matches();
    }

    /**
     * validate Phone
     *
     * @param phoneStr
     * @return
     */
    public static boolean validatePhone(String phoneStr) {
        phoneStr = StringHelper.convertFarsiToEnglishNumberNoChange(phoneStr);
        boolean result = false;
        result = Phone_REGEX.matcher(phoneStr).matches();
        if (phoneStr.length() < 11)
            result = false;
        return result;

    }
    public static boolean validateMobile(String phoneStr) {
        phoneStr = StringHelper.convertFarsiToEnglishNumberNoChange(phoneStr);
        boolean result = false;
        result = Mobile_REGEX.matcher(phoneStr).matches();
        if (phoneStr.length() < 11)
            result = false;
        return result;

    }

    public static boolean isFarsi(String str) {
        if(str == null || str.trim().equals("")){
            return false;
        }

        Boolean result = Farsi_REGEX.matcher(str).matches();
        return result;

    }


    /**
     * convert Numbers
     *
     * @param str
     * @return
     */
    public static String convertEnglishToFarsiNumbers(String str) {

        StringBuilder builder = new StringBuilder();

        if(str == null){
            return "";
        }

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) && ((str.charAt(i)) - 48) < 10) {
                builder.append(farsiChars[(int) (str.charAt(i)) - 48]);
            } else {
                builder.append(str.charAt(i));
            }
        }

        return builder.toString();
    }

    public static String convertFarsiToEnglishNumber(String str) {

        StringBuilder builder = new StringBuilder();

        if(str == null){
            return "";
        }


        for (int i = 0; i < str.length(); i++) {

            char c = str.toCharArray()[i];

            for (int j = 0; j < farsiChars.length; j++) {

                char ac = arabicChars[j];
                char fc = farsiChars[j];

                if (fc == c || ac == c)
                    builder.append(j);
            }

        }

        return builder.toString();
    }



    public static String get3Digits(String value)
    {
        StringTokenizer lst = new StringTokenizer(value, ".");
        String str1 = value;
        String str2 = "";
        if (lst.countTokens() > 1)
        {
            str1 = lst.nextToken();
            str2 = lst.nextToken();
        }
        String str3 = "";
        int i = 0;
        int j = -1 + str1.length();
        if (str1.charAt( -1 + str1.length()) == '.')
        {
            j--;
            str3 = ".";
        }
        for (int k = j;; k--)
        {
            if (k < 0)
            {
                if (str2.length() > 0)
                    str3 = str3 + "." + str2;
                return str3;
            }
            if (i == 3)
            {
                str3 = "," + str3;
                i = 0;
            }
            str3 = str1.charAt(k) + str3;
            i++;
        }
    }

    public static String getPureNumber(String str) {

        str = convertFarsiToEnglishNumber(str);

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {

                builder.append(str.charAt(i));
            }
        }

        return builder.toString();
    }

    public static String convertFarsiToEnglishNumberNoChange(String str) {

        StringBuilder builder = new StringBuilder();

        if(str == null){
            return "";
        }


        for (int i = 0; i < str.length(); i++) {

            char c = str.toCharArray()[i];
            boolean added = false;

            for (int j = 0; j < farsiChars.length; j++) {

                char ac = arabicChars[j];
                char fc = farsiChars[j];

                if (fc == c || ac == c) {
                    builder.append(j);
                    added = true;
                    break;
                }
            }

            if(!added){
                builder.append(c);
            }

        }

        return builder.toString();
    }

    public static String getTomanString(int price){

        return get3Digits(convertEnglishToFarsiNumbers(price + "")) + " " + "تومان";
    }
}
