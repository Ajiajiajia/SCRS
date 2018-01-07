package cn.abtion.neuqercc.mine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.mine.models.FeedBackRequest;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.ToastUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author fhyPayaso
 * @since 2017/11/20 17:10
 * email fhyPayaso@qq.com
 */

public class FeedbackActivity extends ToolBarActivity {


    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.edit_feedback)
    EditText editFeedback;

    FeedBackRequest feedBackRequest;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_feedback;
    }

    @Override
    protected void initVariable() {

        setActivityTitle(getString(R.string.txt_feedback));

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.btn_submit)
    public void onBtnSubmitClicked() {

        if (editFeedback.getText().toString().trim().equals(Config.EMPTY_FIELD)) {

            ToastUtil.showToast(getString(R.string.toast_feedback_empty));

        } else {

            commitFeedBack();

            Intent intent = new Intent(FeedbackActivity.this, SettingActivity.class);
            startActivity(intent);
            finish();
        }
    }


    public void commitFeedBack() {


        feedBackRequest = new FeedBackRequest();
        feedBackRequest.setContent(editFeedback.getText().toString().trim());

        RestClient.getService().feedBack(feedBackRequest).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                ToastUtil.showToast(getString(R.string.toast_commit_successful));
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }


            public void dismissDialog() {

            }

        });
    }



}
