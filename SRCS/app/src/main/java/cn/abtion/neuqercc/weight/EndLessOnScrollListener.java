package cn.abtion.neuqercc.weight;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by heaijia on 2018/1/6.
 */

public abstract class  EndLessOnScrollListener extends RecyclerView.OnScrollListener {



    //主要用来存储上一个totalItemCount
    private int previousTotal = 0;

    //是否正在上拉数据
    private boolean loading = true;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    //当前页，从1开始
    private int currentPage = 1;

    private LinearLayoutManager mLinearLayoutManager;

    public EndLessOnScrollListener(
            LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {

                //说明数据已经加载结束
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading
                && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            //这里需要好好理解
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }


    /**
     * 提供一个抽闲方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     * */

    public abstract void onLoadMore(int currentPage);
}

