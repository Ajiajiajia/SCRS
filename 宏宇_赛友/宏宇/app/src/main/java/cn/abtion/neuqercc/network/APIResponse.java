package cn.abtion.neuqercc.network;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * class for get network response.
 * @author abtion.
 * @since 17/9/22 18:03.
 * email caiheng@hrsoft.net
 */

public class APIResponse<T> extends BaseModel {
    private int code = -2;
    private T data;
    private String msg="";

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
