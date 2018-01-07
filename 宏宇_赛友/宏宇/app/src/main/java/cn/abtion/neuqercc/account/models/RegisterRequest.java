package cn.abtion.neuqercc.account.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author lszr
 * @since 2017/10/16 上午1:51
 * email wsyglszr@gmail.com
 */

public class RegisterRequest extends BaseModel {

    private String phone;
    private String password;

    public RegisterRequest(){

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
