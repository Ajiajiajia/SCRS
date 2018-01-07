package cn.abtion.neuqercc.common.exceptions;

import java.io.IOException;

/**
 * throw this exception when get message in response data.
 * @author abtion.
 * @since 17/9/22 17:53.
 * email caiheng@hrsoft.net
 */

public class ResultException extends IOException {
    private int code;
    private String data;
    private String msg;



    public ResultException(int code, String data,String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
