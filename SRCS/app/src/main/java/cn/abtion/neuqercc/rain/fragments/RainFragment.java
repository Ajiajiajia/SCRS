package cn.abtion.neuqercc.rain.fragments;

import android.content.res.Resources;
import android.service.notification.NotificationListenerService;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.BaseActivity;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.rain.adapters.RainListAdapter;
import cn.abtion.neuqercc.rain.models.RainListModel;

/**
 * Created by heaijia on 2017/12/10.
 */

public class RainFragment extends BaseFragment{

    @BindView(R.id.recyclerview_list_rain)
    RecyclerView recyclerViewlistrain;


    private ArrayList<RainListModel> rainListModels;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rain;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {






        rainListModels=new ArrayList<>();
        for (int i=0;i<20;i++)
        {
            rainListModels.add(new RainListModel());
        }

        RainListAdapter rainListAdapter=new RainListAdapter(getContext(),rainListModels);
        recyclerViewlistrain.setAdapter(rainListAdapter);
        recyclerViewlistrain.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void loadData() {

    }


}
