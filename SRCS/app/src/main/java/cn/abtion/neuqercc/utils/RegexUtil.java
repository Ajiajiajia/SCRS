package cn.abtion.neuqercc.utils;

import java.util.regex.Pattern;

import cn.abtion.neuqercc.common.Config;

/**
 * Created by heaijia on 2017/12/20.
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
