package cn.abtion.neuqercc.cloud.adapters;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.cloud.models.HarvestListModel;
import cn.abtion.neuqercc.cloud.models.ImprovementListModel;

/**
 * Created by heaijia on 2017/12/11.
 */

public class ImProvementListAdapter extends BaseRecyclerViewAdapter {

    private class ItemHolder extends ViewHolder<ImprovementListModel>{

        ItemHolder(View itemview){
            super(itemview);
        }

        @Override
        protected void onBind(ImprovementListModel improvementListModel) {

        }
    }


    public ImProvementListAdapter(Context context, List<ImprovementListModel> improvementListModels) {
        super(context, improvementListModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_improvement_template,parent, false);
        return new ItemHolder(view);
    }
}
