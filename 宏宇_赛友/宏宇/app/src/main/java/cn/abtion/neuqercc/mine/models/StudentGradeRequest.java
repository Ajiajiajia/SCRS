package cn.abtion.neuqercc.mine.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author fhyPayaso
 * @since 2017/12/5 21:27
 * email fhyPayaso@qq.com
 */

public class StudentGradeRequest extends BaseModel {


    private int order;
    private String year;

    public void  StudentGradeRequest() {

    }

    /**
     * 获取年份序号
     * @return
     */

    public int getOrder() {
        return  order;
    }

    public void setOrder(int order) {

        this.order = order;
    }


    /**
     * 获取年份
     * @return
     */
    public String getYear() {
        return year;
    }

    public void setYear(String year) {

        this.year = year;
    }

}
