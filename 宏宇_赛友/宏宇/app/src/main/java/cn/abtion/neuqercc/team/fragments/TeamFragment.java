package cn.abtion.neuqercc.team.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.team.activities.EstablishTeamActivity;
import cn.abtion.neuqercc.team.activities.SearchTeamActivity;
import cn.abtion.neuqercc.team.adapters.TeamViewPagerAdapter;

/**
 * @author abtion.
 * @since 17/9/24 上午2:15.
 * email caiheng@hrsoft.net.
 */

public class TeamFragment extends BaseFragment {
    @BindView(R.id.table_header_navigation)
    TabLayout tableHeaderNavigation;
    @BindView(R.id.viewPager_team)
    ViewPager viewPagerTeam;
    @BindView(R.id.btn_establish_team)
    Button btnEstablishTeam;

    private TeamViewPagerAdapter teamViewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_team;
    }

    @Override
    protected void initVariable() {

        teamViewPagerAdapter = new TeamViewPagerAdapter(getFragmentManager());
        viewPagerTeam.setAdapter(teamViewPagerAdapter);
        tableHeaderNavigation.setupWithViewPager(viewPagerTeam);
    }

    @Override
    protected void initView() {

        //修改tab指示器长度
        setIndicator(tableHeaderNavigation, Config.TAB_INDICATOR_LENGTH, Config.TAB_INDICATOR_LENGTH);

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


    @OnClick(R.id.btn_establish_team)
    public void OnBtnEstablishTeamClicked() {
        Intent intent = new Intent(getContext(), EstablishTeamActivity.class);
        startActivity(intent);
    }


    /**
     * 搜索队伍点击事件
     */
    @OnClick(R.id.img_search_team)
    public void onImgSearchTeamViewClicked() {
        Intent intent = new Intent(getContext(), SearchTeamActivity.class);
        startActivity(intent);

    }
}
