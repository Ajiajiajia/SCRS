package cn.abtion.neuqercc.mine.activities;

import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;

/**
 * @author fhyPayaso
 * @since 2017/11/23 14:05
 * email fhyPayaso@qq.com
 */

public class AboutUsActivity extends ToolBarActivity {


    @Override
    protected int getLayoutId() {

        return R.layout.activity_mine_about_us;

    }

    @Override
    protected void initVariable() {

        setActivityTitle(getString(R.string.txt_about_us));

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }
}
