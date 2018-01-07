package cn.abtion.neuqercc.team.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.team.activities.TeamInformationActivity;
import cn.abtion.neuqercc.team.adapters.AllTeamListAdapter;
import cn.abtion.neuqercc.team.models.AllTeamListModel;
import cn.abtion.neuqercc.team.models.InitAllTeamDataResponse;
import cn.abtion.neuqercc.team.models.InitAllTeamResponse;
import cn.abtion.neuqercc.widget.EndLessOnScrollListener;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author lszr
 * @since 2017/11/14 下午8:42
 * email wsyglszr@gmail.com
 */

public class AllTeamFragment extends BaseFragment {

    @BindView(R.id.recyler_all_team)
    RecyclerView recylerAllTeam;
    private ArrayList<AllTeamListModel> allTeamListModels;
    private static int page=1;

    private AllTeamListAdapter allTeamListAdapter;
    private LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_team;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        porcessInitAllTeam();
    }

    @Override
    protected void loadData() {

    }

    public void setAllTeamAdapter(){

        allTeamListAdapter=new AllTeamListAdapter(getContext(),allTeamListModels);
        recylerAllTeam.setAdapter(allTeamListAdapter);
        recylerAllTeam.setLayoutManager(linearLayoutManager);

        recylerAllTeam.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();
            }
        });


        //点击事件

        allTeamListAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<AllTeamListModel>() {
            @Override
            public void onItemClicked(AllTeamListModel allTeamListModel, BaseRecyclerViewAdapter.ViewHolder holder) {
                Intent intent=new  Intent(getContext(),TeamInformationActivity.class);
                String gson=new Gson().toJson(allTeamListModel);
                intent.putExtra("teamInformation",gson);
                startActivity(intent);
            }
        });

    }

    private void loadMoreData() {
        RestClient.getService().initAllTeam(++page, Config.size).enqueue(new DataCallback<APIResponse<InitAllTeamDataResponse<List<InitAllTeamResponse>>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<InitAllTeamDataResponse<List<InitAllTeamResponse>>>> call, Response<APIResponse<InitAllTeamDataResponse<List<InitAllTeamResponse>>>> response) {
                if(response.body().getData()!=null){
                    allTeamListModels=new ArrayList<>();
                    for (int i=0;i<response.body().getData().getItem().size();i++){
                        allTeamListModels.add(new AllTeamListModel(
                                response.body().getData().getItem().get(i).getTeam_name(),
                                response.body().getData().getItem().get(i).getCompetition_desc(),
                                response.body().getData().getItem().get(i).getGood_at(),
                                response.body().getData().getItem().get(i).getDeclaration(),
                                response.body().getData().getItem().get(i).getId()));
                    }
                    allTeamListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onDataFailure(Call<APIResponse<InitAllTeamDataResponse<List<InitAllTeamResponse>>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {
                if (progressDialog.isShowing()) {
                    disMissProgressDialog();
                }
            }
        });

    }

    public void porcessInitAllTeam(){
        //弹出progressDialog
        progressDialog.setMessage(getString(R.string.dialog_wait_moment));
        progressDialog.show();

        //网络请求
        RestClient.getService().initAllTeam(page, Config.size).enqueue(new DataCallback<APIResponse<InitAllTeamDataResponse<List<InitAllTeamResponse>>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<InitAllTeamDataResponse<List<InitAllTeamResponse>>>> call, Response<APIResponse<InitAllTeamDataResponse<List<InitAllTeamResponse>>>> response) {
                allTeamListModels=new ArrayList<>();
                for (int i=0;i<response.body().getData().getItem().size();i++){
                    allTeamListModels.add(new AllTeamListModel(
                                    response.body().getData().getItem().get(i).getTeam_name(),
                                    response.body().getData().getItem().get(i).getCompetition_desc(),
                                    response.body().getData().getItem().get(i).getGood_at(),
                                    response.body().getData().getItem().get(i).getDeclaration(),
                                    response.body().getData().getItem().get(i).getId()));
                }
                setAllTeamAdapter();

            }

            @Override
            public void onDataFailure(Call<APIResponse<InitAllTeamDataResponse<List<InitAllTeamResponse>>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {
                if (progressDialog.isShowing()) {
                    disMissProgressDialog();
                }
            }
        });


    }

}
