package cn.abtion.neuqercc.account.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.models.LoginRequest;
import cn.abtion.neuqercc.base.activities.NoBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.main.MainActivity;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.RegexUtil;
import cn.abtion.neuqercc.utils.ToastUtil;
import cn.abtion.neuqercc.weight.MainViewPager;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/9/22 17:59.
 * email caiheng@hrsoft.net
 */

public class LoginActivity extends NoBarActivity {

    @BindView(R.id.edit_identifier)
    TextInputEditText editIdentifier;
    @BindView(R.id.edit_password)
    TextInputEditText editPassword;

    public static String password;
    public static String phonenumber;


    private LoginRequest loginRequest;



    /**
     * 获取LayoutId.
     *
     * @return LayoutId 布局文件Id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    /**
     * 初始化变量.
     */
    @Override
    protected void initVariable() {
        loginRequest = new LoginRequest();
    }

    /**
     * 初始化View的状态，挂载各种监听事件.
     */
    @Override
    protected void initView() {

    }

    /**
     * 初始化加载页面数据.
     */
    @Override
    protected void loadData() {

    }

    /**
     * 登录按钮点击事件--登录
     */
    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked() {

        loginRequest.setIdentifier(editIdentifier.getText().toString().trim());
        loginRequest.setPassword(editPassword.getText().toString().trim());

        if(isDateTrue()){

            processLogin();
        }
    }



    /**
     * 按钮点击事件--注册
     */
    @OnClick(R.id.txt_register)
    public void onTxtRegisterClicked(){



        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
        finish();

    }

    /**
     * 进行登录的相关操作--网络请求
     */
    private void processLogin() {

        //弹出progressDialog
        progressDialog.setMessage(getString(R.string.dialog_wait_moment));
        progressDialog.show();

        //网络请求
        RestClient.getService().login(loginRequest).enqueue(new DataCallback<APIResponse>() {

            //请求成功时回调
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {


                //登录成功记录账号和密码
                phonenumber=editIdentifier.getText().toString().trim();
                password=editPassword.getText().toString().trim();
                ToastUtil.showToast(getString(R.string.toast_login_successful));


                //跳转至MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            //请求失败的时候回调
            @Override
            public void onDataFailure(Call<APIResponse> call, Throwable t) {

            }

            //无论成功或者失败时都回调，用于dismissDialog或隐藏其他控件
            @Override
            public void dismissDialog() {
                if (progressDialog.isShowing()) {
                    disMissProgressDialog();
                }
            }
        });

    }


    /**
     * 用于TextInputEditText控件提示信息   Using this class allows us to display a hint in the IME when in 'extract' mode
     */
    private void showError(TextInputEditText textInputEditText,String error){
        textInputEditText.setError(error);
        textInputEditText.setFocusable(true);
        textInputEditText.setFocusableInTouchMode(true);
        textInputEditText.requestFocus();
    }

    /**
     * 验证用户输入是否正确
     * @return   正确为true
     */
    public boolean isDateTrue() {

            boolean flag = true;

            if(editIdentifier.getText().toString().trim().equals(Config.EMPTY_FIELD)){
                showError(editIdentifier,getString(R.string.error_account_empty_illegal));
                flag=false;
            }
            else if (!RegexUtil.checkMobile(editIdentifier.getText().toString().trim())) {
                showError(editIdentifier, getString(R.string.error_phone_number_illegal));
                flag = false;
            }
            else if (editPassword.getText().toString().trim().length() < Config.PASSWORD_MIN_LIMIT) {
                showError(editPassword,getString(R.string.error_password_min_limit) );
                flag = false;
            }
            else if (editPassword.getText().toString().trim().length() > Config.PASSWORD_MAX_LIMIT) {
                showError(editPassword, getString(R.string.error_password_max_limit));
                flag = false;
            }
        return flag;
    }
}