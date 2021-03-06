package cn.abtion.neuqercc.account.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.models.UpdatePasswordRequest;
import cn.abtion.neuqercc.account.models.SmsRequest;
import cn.abtion.neuqercc.base.activities.NoBarActivity;

import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.main.MainActivity;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.RegexUtil;
import cn.abtion.neuqercc.utils.ToastUtil;
import cn.abtion.neuqercc.widget.CaptchaCountDownTimer;
import retrofit2.Call;
import retrofit2.Response;


/**
 * @author lszr
 * @since 2017/10/16 下午9:29
 * email wsyglszr@gmail.com
 */

public class UpdatePasswordActivity extends NoBarActivity {

    @BindView(R.id.edit_password)
    TextInputEditText editPassword;
    @BindView(R.id.edit_repeat_password)
    TextInputEditText editRepeatPassword;
    @BindView(R.id.edit_phone)
    TextInputEditText editPhone;
    @BindView(R.id.edit_captcha)
    TextInputEditText editCaptcha;
    @BindView(R.id.btn_get_verify_code)
    Button btnGetVerifyCode;

    private UpdatePasswordRequest updatePasswordRequest;

    private SmsRequest smsRequest;
    private String verifyCode;
    CaptchaCountDownTimer captchaTimer;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void initVariable() {
        smsRequest = new SmsRequest();
        updatePasswordRequest = new UpdatePasswordRequest();

        initCountDownTimer();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    /**
     * 获取验证码按钮点击事件
     */
    @OnClick(R.id.btn_get_verify_code)
    public void onBtnGetVerifyCodeClicked() {

        smsRequest.setPhone(editPhone.getText().toString().trim());

        if (isPhoneTrue()) {

            captchaTimer.timerStart(true);
            getVerifyCode();
        }
    }


    /**
     * 短信验证码
     */
    public void getVerifyCode() {

        //网络请求

        RestClient.getService().captch(smsRequest).enqueue(new DataCallback<APIResponse>() {

            //请求成功时回调
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {

                verifyCode = response.body().getData().toString().trim();
                ToastUtil.showToast(verifyCode);
                //ToastUtil.showToast(getString(R.string.toast_send_successful));

            }

            //请求失败时回调
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
     * 倒计时相关方法
     */

    public void initCountDownTimer() {

        if (!CaptchaCountDownTimer.FLAG_FIRST_IN &&
                CaptchaCountDownTimer.curMillis + Config.COUNT_DOWN_TIME_TOTAL > System.currentTimeMillis()) {

            setCountDownTimer(CaptchaCountDownTimer.curMillis + Config.COUNT_DOWN_TIME_TOTAL - System.currentTimeMillis());
            captchaTimer.timerStart(false);

        } else {
            setCountDownTimer(Config.COUNT_DOWN_TIME_TOTAL);
        }
    }


    public void setCountDownTimer(final long countDownTime) {

        captchaTimer = new CaptchaCountDownTimer(countDownTime, Config.COUNT_DOWN_TIME_PER) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnGetVerifyCode.setEnabled(false);
                btnGetVerifyCode.setText((millisUntilFinished / Config.COUNT_DOWN_TIME_PER) + " s");
            }

            @Override
            public void onFinish() {

                btnGetVerifyCode.setEnabled(true);
                btnGetVerifyCode.setText(getString(R.string.btn_get_captcha));

                if (countDownTime != Config.COUNT_DOWN_TIME_TOTAL) {
                    setCountDownTimer(Config.COUNT_DOWN_TIME_TOTAL);
                }
            }
        };
    }


    /**
     * 验证手机号正则
     *
     * @return
     */
    private boolean isPhoneTrue() {
        boolean flag = true;
        if (editPhone.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
            showError(editPhone, getString(R.string.error_phone_number_empty_illegal));
            flag = false;
        } else if (!RegexUtil.checkMobile(editPhone.getText().toString().trim())) {
            showError(editPhone, getString(R.string.error_phone_number_illegal));
            flag = false;
        }
        return flag;
    }


    /**
     * 完成改密码点击事件
     */
    @OnClick(R.id.btn_over)
    public void onBtnOverClicked() {

        //updatePasswordRequest.setPhone(getIntent().getStringExtra("phoneNumber"));
        updatePasswordRequest.setPhone(editPhone.getText().toString().trim());
        updatePasswordRequest.setPassword(editPassword.getText().toString().trim());

        if (isDataTrue()) {
            updatePassword();
        }
    }


    /**
     * 改密码按钮相关方法
     */
    public void updatePassword() {

        //弹出progressDialog
        progressDialog.setMessage(getString(R.string.dialog_wait_moment));
        progressDialog.show();

        //网络请求

        RestClient.getService().updatePassword(updatePasswordRequest).enqueue(new DataCallback<APIResponse>() {

            //请求成功时回调
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {

                ToastUtil.showToast(getString(R.string.toast_update_successful));

                Intent intent = new Intent(UpdatePasswordActivity.this, MainActivity.class);
                startActivity(intent);
                captchaTimer.cancel();
                finish();

            }

            //请求失败时回调
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
     * 返回按钮点击事件
     */
    @OnClick(R.id.btn_return)
    public void onBtnReturnClicked() {
        Intent intent = new Intent(UpdatePasswordActivity.this, LoginActivity.class);
        startActivity(intent);
        captchaTimer.cancel();
        finish();
    }


    /**
     * 用于TextInputEditText控件显示错误信息
     *
     * @param textInputEditText 控件对象
     * @param error             错误信息
     */
    private void showError(TextInputEditText textInputEditText, String error) {
        textInputEditText.setError(error);
        textInputEditText.setFocusable(true);
        textInputEditText.setFocusableInTouchMode(true);
        textInputEditText.requestFocus();
    }

    /**
     * 验证用户输入是否正确
     *
     * @return 正确为true
     */
    private boolean isDataTrue() {
        boolean flag = true;
//        if (editCaptcha.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
//            showError(editCaptcha, getString(R.string.error_captcha_empty_illegal));
//            flag = false;
//        } else if (!editCaptcha.getText().toString().trim().equals(verifyCode)) {
//            showError(editCaptcha, getString(R.string.error_captcha_number_illegal));
//            flag = false;
//        } else
        if (editPassword.getText().toString().trim().length() < Config.PASSWORD_MIN_LIMIT) {
            showError(editPassword, getString(R.string.error_password_min_limit));
            flag = false;
        } else if (editPassword.getText().toString().trim().length() > Config.PASSWORD_MAX_LIMIT) {
            showError(editPassword, getString(R.string.error_password_max_limit));
            flag = false;
        } else if (!editRepeatPassword.getText().toString().trim().equals(editPassword.getText().toString().trim())) {
            showError(editRepeatPassword, getString(R.string.error_passwords_inconsistent));
            flag = false;
        }
        return flag;
    }

}
