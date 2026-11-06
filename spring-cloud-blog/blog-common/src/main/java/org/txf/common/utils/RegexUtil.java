package org.txf.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Slf4j
public class RegexUtil {


    private static String emialRegex = "^[A-Za-z0-9_%+-]+(?:\\.[A-Za-z0-9_%+-]+)*@"
            + "(?:[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?\\.)+[A-Za-z]{2,63}$";


    private static String urlRegex = "(?i)^https?://"
            + "(?:[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\\.)+[a-z]{2,63}"
            + "(?::[0-9]{1,5})?(?:[/?#]\\S*)?$";


    public static boolean checkEmail(String email) {
        if (!StringUtils.hasLength(email)) {
            return false;
        }
        if (!Pattern.matches(emialRegex, email)) {
            return false;
        }
        return true;
    }

    public static boolean checkUrl(String url) {
        if (!StringUtils.hasLength(url)) {
            return false;
        }
        if (!Pattern.matches(urlRegex, url)) {
            return false;
        }
        return true;
    }
}
