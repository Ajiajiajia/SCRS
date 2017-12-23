package cn.abtion.neuqercc.cloud.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.cloud.adapters.ImProvementListAdapter;
import cn.abtion.neuqercc.cloud.models.ImprovementListModel;

/**
 * Created by heaijia on 2017/12/10.
 */

public class ImprovementFragment extends BaseFragment{

    @BindView(R.id.recyclerview_list_improvement)
    RecyclerView recyclerViewImprovement;
    private ArrayList<ImprovementListModel> improvementListModel;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recyclerview_improvement;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        improvementListModel = new ArrayList<>();
        for (int i=0;i<20;i++)
        {
            improvementListModel.add(new ImprovementListModel());
        }
        ImProvementListAdapter imProvementListAdapter=new ImProvementListAdapter(getContext(),improvementListModel);
        recyclerViewImprovement.setAdapter(imProvementListAdapter);
        recyclerViewImprovement.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void loadData() {

    }
}
