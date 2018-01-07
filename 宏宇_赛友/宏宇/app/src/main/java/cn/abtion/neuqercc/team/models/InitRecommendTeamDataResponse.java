package cn.abtion.neuqercc.team.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author lszr
 * @since 2017/12/12 下午11:24
 * email wsyglszr@gmail.com
 */

public class InitRecommendTeamDataResponse<T> extends BaseModel{
    private int toltalCount;
    private int page;

    private T item;

    public InitRecommendTeamDataResponse() {
    }

    public int getToltalCount() {

        return toltalCount;
    }

    public void setToltalCount(int toltalCount) {
        this.toltalCount = toltalCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
