package cn.abtion.neuqercc.mine.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author fhyPayaso
 * @since 2017/12/5 21:39
 * email fhyPayaso@qq.com
 */

public class GoodAtRequest extends BaseModel{


    private int order;
    private String field;

    public void  GoodAtRequest() {

    }

    /**
     * 获取擅长领域序号
     * @return
     */

    public int getOrder() {

        return  order;
    }

    public void setOrder(int order) {

        this.order = order;
    }

    /**
     * 获取擅长领域
     * @return
     */
    public String getField() {
        return field;
    }

    public void setYear(String field) {

        this.field = field;
    }



}
