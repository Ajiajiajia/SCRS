package cn.abtion.neuqercc.account.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author abtion.
 * @since 17/9/22 23:59.
 * email caiheng@hrsoft.net
 */


public class LoginRequest extends BaseModel {

    private String phone;
    private String password;

    public LoginRequest() {

    }

    public String getIdentifier() {

        return phone;
    }

    public void setIdentifier(String phone) {

        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }


}
