package cn.abtion.neuqercc.cloud.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.cloud.adapters.HarvestListAdapter;
import cn.abtion.neuqercc.cloud.models.HarvestListModel;

/**
 * Created by heaijia on 2017/12/10.
 */

public class HarvestFragment extends BaseFragment{

    @BindView(R.id.recyclerview_list_harvest)
    RecyclerView recyclervirelistharvest;

    private ArrayList<HarvestListModel> harvestListModels;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recyclerview_harvest;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        harvestListModels=new ArrayList<>();
        for (int i=0;i<20;i++){
            harvestListModels.add(new HarvestListModel());

        }
        HarvestListAdapter harvestListAdapter= new HarvestListAdapter(getContext(),harvestListModels);
        recyclervirelistharvest.setAdapter(harvestListAdapter);
        recyclervirelistharvest.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected void loadData() {

    }
}
