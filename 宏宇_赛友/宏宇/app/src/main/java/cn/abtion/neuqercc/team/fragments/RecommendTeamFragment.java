package cn.abtion.neuqercc.team.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.team.activities.TeamInformationActivity;
import cn.abtion.neuqercc.team.adapters.RecommendTeamListAdapter;
import cn.abtion.neuqercc.team.models.InitRecommendTeamDataResponse;
import cn.abtion.neuqercc.team.models.InitRecommendTeamResponse;
import cn.abtion.neuqercc.team.models.RecommendTeamListModel;
import cn.abtion.neuqercc.widget.EndLessOnScrollListener;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author lszr
 * @since 2017/11/14 下午8:47
 * email wsyglszr@gmail.com
 */

public class RecommendTeamFragment extends BaseFragment {
    @BindView(R.id.recyler_recommend_team)
    RecyclerView recylerRecommendTeam;
    private ArrayList<RecommendTeamListModel> recommendTeamListModels=new ArrayList<>();
    private int page=1;
    private RecommendTeamListAdapter recommendTeamListAdapter;

    private LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend_team;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        processInitRecommendTeam();


    }

    @Override
    protected void loadData() {

    }

    public void processInitRecommendTeam(){

        //弹出progressDialog
        progressDialog.setMessage(getString(R.string.dialog_wait_moment));
        progressDialog.show();

        //网络请求
        RestClient.getService().initRecommendTeam(LoginActivity.phoneNumber,page, Config.size).enqueue(new DataCallback<APIResponse<InitRecommendTeamDataResponse<List<InitRecommendTeamResponse>>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<InitRecommendTeamDataResponse<List<InitRecommendTeamResponse>>>> call, Response<APIResponse<InitRecommendTeamDataResponse<List<InitRecommendTeamResponse>>>> response) {
                recommendTeamListModels=new ArrayList<>();
                for (int i=0;i<response.body().getData().getItem().size();i++){
                    recommendTeamListModels.add(new RecommendTeamListModel(
                            response.body().getData().getItem().get(i).getTeam_name(),
                            response.body().getData().getItem().get(i).getCompetition_desc(),
                            response.body().getData().getItem().get(i).getGood_at(),
                            response.body().getData().getItem().get(i).getDeclaration(),
                            response.body().getData().getItem().get(i).getId()
                    ));
                }
                setRecommendTeamAdapter();
            }

            @Override
            public void onDataFailure(Call<APIResponse<InitRecommendTeamDataResponse<List<InitRecommendTeamResponse>>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {
                if (progressDialog.isShowing()) {
                    disMissProgressDialog();
                }
            }
        });
    }

    public void setRecommendTeamAdapter(){
        recommendTeamListAdapter=new RecommendTeamListAdapter(getContext(),recommendTeamListModels);
        recylerRecommendTeam.setAdapter(recommendTeamListAdapter);
        recylerRecommendTeam.setLayoutManager(linearLayoutManager);

        recylerRecommendTeam.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();
            }
        });

        //点击事件

        recommendTeamListAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<RecommendTeamListModel>() {
            @Override
            public void onItemClicked(RecommendTeamListModel recommendTeamListModel, BaseRecyclerViewAdapter.ViewHolder holder) {
                Intent intent=new Intent(getContext(),TeamInformationActivity.class);

                String gson=new Gson().toJson(recommendTeamListModel);
                intent.putExtra("teamInformation",gson);

                startActivity(intent);
            }
        });
    }

    private void loadMoreData() {
        RestClient.getService().initRecommendTeam(LoginActivity.phoneNumber,++page, Config.size).enqueue(new DataCallback<APIResponse<InitRecommendTeamDataResponse<List<InitRecommendTeamResponse>>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<InitRecommendTeamDataResponse<List<InitRecommendTeamResponse>>>> call, Response<APIResponse<InitRecommendTeamDataResponse<List<InitRecommendTeamResponse>>>> response) {
                if (response.body().getData().getItem()!=null){
                    for (int i=0;i<response.body().getData().getItem().size();i++){
                        recommendTeamListModels.add(new RecommendTeamListModel(
                                response.body().getData().getItem().get(i).getTeam_name(),
                                response.body().getData().getItem().get(i).getCompetition_desc(),
                                response.body().getData().getItem().get(i).getGood_at(),
                                response.body().getData().getItem().get(i).getDeclaration(),
                                response.body().getData().getItem().get(i).getId()
                        ));
                    }
                    recommendTeamListAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onDataFailure(Call<APIResponse<InitRecommendTeamDataResponse<List<InitRecommendTeamResponse>>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });

    }

}
