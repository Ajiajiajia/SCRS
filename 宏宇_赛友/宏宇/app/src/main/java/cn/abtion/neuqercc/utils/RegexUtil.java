package cn.abtion.neuqercc.utils;

import java.util.regex.Pattern;

import cn.abtion.neuqercc.common.Config;

/**
 * @author abtion.
 * @since 17/11/1 20:44.
 * email caiheng@hrsoft.net.
 */

public class RegexUtil {
    //手机号正则
    public static boolean checkMobile(String phone) {
        return Pattern.matches(Config.MOBILE_REGEX, phone);
    }

    //验证邮箱
    public static boolean checkEmail(String email){
        return Pattern.matches(Config.EMAIL_REGEX,email);
    }
}
