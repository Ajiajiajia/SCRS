package cn.abtion.neuqercc.home.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author lszr
 * @since 2017/11/30 下午9:16
 * email wsyglszr@gmail.com
 */

public class InitCrouselFigureRequest extends BaseModel {
    private int order;
    private String url;

    public InitCrouselFigureRequest() {

    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
