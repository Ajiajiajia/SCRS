package cn.abtion.neuqercc.cloud.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.cloud.models.HarvestListModel;
import okhttp3.internal.framed.HeadersMode;

/**
 * Created by heaijia on 2017/12/11.
 */

public class HarvestListAdapter extends BaseRecyclerViewAdapter {
    public HarvestListAdapter(Context context, List <HarvestListModel>harvestListModels) {
        super(context, harvestListModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_harvest_template,parent, false);
        return new ItemHolder(view);
    }

    private class ItemHolder extends ViewHolder<HarvestListModel>{

        ItemHolder(View itemview)
        {
            super(itemview);
        }

        @Override
        protected void onBind(HarvestListModel harvestListModel) {

        }

    }
}
