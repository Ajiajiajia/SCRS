package cn.abtion.neuqercc.snow.fragments;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.BaseActivity;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.rain.models.InitAllTeamDateResponse;
import cn.abtion.neuqercc.rain.models.InitAllTeamResponse;
import cn.abtion.neuqercc.snow.adapters.AllTeamListAdapter;
import cn.abtion.neuqercc.snow.models.AllTeamListModel;
import cn.abtion.neuqercc.weight.EndLessOnScrollListener;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by heaijia on 2017/12/10.
 */

public class SnowFragment extends BaseFragment {

    @BindView(R.id.recyler_all_team)
    RecyclerView recylerAllTeam;

    private ArrayList<AllTeamListModel>allTeamListModels;



    private static int page=1;

    private AllTeamListAdapter allTeamListAdapter;

    private LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_snow;
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


    public void porcessInitAllTeam(){

        /**
         * 网络请求
         */

        RestClient.getService().initAllTeam(page, Config.size).enqueue(new DataCallback< APIResponse< InitAllTeamDateResponse< List< InitAllTeamResponse>>>>(){

            @Override
            public void onDataResponse(Call<APIResponse<InitAllTeamDateResponse<List<InitAllTeamResponse>>>> call, Response<APIResponse<InitAllTeamDateResponse<List<InitAllTeamResponse>>>> response) {

                allTeamListModels = new ArrayList<>();

                for (int i=0;i<response.body().getData().getItem().size();i++){
                    allTeamListModels.add(new AllTeamListModel(
                            response.body().getData().getItem().get(i).getId(),
                            response.body().getData().getItem().get(i).getTeam_name(),
                            response.body().getData().getItem().get(i).getCompetition_desc(),
                            response.body().getData().getItem().get(i).getDeclaration(),
                            response.body().getData().getItem().get(i).getGood_at()));

                    }
                setAllTeamAdapter();

            }

            @Override
            public void onDataFailure(Call<APIResponse<InitAllTeamDateResponse<List<InitAllTeamResponse>>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });

    }



    private void loadMoreData() {
        RestClient.getService().initAllTeam(++page, Config.size).enqueue(new DataCallback<APIResponse<InitAllTeamDateResponse<List<InitAllTeamResponse>>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<InitAllTeamDateResponse<List<InitAllTeamResponse>>>> call, Response<APIResponse<InitAllTeamDateResponse<List<InitAllTeamResponse>>>> response) {
                if(response.body().getData()!=null){


                    allTeamListModels=new ArrayList<>();



                    for (int i=0;i<response.body().getData().getItem().size();i++){
                        allTeamListModels.add(new AllTeamListModel(
                                response.body().getData().getItem().get(i).getId(),
                                response.body().getData().getItem().get(i).getTeam_name(),
                                response.body().getData().getItem().get(i).getCompetition_desc(),
                                response.body().getData().getItem().get(i).getDeclaration(),
                                response.body().getData().getItem().get(i).getGood_at()));

                    }
                    allTeamListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onDataFailure(Call<APIResponse<InitAllTeamDateResponse<List<InitAllTeamResponse>>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {
                if (progressDialog.isShowing()) {
                    disMissProgressDialog();
                }
            }
        });

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


    }

}
