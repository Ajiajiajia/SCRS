package cn.abtion.neuqercc.team.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.home.models.ContestListModel;
import cn.abtion.neuqercc.team.models.SearchResultTeamModel;

/**
 * @author lszr
 * @since 2017/11/28 下午7:56
 * email wsyglszr@gmail.com
 */

public class SearchTeamResultAdapter extends BaseRecyclerViewAdapter {


    public SearchTeamResultAdapter(Context context, List<SearchResultTeamModel> searchResultTeamModels) {
        super(context, searchResultTeamModels);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_rec_all_team, parent, false);
        return new ItemHolder(view);
    }

    static class ItemHolder extends ViewHolder<SearchResultTeamModel> {
        @BindView(R.id.txt_team_name)
        TextView txtTeamName;
        @BindView(R.id.txt_contest_name)
        TextView txtContestName;
        @BindView(R.id.txt_want)
        TextView txtWant;
        @BindView(R.id.txt_declaration)
        TextView txtDeclaration;


        ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(SearchResultTeamModel searchResultTeamModel) {
            txtTeamName.setText(searchResultTeamModel.getTeam_name() == null ? "N/A" : searchResultTeamModel.getTeam_name());
            txtContestName.setText(searchResultTeamModel.getCompetition_desc() == null ? "N/A" : searchResultTeamModel.getCompetition_desc());
            txtDeclaration.setText(searchResultTeamModel.getDeclaration() == null ? "N/A" : searchResultTeamModel.getDeclaration());
            txtWant.setText(searchResultTeamModel.getGood_at() == null ? "N/A" : searchResultTeamModel.getGood_at());
        }

    }
}
