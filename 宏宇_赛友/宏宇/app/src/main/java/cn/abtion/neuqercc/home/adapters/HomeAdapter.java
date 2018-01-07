package cn.abtion.neuqercc.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.home.models.ContestListModel;

/**
 * @author abtion.
 * @since 17/9/25 17:07.
 * email caiheng@hrsoft.net.
 */

public class HomeAdapter extends BaseRecyclerViewAdapter<ContestListModel> {


    public HomeAdapter(Context context, List<ContestListModel> contestListModel) {
        super(context, contestListModel);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rec_home, parent, false);
        return new ItemHolder(view);

    }

    static class ItemHolder extends ViewHolder<ContestListModel> {

        @BindView(R.id.txt_contest_list_title)
        TextView txtContestListTitle;
        @BindView(R.id.txt_contest_list_summary)
        TextView txtContestListSummary;
        @BindView(R.id.txt_contest_list_time_upper)
        TextView txtContestListTimeUpper;
        @BindView(R.id.txt_contest_list_time_lower)
        TextView txtContestListTimeLower;


        ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(ContestListModel contestListModel) {
            txtContestListTitle.setText(contestListModel.getTitle() == null ? "N/A" : contestListModel.getTitle());
            txtContestListSummary.setText(contestListModel.getSummary() == null ? "N/A" : contestListModel.getSummary());
            txtContestListTimeUpper.setText(contestListModel.getContestTime() == null ? "N/A" : contestListModel.getContestTime());
            txtContestListTimeLower.setText(contestListModel.getSignTime() == null ? "N/A" : contestListModel.getSignTime());
        }
    }
}

