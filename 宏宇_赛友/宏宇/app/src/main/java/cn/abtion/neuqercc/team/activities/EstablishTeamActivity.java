package cn.abtion.neuqercc.team.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.team.models.EstablishTeamRequest;

/**
 * @author lszr
 * @since 2017/11/20 下午7:16
 * email wsyglszr@gmail.com
 */

public class EstablishTeamActivity extends ToolBarActivity {
    @BindView(R.id.edit_team_name)
    EditText editTeamName;
    @BindView(R.id.edit_want_join)
    EditText editWantJoin;
    @BindView(R.id.edit_declaration)
    EditText editDeclaration;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_establish_team;
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

    }


    public void initTitle() {

        setActivityTitle(getString(R.string.title_establish_team));
        setTextOver(getString(R.string.title_over));
        setOnOverTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EstablishTeamActivity.this, EstablishTeamFinishActivity.class);

                EstablishTeamRequest establishTeamRequest = new EstablishTeamRequest(
                        LoginActivity.phoneNumber,
                        editTeamName.getText().toString(),
                        editWantJoin.getText().toString(),
                        editDeclaration.getText().toString(),
                        "前端开发"
                );
                String gson=new Gson().toJson(establishTeamRequest);

                intent.putExtra("establishTeamRequest",gson);


                startActivity(intent);
            }
        });

    }


}
