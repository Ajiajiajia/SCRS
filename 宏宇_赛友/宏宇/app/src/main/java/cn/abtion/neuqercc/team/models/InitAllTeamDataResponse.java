package cn.abtion.neuqercc.team.models;

import java.util.List;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author lszr
 * @since 2017/12/12 下午2:12
 * email wsyglszr@gmail.com
 */

public class InitAllTeamDataResponse<T> extends BaseModel {
    private int toltalCount;
    private int page;

    private T item;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public InitAllTeamDataResponse() {
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


}
