package cn.abtion.neuqercc.home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.home.models.RaidersAndDetailsRequest;

/**
 * @author fhyPayaso
 * @since 2017/11/7 16:39
 * email fhyPayaso@qq.com
 */

public class DetailsFragment extends BaseFragment {

    @BindView(R.id.img_details)
    ImageView imgDetails;
    @BindView(R.id.txt_details)
    TextView txtDetails;
    private RaidersAndDetailsRequest data;


    public DetailsFragment(RaidersAndDetailsRequest raidersAndDetailsRequest) {
        data = raidersAndDetailsRequest;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_details;
    }

    @Override
    protected void initVariable() {


    }

    @Override
    protected void initView() {

        Glide.with(DetailsFragment.this).load(data.getPic()).into(imgDetails);

        txtDetails.setText(data.getDesc());
    }

    @Override
    protected void loadData() {

    }

}
