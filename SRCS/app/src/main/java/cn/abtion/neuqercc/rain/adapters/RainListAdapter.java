package cn.abtion.neuqercc.rain.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.BaseActivity;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.rain.models.RainListModel;

/**
 * Created by heaijia on 2017/12/13.
 */

public class RainListAdapter extends BaseRecyclerViewAdapter<RainListModel>{

    public RainListAdapter(Context context, List<RainListModel> rainListModels) {
        super(context, rainListModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.item_rain_template, parent, false);
        return new ItemsHolder(view);
    }

    private class ItemsHolder extends ViewHolder<RainListModel> {


        ItemsHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RainListModel allTeamListModel) {

        }

    }
}
