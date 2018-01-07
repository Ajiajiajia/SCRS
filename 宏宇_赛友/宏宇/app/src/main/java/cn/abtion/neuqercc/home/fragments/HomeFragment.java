package cn.abtion.neuqercc.home.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.adapters.BaseRecyclerViewAdapter;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.home.activities.CompetitionActivity;
import cn.abtion.neuqercc.home.activities.SearchContestActivity;
import cn.abtion.neuqercc.home.adapters.HomeAdapter;
import cn.abtion.neuqercc.home.models.ContestListModel;
import cn.abtion.neuqercc.home.models.InitCrouselFigureRequest;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.home.models.InitContestRecylerViewDataRequest;
import cn.abtion.neuqercc.home.models.InitContestRecylerViewItemRequest;
import cn.abtion.neuqercc.home.models.InitCrouselFigureRequest;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.ToastUtil;
import retrofit2.Call;
import retrofit2.Response;
import cn.abtion.neuqercc.widget.EndLessOnScrollListener;
import retrofit2.Call;
import retrofit2.Response;

import static android.view.MotionEvent.ACTION_UP;
import static cn.abtion.neuqercc.common.Config.FLING_MIN_DISTANCE;


/**
 * @author abtion.
 * @since 17/9/24 上午2:16.
 * email caiheng@hrsoft.net.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.vf_contest)
    ViewFlipper vfContest;

    private ArrayList<ContestListModel> contestListModels;

    private InitCrouselFigureRequest initCrouselFigureRequest;

    private int downX = Config.VIEW_FLIPPER_INITAIL_VALUE;

    private static int page = 1;
    private HomeAdapter homeAdapter;
    private LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);


    @BindView(R.id.spinner_home)
    Spinner spinnerHome;
    @BindView(R.id.search_home)
    SearchView searchHome;
    @BindView(R.id.rec_home)
    RecyclerView recHome;
    @BindView(R.id.view_container)
    FrameLayout viewContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        processInitCarouselFigure();

        initViewFlipper();

        initContestRecyclerView();

    }

    @Override
    protected void loadData() {

    }


    public void initContestRecyclerView() {


        recHome.setNestedScrollingEnabled(false);
        contestListModels = new ArrayList<>();

        //弹出progressDialog
        progressDialog.setMessage(getString(R.string.dialog_wait_moment));
        progressDialog.show();

        //网络请求
        RestClient.getService().initContestRecylerView(page,Config.size).enqueue(new DataCallback<APIResponse<InitContestRecylerViewDataRequest<List<InitContestRecylerViewItemRequest>>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<InitContestRecylerViewDataRequest<List<InitContestRecylerViewItemRequest>>>> call, Response<APIResponse<InitContestRecylerViewDataRequest<List<InitContestRecylerViewItemRequest>>>> response) {
                List<InitContestRecylerViewItemRequest> list = response.body().getData().getItem();

                for (int i = 0; i < list.size(); i++) {
                    contestListModels.add(new ContestListModel(
                            list.get(i).getId(),
                            list.get(i).getName(),
                            list.get(i).getShort_desc(),
                            list.get(i).getRegistration_time(),
                            list.get(i).getCompetition_time()));
                }

                initAdapter(contestListModels);


            }

            @Override
            public void onDataFailure(Call<APIResponse<InitContestRecylerViewDataRequest<List<InitContestRecylerViewItemRequest>>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {
                if (progressDialog.isShowing()) {
                    disMissProgressDialog();
                }
            }
        });


    }

    /**
     * Recylerview的setAdapter和点击事件
     *
     * @param contestListModels
     */
    private void initAdapter(final ArrayList<ContestListModel> contestListModels) {
        homeAdapter = new HomeAdapter(getContext(), contestListModels);
        recHome.setAdapter(homeAdapter);
        recHome.setLayoutManager(linearLayoutManager);

        recHome.addOnScrollListener(new EndLessOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();
            }
        });

        //点击事件
        homeAdapter.setOnItemClickedListener(new BaseRecyclerViewAdapter.OnItemClicked<ContestListModel>() {

            @Override
            public void onItemClicked(ContestListModel contestListModel, BaseRecyclerViewAdapter.ViewHolder viewHolder) {
                Intent intent = new Intent(getContext(), CompetitionActivity.class);
                intent.putExtra("contestId", contestListModel.getId());
                startActivity(intent);
            }
        });
    }

    private void loadMoreData() {
        RestClient.getService().initContestRecylerView(++page,Config.size).enqueue(new DataCallback<APIResponse<InitContestRecylerViewDataRequest<List<InitContestRecylerViewItemRequest>>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<InitContestRecylerViewDataRequest<List<InitContestRecylerViewItemRequest>>>> call, Response<APIResponse<InitContestRecylerViewDataRequest<List<InitContestRecylerViewItemRequest>>>> response) {
                List<InitContestRecylerViewItemRequest> list = response.body().getData().getItem();
                if (list!=null){

                    for (int i = 0; i < list.size(); i++) {
                        contestListModels.add(new ContestListModel(
                                list.get(i).getId(),
                                list.get(i).getName(),
                                list.get(i).getShort_desc(),
                                list.get(i).getRegistration_time(),
                                list.get(i).getCompetition_time()));
                    }

                    homeAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onDataFailure(Call<APIResponse<InitContestRecylerViewDataRequest<List<InitContestRecylerViewItemRequest>>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });


    }


    public void processInitCarouselFigure() {

        //弹出progressDialog
        progressDialog.setMessage(getString(R.string.dialog_wait_moment));
        progressDialog.show();

        //网络请求
        RestClient.getService().initCrouselFigure().enqueue(new DataCallback<APIResponse<List<InitCrouselFigureRequest>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<List<InitCrouselFigureRequest>>> call, Response<APIResponse<List<InitCrouselFigureRequest>>> response) {
                List<InitCrouselFigureRequest> list = response.body().getData();
                for (int i = 0; i < list.size(); i++) {
                    ImageView imageView = new ImageView(getContext());
                    Glide.with(HomeFragment.this).load(list.get(i).getUrl()).into(imageView);
                    vfContest.addView(imageView);
                }

            }

            @Override
            public void onDataFailure(Call<APIResponse<List<InitCrouselFigureRequest>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {
                if (progressDialog.isShowing()) {
                    disMissProgressDialog();
                }
            }
        });

    }


    private ImageView getImageView(int id) {
        ImageView imageView = new ImageView(this.getContext());
        imageView.setImageResource(id);
        return imageView;
    }


    public void initViewFlipper() {

        vfContest.setInAnimation(inFromRightAnimation());
        vfContest.setOutAnimation(outToLeftAnimation());
        vfContest.setFlipInterval(Config.FLIPPER_TIME_INTERVAL);
        vfContest.startFlipping();
        vfContest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //解决viewFlipper和view的冲突
                vfContest.getParent().requestDisallowInterceptTouchEvent(true);
                //手势滑动
                int x;
                long downTime = 0;
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        //记录点击时的坐标
                        downX = (int) event.getRawX();
                        vfContest.stopFlipping();
                        break;
                    case ACTION_UP:
                        //记录离开时的坐标
                        x = (int) event.getRawX();
                        if (downX - x > FLING_MIN_DISTANCE) {

                            vfContest.setInAnimation(inFromRightAnimation());
                            vfContest.setOutAnimation(outToLeftAnimation());
                            vfContest.showNext();
                            vfContest.startFlipping();
                        } else if (x - downX > FLING_MIN_DISTANCE) {

                            vfContest.setOutAnimation(outToRightAnimation());
                            vfContest.setInAnimation(inFromLeftAnimation());
                            vfContest.showPrevious();

                            //从左向右滑之后，重新设置动画效果
                            vfContest.startFlipping();
                            vfContest.setInAnimation(inFromRightAnimation());
                            vfContest.setOutAnimation(outToLeftAnimation());

                        } else {

                            Intent intent = new Intent(getContext(), CompetitionActivity.class);
                            startActivity(intent);

                            vfContest.showNext();
                            vfContest.startFlipping();
                        }
                        break;
                    default:
                        break;
                }

                return true;
            }
        });


    }


    /**
     * 定义从右侧进入的动画效果
     */
    protected Animation inFromRightAnimation() {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(Config.FLIPPER_ACTION_SPEED);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    /**
     * 定义从左侧退出的动画效果
     */
    protected Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(Config.FLIPPER_ACTION_SPEED);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }

    /**
     * 定义从左侧进入的动画效果
     */
    protected Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(Config.FLIPPER_ACTION_SPEED);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }

    /**
     * 定义从右侧退出时的动画效果
     */
    protected Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(Config.FLIPPER_ACTION_SPEED);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }







    @OnClick(R.id.search_home)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), SearchContestActivity.class);
        startActivity(intent);
    }
}
