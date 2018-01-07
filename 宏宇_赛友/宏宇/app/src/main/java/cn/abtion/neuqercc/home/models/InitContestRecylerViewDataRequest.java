package cn.abtion.neuqercc.home.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author lszr
 * @since 2017/12/9 下午10:02
 * email wsyglszr@gmail.com
 */

public class InitContestRecylerViewDataRequest<T> extends BaseModel {


    private int totalCount;
    private int page;
    private T item;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
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

    public InitContestRecylerViewDataRequest() {
    }


}
