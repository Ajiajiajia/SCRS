package cn.abtion.neuqercc.rain.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * Created by heaijia on 2017/12/29.
 */

public class InitAllTeamDateResponse<T> extends BaseModel {
    private int totalCount;
    private int page;

    public InitAllTeamDateResponse() {
    }

    private T item;



    public void setItem(T item){
        this.item=item;
    }

    public void setTotalCount(){
        this.totalCount=totalCount;
    }

    public void setPage(int page){
        this.page=page;
    }

    public T getItem(){
        return item;
    }

    public int getToltalCount() {

        return totalCount;
    }

    public int getPage() {
        return page;
    }




}
