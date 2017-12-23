package cn.abtion.neuqercc.cloud.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.abtion.neuqercc.cloud.fragments.HarvestFragment;
import cn.abtion.neuqercc.cloud.fragments.ImprovementFragment;
import cn.abtion.neuqercc.common.Config;

/**
 * Created by heaijia on 2017/12/13.
 */

public class CloudViewPageAdapter extends FragmentPagerAdapter {

    public CloudViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    private String[] titles= new String[] {Config.HARVEST_CLOUD,Config.IMPROVEMENT_CLOUD};
    private HarvestFragment harvestFragment;
    private ImprovementFragment improvementFragment;
    private android.support.v4.app.Fragment currentFragment;

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case Config.HARVEST_VIEW_PAGER_CLOUD_ZERO:
                if (harvestFragment==null){
                    harvestFragment=new HarvestFragment();
                }
                currentFragment=harvestFragment;
                break;
            case Config.IMPROVEMENT_VIEW_PAGER_CLOUD_ONE:
                if (improvementFragment==null){
                    improvementFragment=new ImprovementFragment();
                }
                currentFragment=improvementFragment;
                break;
            default:
        }
        return currentFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public  CharSequence getPageTitle(int position){
        return titles[position];
    }
}
