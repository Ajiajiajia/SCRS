package cn.abtion.neuqercc.snow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.snow.models.AllTeamListModel;

/**
 * Created by heaijia on 2018/1/5.
 */

public class AllTeamListAdapter extends BaseRecyclerViewAdapter<AllTeamListModel> {


    public AllTeamListAdapter(Context context, List<AllTeamListModel> allTeamListModels) {
        super(context, allTeamListModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rec_all_team, parent, false);
        return new ItemHolder(view);
    }

    static class ItemHolder extends ViewHolder<AllTeamListModel>{
        @BindView(R.id.txt_team_name)
        TextView txtTeamName;
        @BindView(R.id.txt_contest_name)
        TextView txtContestName;
        @BindView(R.id.txt_want)
        TextView txtWant;
        @BindView(R.id.txt_declaration)
        TextView txtDeclaration;


        ItemHolder(View itemView)
        {
            super(itemView);

        }

        @Override
        protected void onBind(AllTeamListModel allTeamListModel) {
            txtTeamName.setText(allTeamListModel.getTeamName() == null ? "N/A" : allTeamListModel.getTeamName());
            txtContestName.setText(allTeamListModel.getContestName()==null?"N/A":allTeamListModel.getContestName());
            txtDeclaration.setText(allTeamListModel.getDeclaration()==null?"N/A":allTeamListModel.getDeclaration());
            txtWant.setText(allTeamListModel.getWantDirection()==null?"N/A":allTeamListModel.getWantDirection());

        }


    }
}
