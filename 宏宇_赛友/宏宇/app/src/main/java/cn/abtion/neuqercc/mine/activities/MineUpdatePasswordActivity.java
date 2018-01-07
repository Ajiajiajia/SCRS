package cn.abtion.neuqercc.mine.activities;

import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.account.models.UpdatePasswordRequest;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.ToastUtil;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author fhyPayaso
 * @since 2017/11/20 17:52
 * email fhyPayaso@qq.com
 */

public class MineUpdatePasswordActivity extends ToolBarActivity {


    public static final int FLAG_CORRECT = 0;
    public static final int FLAG_LACK_ERROR = 1;
    public static final int FLAG_PASSWORD_ERROR = 2;
    public static final int FLAG_PASSWORD_DIFFERENT = 3;

    private UpdatePasswordRequest updatePasswordRequest;

    @BindView(R.id.btn_mine_confirm_changes)
    Button btnMineConfirmChanges;
    @BindView(R.id.edit_input_old_password)
    EditText editOldPassword;
    @BindView(R.id.edit_input_new_password)
    EditText editNewPassword;
    @BindView(R.id.edit_input_repeat_password)
    EditText editRepeatPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_update_password;
    }

    @Override
    protected void initVariable() {
        setActivityTitle(getString(R.string.txt_update_password));

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.btn_mine_confirm_changes)
    public void onBtnConfirmClicked() {

        switch (isDataTrue()) {

            case FLAG_LACK_ERROR:
                ToastUtil.showToast(getString(R.string.toast_lack_information));
                break;
            case FLAG_PASSWORD_ERROR:
                ToastUtil.showToast(getString(R.string.toast_password_error));
                break;
            case FLAG_PASSWORD_DIFFERENT:
                ToastUtil.showToast(getString(R.string.error_passwords_inconsistent));
                break;
            default:

                updatePasswordRequest = new UpdatePasswordRequest();
                updatePasswordRequest.setPhone(LoginActivity.phoneNumber);
                updatePasswordRequest.setPassword(editNewPassword.getText().toString().trim());
                updatePassword();

                break;

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






    private int isDataTrue() {

        int flag=FLAG_CORRECT;

        if(editOldPassword.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
            flag =FLAG_LACK_ERROR;
        } else if (editNewPassword.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
            flag = FLAG_LACK_ERROR;
        } else if(editRepeatPassword.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
            flag = FLAG_LACK_ERROR;
        } else if (!editOldPassword.getText().toString().trim().equals(LoginActivity.password)) {
            flag = FLAG_PASSWORD_ERROR;
        } else if (!editNewPassword.getText().toString().trim().equals(editRepeatPassword.getText().toString().trim())) {
            flag = FLAG_PASSWORD_DIFFERENT;
        }
        return flag;
    }


}
