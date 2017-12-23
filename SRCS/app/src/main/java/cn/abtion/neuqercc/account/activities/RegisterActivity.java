package cn.abtion.neuqercc.account.activities;

import android.content.Intent;

import butterknife.OnClick;
import cn.abtion.neuqercc.main.MainActivity;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.NoBarActivity;

/**
 * Created by heaijia on 2017/12/4.
 */

public class RegisterActivity extends NoBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }




    @OnClick(R.id.btn_register)
    public void onBtnLoginClicked_Register() {
        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
