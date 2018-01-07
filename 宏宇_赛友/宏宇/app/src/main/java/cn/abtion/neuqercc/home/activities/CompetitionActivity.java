package cn.abtion.neuqercc.home.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.home.adapters.CompetitionViewPagerAdapter;
import cn.abtion.neuqercc.home.models.RaidersAndDetailsRequest;
import cn.abtion.neuqercc.main.MainActivity;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.team.activities.SearchTeamActivity;
import cn.abtion.neuqercc.utils.ToastUtil;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/9/30 22:07.
 * email caiheng@hrsoft.net.
 */

public class CompetitionActivity<T> extends ToolBarActivity {

    @BindView(R.id.table_header_navigation)
    TabLayout headerTitleTable;
    @BindView(R.id.viewpager_body)
    ViewPager competitionViewPager;

    private int contestItemId;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_competion;
    }


    @Override
    protected void initVariable() {
        Intent intent = getIntent();
        if (intent != null) {
            contestItemId = intent.getExtras().getInt("contestId");
        }

        processGetRaidersAndDetails();


    }

    @Override
    protected void initView() {

        this.setActivityTitle(getString(R.string.title_competition));




        //修改tab指示器长度
        setIndicator(headerTitleTable, Config.TAB_INDICATOR_WIDTH, Config.TAB_INDICATOR_WIDTH);
    }

    @Override
    protected void loadData() {

    }

    /**
     * 修改指示器长度的方法
     */
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    @OnClick(R.id.btn_search)
    public void onBtnSearchViewClicked() {
        Intent intent = new Intent(CompetitionActivity.this, SearchTeamActivity.class);
        startActivity(intent);
    }


    public void processGetRaidersAndDetails() {

        //弹出progressDialog
        progressDialog.setMessage(getString(R.string.dialog_wait_moment));
        progressDialog.show();

        //网络请求
        RestClient.getService().getRaidersAndDetails(contestItemId).enqueue(new DataCallback<APIResponse<RaidersAndDetailsRequest>>() {
            @Override
            public void onDataResponse(Call<APIResponse<RaidersAndDetailsRequest>> call, Response<APIResponse<RaidersAndDetailsRequest>> response) {


                CompetitionViewPagerAdapter competitionPagerAdapter = new CompetitionViewPagerAdapter(getSupportFragmentManager(),response.body().getData());
                competitionViewPager.setAdapter(competitionPagerAdapter);
                //tabLayout和ViewPager联动
                headerTitleTable.setupWithViewPager(competitionViewPager);
            }

            @Override
            public void onDataFailure(Call<APIResponse<RaidersAndDetailsRequest>> call, Throwable t) {

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
