package cn.abtion.neuqercc.account.models;

import cn.abtion.neuqercc.base.models.BaseModel;


/**
 * @author fhyPayaso
 * @since 2017/11/7 16:40
 * email fhyPayaso@qq.com
 */

public class UpdatePasswordRequest extends BaseModel{


    private String phone;
    private String password;

    public UpdatePasswordRequest(){

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
