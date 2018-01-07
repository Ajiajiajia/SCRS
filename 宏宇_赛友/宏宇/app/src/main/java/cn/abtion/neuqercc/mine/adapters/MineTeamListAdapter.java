package cn.abtion.neuqercc.mine.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.mine.models.MineTeamListResponse;

/**
 * @author fhyPayaso
 * @since 2017/11/21 13:28
 * email fhyPayaso@qq.com
 */

public class MineTeamListAdapter extends BaseRecyclerViewAdapter<MineTeamListResponse> {


    public MineTeamListAdapter(Context context, List<MineTeamListResponse> teamListModels) {

        super(context, teamListModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rec_mine_team, parent, false);
        return new ItemHolder(view);
    }


    static class ItemHolder extends ViewHolder<MineTeamListResponse> {

        @BindView(R.id.txt_team_name)
        TextView txtTeamName;
        @BindView(R.id.team_number)
        TextView txtTeamNumber;
        @BindView(R.id.team_position)
        TextView txtTeamPosition;
        @BindView(R.id.txt_team_species)
        TextView txtTeamSpecies;

        ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(MineTeamListResponse mineTeamListModel) {

            txtTeamName.setText(mineTeamListModel.getTeamName() == null ? "N/A" : mineTeamListModel.getTeamName());
            txtTeamSpecies.setText(mineTeamListModel.getCompetitionDesc() == null ? "N/A" : mineTeamListModel.getCompetitionDesc());
            txtTeamNumber.setText(String.valueOf(mineTeamListModel.getTeamMemberNum()));


        }

    }


}
