package cn.abtion.neuqercc.team.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.team.adapters.AllTeamListAdapter;
import cn.abtion.neuqercc.team.adapters.SearchTeamResultAdapter;
import cn.abtion.neuqercc.team.adapters.TeamMemberListAdapter;
import cn.abtion.neuqercc.team.models.AllTeamListModel;
import cn.abtion.neuqercc.team.models.InitAllTeamResponse;
import cn.abtion.neuqercc.team.models.SearchResultTeamModel;
import cn.abtion.neuqercc.widget.CustomLinearLayoutManager;
import cn.abtion.neuqercc.widget.EndLessOnScrollListener;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author lszr
 * @since 2017/11/28 下午7:21
 * email wsyglszr@gmail.com
 */

public class SearchTeamResultActivity extends ToolBarActivity {
    @BindView(R.id.recyler_search_team_result)
    RecyclerView recylerSearchTeamResult;

    private ArrayList<SearchResultTeamModel> searchResultTeamModels;
    private SearchTeamResultAdapter searchTeamResultAdapter;
    private LinearLayoutManager linearLayoutManager=new CustomLinearLayoutManager(this, CustomLinearLayoutManager.VERTICAL, false);


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_team_result;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_search_result));
        processSearchTeamResult();

    }

    @Override
    protected void loadData() {

    }

    public void processSearchTeamResult(){
        Intent intent=getIntent();
        String searchTeamName=intent.getStringExtra("searchTeamName");

        //弹出progressDialog
        progressDialog.setMessage(getString(R.string.dialog_wait_moment));
        progressDialog.show();

        //网络请求
        RestClient.getService().searchTeam(searchTeamName).enqueue(new DataCallback<APIResponse<List<InitAllTeamResponse>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<List<InitAllTeamResponse>>> call, Response<APIResponse<List<InitAllTeamResponse>>> response) {
                searchResultTeamModels=new ArrayList<>();

                for (int i=0 ;i<response.body().getData().size();i++){
                    searchResultTeamModels.add(new SearchResultTeamModel(
                            response.body().getData().get(i).getId(),
                            response.body().getData().get(i).getTeam_name(),
                            response.body().getData().get(i).getCompetition_desc(),
                            response.body().getData().get(i).getDeclaration(),
                            response.body().getData().get(i).getGood_at()));
                }
                initSearchResultRecylerViewAdapter();
            }

            @Override
            public void onDataFailure(Call<APIResponse<List<InitAllTeamResponse>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {
                if (progressDialog.isShowing()) {
                    disMissProgressDialog();
                }
            }
        });



    }


    public void initSearchResultRecylerViewAdapter(){




        searchTeamResultAdapter = new SearchTeamResultAdapter(this, searchResultTeamModels);

        recylerSearchTeamResult.setLayoutManager(linearLayoutManager);
        recylerSearchTeamResult.setAdapter(searchTeamResultAdapter);



        //点击事件

        searchTeamResultAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<SearchResultTeamModel>() {
            @Override
            public void onItemClicked(SearchResultTeamModel searchResultTeamModel, BaseRecyclerViewAdapter.ViewHolder holder) {
                Intent intent=new Intent(SearchTeamResultActivity.this,TeamInformationActivity.class);

                AllTeamListModel allTeamListModel=new AllTeamListModel(
                        searchResultTeamModel.getTeam_name(),
                        searchResultTeamModel.getCompetition_desc(),
                        searchResultTeamModel.getGood_at(),
                        searchResultTeamModel.getDeclaration(),
                        searchResultTeamModel.getId());

                String gson=new Gson().toJson(allTeamListModel);
                intent.putExtra("teamInformation",gson);

                startActivity(intent);
            }
        });
    }


}
