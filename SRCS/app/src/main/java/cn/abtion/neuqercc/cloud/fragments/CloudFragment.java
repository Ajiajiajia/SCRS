package cn.abtion.neuqercc.cloud.fragments;

import android.content.res.Resources;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.BaseActivity;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.cloud.adapters.CloudViewPageAdapter;
import cn.abtion.neuqercc.common.Config;

/**
 * Created by heaijia on 2017/12/10.
 */

public class CloudFragment extends BaseFragment{


    @BindView(R.id.tlayout_header_navigation)
    TabLayout tlayoutheadernavigation;

    @BindView(R.id.viewpage_choice)
    ViewPager viewpagechoice;




    private CloudViewPageAdapter cloudViewPageAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cloud;
    }

    @Override
    protected void initVariable() {

        cloudViewPageAdapter = new CloudViewPageAdapter(getFragmentManager());
        viewpagechoice.setAdapter(cloudViewPageAdapter);
        tlayoutheadernavigation.setupWithViewPager(viewpagechoice);

    }

    @Override
    protected void initView() {

        setIndicator(tlayoutheadernavigation, Config.TAB_INDICATOR_LENGTH, Config.TAB_INDICATOR_LENGTH) ;
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

}
