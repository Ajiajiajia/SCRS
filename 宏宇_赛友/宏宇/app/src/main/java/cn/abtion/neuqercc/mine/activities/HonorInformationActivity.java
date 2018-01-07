package cn.abtion.neuqercc.mine.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.mine.models.ShowHonorResponse;
import cn.abtion.neuqercc.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2017/11/28 13:25
 * email fhyPayaso@qq.com
 */

public class HonorInformationActivity extends ToolBarActivity {

    @BindView(R.id.img_honor)
    ImageView imgHonor;
    @BindView(R.id.txt_event_name)
    TextView txtEventName;
    @BindView(R.id.txt_event_time)
    TextView txtEventTime;

    private String picUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_honor_information;
    }

    @Override
    protected void initVariable() {


    }

    @Override
    protected void initView() {

        setActivityTitle(getString(R.string.title_honor_information));
    }

    @Override
    protected void loadData() {

        loadIntent();
    }

    public void loadIntent() {

        Intent intent = getIntent();
        ShowHonorResponse honorResponse = new Gson().fromJson(intent.getStringExtra(Config.INTENT_EXTRA_HONOR), ShowHonorResponse.class);
        txtEventName.setText(honorResponse.getGloryName());
        txtEventTime.setText(honorResponse.getGloryTime());
        picUrl = honorResponse.getGloryPicUrl();
        Glide.with(this).load(picUrl).into(imgHonor);
    }


    @OnClick(R.id.img_honor)
    public void onImgHonorClicked() {

        showFullImg();
    }


    public void showFullImg() {

        View view = View.inflate(this, R.layout.item_dialog_full_img, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_full_dialog);
        Glide.with(this).load(picUrl).into(imageView);

        builder.show();

    }


}
