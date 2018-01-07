package cn.abtion.neuqercc.mine.activities;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.mine.adapters.MineTeamListAdapter;
import cn.abtion.neuqercc.mine.models.MineTeamListResponse;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author fhyPayaso
 * @since 2017/11/21 12:50
 * email fhyPayaso@qq.com
 */

public class MineTeamListActivity extends ToolBarActivity {

    @BindView(R.id.rec_mine_team)
    RecyclerView recMineTeam;

    private List<MineTeamListResponse> mineTeamListResponseList = new ArrayList<MineTeamListResponse>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_team_list;
    }

    @Override
    protected void initVariable() {

        setActivityTitle(getString(R.string.title_mine_team));
        initMineTeamList();

    }

    @Override
    protected void initView() {


    }

    @Override
    protected void loadData() {

    }

    public void initMineTeamList() {

        RestClient.getService().mineTeamList(LoginActivity.phoneNumber).enqueue(new DataCallback<APIResponse<List<MineTeamListResponse>>>() {

            @Override
            public void onDataResponse(Call<APIResponse<List<MineTeamListResponse>>> call, Response<APIResponse<List<MineTeamListResponse>>> response) {

                mineTeamListResponseList = response.body().getData();
                initContestRecyclerView();

            }

            @Override
            public void onDataFailure(Call<APIResponse<List<MineTeamListResponse>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }

        });


    }


    public void initContestRecyclerView() {


        recMineTeam.setNestedScrollingEnabled(false);
        MineTeamListAdapter mineTeamListAdapter = new MineTeamListAdapter(MineTeamListActivity.this, mineTeamListResponseList);
        recMineTeam.setAdapter(mineTeamListAdapter);
        recMineTeam.setLayoutManager(new LinearLayoutManager(MineTeamListActivity.this, LinearLayoutManager.VERTICAL, false));

        //点击事件
        mineTeamListAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<MineTeamListResponse>() {

            @Override
            public void onItemClicked(MineTeamListResponse mineTeamListModel, BaseRecyclerViewAdapter.ViewHolder viewHolder) {

                Intent intent = new Intent(MineTeamListActivity.this, MineTeamIfromationActivity.class);
                intent.putExtra("teamId", mineTeamListResponseList.get(viewHolder.getAdapterPosition()).getTeamId());
                startActivity(intent);

            }
        });
    }

}
