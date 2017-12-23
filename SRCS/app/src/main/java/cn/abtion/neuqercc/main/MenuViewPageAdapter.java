package cn.abtion.neuqercc.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.abtion.neuqercc.cloud.fragments.CloudFragment;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.rain.fragments.RainFragment;
import cn.abtion.neuqercc.snow.fragments.SnowFragment;
import cn.abtion.neuqercc.sun.fragments.SunFragment;

/**
 * Created by heaijia on 2017/12/10.
 */

public class MenuViewPageAdapter extends FragmentPagerAdapter {

    private SunFragment sunFragment;
    private CloudFragment cloudFragment;
    private RainFragment rainFragment;
    private SnowFragment snowFragment;
    private Fragment currentFragment;

    public MenuViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){

            case Config.FLAG_SUN:
                if (sunFragment==null){
                    sunFragment = new SunFragment();
                }
                currentFragment = sunFragment;
                break;
            case Config.FLAG_CLOUD:
                if (cloudFragment==null){
                    cloudFragment=new CloudFragment();
                }
                currentFragment = cloudFragment;
                break;
            case Config.FLAG_RAIN:
                if (rainFragment==null){
                    rainFragment = new RainFragment();
                }
                currentFragment = rainFragment;
                break;
            case Config.FLAG_SNOW:
                if (snowFragment==null){
                    snowFragment=new SnowFragment();
                }
                currentFragment = snowFragment;
                break;

            default:

        }

        return currentFragment;
    }

    @Override
    public int getCount() {
        return Config.PAGE_LIMIT;
    }
}
