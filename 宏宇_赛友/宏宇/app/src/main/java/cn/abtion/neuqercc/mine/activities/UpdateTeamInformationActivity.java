package cn.abtion.neuqercc.mine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.mine.models.MineTeamInformationRequest;
import cn.abtion.neuqercc.mine.models.UpdateTeamInformationRequest;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.ToastUtil;
import retrofit2.Call;
import retrofit2.Response;


/**
 * @author fhyPayaso
 * @since 2017/11/27 21:30
 * email fhyPayaso@qq.com
 */


public class UpdateTeamInformationActivity extends ToolBarActivity {


    @BindView(R.id.edit_team_name)
    EditText editTeamName;
    @BindView(R.id.edit_want_join)
    EditText editWantJoin;
    @BindView(R.id.edit_team_declaration)
    EditText editTeamDeclaration;


    public static int teamId;
    UpdateTeamInformationRequest updateTeamInformationRequest;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_team_information;
    }

    @Override
    protected void initVariable() {


    }


    @Override
    protected void initView() {

        initTitle();

    }


    @Override
    protected void loadData() {

        loadTeamInformation();

    }


    protected void initTitle() {

        setActivityTitle(getString(R.string.title_edit_team_information));
        setTextOver(getString(R.string.title_over));


        TextView txtTitleOver = (TextView) getToolbar().findViewById(R.id.txt_toolbar_over);

        txtTitleOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isDataTrue()) {
                    commitTeamInformation();
                } else {
                    ToastUtil.showToast(getString(R.string.toast_lack_information));
                }


            }
        });
    }


    protected void loadTeamInformation() {

        Intent intent = getIntent();
        MineTeamInformationRequest teamInformationRequest = new Gson()
                .fromJson(intent.getStringExtra("teamInformation"), MineTeamInformationRequest.class);
        teamId = teamInformationRequest.getId();
        editTeamName.setText(teamInformationRequest.getTeamName());
        editWantJoin.setText(teamInformationRequest.getCompetitionDesc());
        editTeamDeclaration.setText(teamInformationRequest.getDeclaration());

    }


    /**
     * 提交队伍修改信息
     */
    protected void commitTeamInformation() {

        updateTeamInformationRequest = new UpdateTeamInformationRequest(teamId,
                editTeamName.getText().toString(),
                editWantJoin.getText().toString(),
                editTeamDeclaration.getText().toString(),
                "test"
        );


        RestClient.getService().updateTeamInformation(updateTeamInformationRequest).enqueue(new DataCallback<APIResponse>() {
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {

                ToastUtil.showToast(R.string.toast_edit_successful);
                finish();
            }

            @Override
            public void onDataFailure(Call<APIResponse> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });


    }


    private boolean isDataTrue() {

        boolean flag = true;

        if (editTeamName.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
            flag = false;
        } else if (editWantJoin.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
            flag = false;
        }
        return flag;
    }


}
