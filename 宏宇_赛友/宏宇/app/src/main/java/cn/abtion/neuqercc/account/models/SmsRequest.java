package cn.abtion.neuqercc.account.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author fhyPayaso
 * @since 2017/11/7 16:40
 * email fhyPayaso@qq.com
 */

public class SmsRequest extends BaseModel{

    private String phone;

    private String captcha;

    public SmsRequest(){

    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
