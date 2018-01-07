package cn.abtion.neuqercc.mine.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author fhyPayaso
 * @since 2017/12/9 22:00
 * email fhyPayaso@qq.com
 */

public class FeedBackRequest extends BaseModel {


    private String content;

    public void  FeedBackRequest() {

    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;

    }

}
