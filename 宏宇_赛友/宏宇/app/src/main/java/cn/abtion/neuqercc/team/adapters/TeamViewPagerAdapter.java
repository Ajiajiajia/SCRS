package cn.abtion.neuqercc.team.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.team.fragments.AllTeamFragment;
import cn.abtion.neuqercc.team.fragments.RecommendTeamFragment;

/**
 * @author lszr
 * @since 2017/11/14 下午8:25
 * email wsyglszr@gmail.com
 */

public class TeamViewPagerAdapter extends FragmentPagerAdapter {


    private String[] titles = new String[]{Config.ALL_TEAM, Config.RECOMMAND_TEAM};
    private AllTeamFragment allTeamFragment;
    private RecommendTeamFragment recommendTeamFragment;
    private android.support.v4.app.Fragment currentFragment;

    public TeamViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case Config.TEAM_VIEW_PAGER_ITEME_ZERO:
                if (allTeamFragment==null){
                    allTeamFragment = new AllTeamFragment();
                }
                currentFragment = allTeamFragment;
                break;
            case Config.TEAM_VIEW_PAGER_ITEME_ONE:
                if (recommendTeamFragment==null){
                    recommendTeamFragment=new RecommendTeamFragment();
                }
                currentFragment=recommendTeamFragment;
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
