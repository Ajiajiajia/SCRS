package cn.abtion.neuqercc.main;

import android.content.Intent;
import android.support.transition.Fade;
import android.support.transition.TransitionManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.models.LoginRequest;
import cn.abtion.neuqercc.base.activities.BaseActivity;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by heaijia on 2017/12/5.
 */

public class MainActivity extends NoBarActivity {


    @BindView(R.id.txt_tab_menu_cloud)
    TextView txtTabMenuCloud;
    @BindView(R.id.txt_tab_menu_rain)
    TextView txtTabMenuRain;
    @BindView(R.id.txt_tab_menu_snow)
    TextView txtTabMenuSnow;
    @BindView(R.id.viewpage_menu_container)
    ViewPager mainViewPager;
//    @BindView(R.id.viewpage_menu_container)
//    ViewPager viewpageMenuContainer;
    @BindView(R.id.frame_main_container)
    FrameLayout frameMainContainer;
    @BindView(R.id.img_tab_menu_sun)
    ImageView imgTabMenuSun;
    @BindView(R.id.txt_tab_menu_sun)
    TextView txtTabMenuSun;
    @BindView(R.id.llayout_tab_menu_sun)
    LinearLayout llayoutTabMenuSun;
    @BindView(R.id.img_tab_menu_cloud)
    ImageView imgTabMenuCloud;
    @BindView(R.id.llayout_tab_menu_cloud)
    LinearLayout llayoutTabMenuCloud;
    @BindView(R.id.img_tab_menu_rain)
    ImageView imgTabMenuRain;
    @BindView(R.id.llayout_tab_menu_rain)
    LinearLayout llayoutTabMenuRain;
    @BindView(R.id.img_tab_menu_snow)
    ImageView imgTabMenuSnow;
    @BindView(R.id.llayout_tab_menu_snow)
    LinearLayout llayoutTabMenuSnow;
    @BindView(R.id.llayout_tab_menu)
    LinearLayout llayoutTabMenu;




    /**
     * 用于后面点击按钮进行跳转到  MainActivity界面，flag相当于value,因为在其他Activity,可以直接调用Mainactivity。start就可以直接回到 Main的主界面了
     * @param context
     * @param flag
     */
    public static void start(BaseActivity context, int flag) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_menu;
    }

    @Override
    protected void initVariable() {

        MenuViewPageAdapter menuViewPageAdapter = new MenuViewPageAdapter(getSupportFragmentManager());
        mainViewPager.setCurrentItem(Config.FLAG_SUN);
        mainViewPager.setOffscreenPageLimit(Config.PAGE_LIMIT);
        mainViewPager.setAdapter(menuViewPageAdapter);

        mainViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        LoginRequest loginRequest=new LoginRequest();
//        loginRequest.serIdentifier("15076035390");
//        loginRequest.setPassword("12345678");
//        RestClient.getService().login(loginRequest).enqueue(new DataCallback<APIResponse>() {
//            @Override
//            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {
//
//            }
//
//            @Override
//            public void onDataFailure(Call<APIResponse> call, Throwable t) {
//
//            }
//
//            @Override
//            public void dismissDialog() {
//
//            }
//        });





    }

    @Override
    protected void initView() {
        onLyTabMenuSunClicked();

        //从其他活动转入后指定显示的fragment
        Intent intent = getIntent();
        int flag = intent.getIntExtra(Config.KEY_MAIN_FLAG, -1);
        mainViewPager.setCurrentItem(flag);


        TransitionManager.beginDelayedTransition((ViewGroup) findViewById(android.R.id.content), new Fade());

    }

    @Override
    protected void loadData() {

    }




    /**
     * 清除选中状态
     */
    private void clearChoiceStatus() {


        imgTabMenuSun.setSelected(false);
        imgTabMenuCloud.setSelected(false);
        imgTabMenuRain.setSelected(false);
        imgTabMenuSnow.setSelected(false);

        txtTabMenuSun.setTextColor(getResources().getColor(R.color.subtitle));
        txtTabMenuCloud.setTextColor(getResources().getColor(R.color.subtitle));
        txtTabMenuRain.setTextColor(getResources().getColor(R.color.subtitle));
        txtTabMenuSnow.setTextColor(getResources().getColor(R.color.subtitle));

    }

    /**
     * 高亮Sun
     */
    private void changeSunMenuStatus() {
        clearChoiceStatus();
        imgTabMenuSun.setSelected(true);
        txtTabMenuSun.setTextColor(getResources().getColor(R.color.text_warning));
    }

    /**
     * 高亮Cloud
     */
    private void changeCloudMenuStatus() {
        clearChoiceStatus();
        imgTabMenuCloud.setSelected(true);
        txtTabMenuCloud.setTextColor(getResources().getColor(R.color.text_warning));

    }

    /**
     * 高亮Rain
     */
    private void changeRainMenuStatus() {
        clearChoiceStatus();
        imgTabMenuRain.setSelected(true);
        txtTabMenuRain.setTextColor(getResources().getColor(R.color.text_warning));

    }

    /**
     * 高亮Snow
     */
    private void changeSnowMenuStatus() {
        clearChoiceStatus();
        imgTabMenuSnow.setSelected(true);
        txtTabMenuSnow.setTextColor(getResources().getColor(R.color.text_warning));
    }



    /**
     * 点击Home
     */
    @OnClick(R.id.llayout_tab_menu_sun)
    public void onLyTabMenuSunClicked() {

        changeSunMenuStatus();
        mainViewPager.setCurrentItem(Config.FLAG_SUN, false);

    }

    /**
     * 点击Cloud
     */
    @OnClick(R.id.llayout_tab_menu_cloud)
    public void onLyTabMenuCloudClicked() {
        changeCloudMenuStatus();
        mainViewPager.setCurrentItem(Config.FLAG_CLOUD, false);

    }

    /**
     * 点击Rain
     */
    @OnClick(R.id.llayout_tab_menu_rain)
    public void onLyTabMenuMessageClicked() {

        changeRainMenuStatus();
        mainViewPager.setCurrentItem(Config.FLAG_RAIN, false);

    }

    /**
     * Snow
     */
    @OnClick(R.id.llayout_tab_menu_snow)
    public void onLyTabMenuSnowClicked() {

        changeSnowMenuStatus();
        mainViewPager.setCurrentItem(Config.FLAG_SNOW, false);

    }



    @OnClick({R.id.llayout_tab_menu_sun, R.id.llayout_tab_menu_cloud, R.id.llayout_tab_menu_rain, R.id.llayout_tab_menu_snow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llayout_tab_menu_sun:
                break;
            case R.id.llayout_tab_menu_cloud:
                break;
            case R.id.llayout_tab_menu_rain:
                break;
            case R.id.llayout_tab_menu_snow:
                break;
        }
    }
}
