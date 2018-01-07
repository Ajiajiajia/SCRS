package cn.abtion.neuqercc.mine.activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.mine.models.ShowHonorResponse;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.DialogUtil;
import cn.abtion.neuqercc.utils.ToastUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author fhyPayaso
 * @since 2017/11/20 19:57
 * email fhyPayaso@qq.com
 */

public class HonorUpdateActivity extends ToolBarActivity {


    /**
     * 动态申请权限
     */
    private static final String[] PERMISSION_EXTERNAL_STORAGE = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_EXTERNAL_STORAGE = 100;
    public final int TAKE_PHOTO_FLAG = 1;
    public final int SET_IMG_FLAG = 100;


    /**
     * 获取默认的年级和擅长领域信息
     */
    private String currentTime;

    /**
     * 获取当前item在列表的第几项
     */
    private int flagTime = 0;

    /**
     * 列表中点击时暂存点击位置
     */
    private int flagTempTime = 0;

    private String[] honorTimeList;

    /**
     * 信息上传类型，0为修改，1为添加
     */
    private int uploadType = 0;


    /**
     * 判断证书图片是否为空
     */
    private boolean flagUpLoad = true;
    private int currentOrder;

    private String filePath;
    private Intent intent;
    private ShowHonorResponse honorResponse;

    Button btnTakePhoto;
    Button btnFromAlbum;
    Button btnCancel;

    @BindView(R.id.img_add_honor)
    ImageView imgAddHonor;
    @BindView(R.id.btn_honor_confirm_edit)
    Button btnHonorConfirmEdit;
    @BindView(R.id.edit_event_name)
    EditText editEventName;
    @BindView(R.id.txt_update_time)
    TextView txtUpdateTime;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_honor_update;
    }

    @Override
    protected void initVariable() {


        setActivityTitle(getString(R.string.title_certificate_editing));
        verifyStoragePermissions(HonorUpdateActivity.this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

        loadIntent();
    }

    @OnClick(R.id.img_add_honor)
    public void onImgAddHonorClicked() {

        showHonorDialog();
    }

    public void loadIntent() {

        intent = getIntent();
        honorTimeList = intent.getStringArrayExtra("timeList");
        honorResponse = new Gson().fromJson(intent.getStringExtra("honorInformation"), ShowHonorResponse.class);

        //0代表编辑证书，1代表添加证书
        if (intent.getStringExtra("honorWall").equals("0")) {

            uploadType = 0;
            editEventName.setText(honorResponse.getGloryName());
            currentTime = honorResponse.getGloryTime();
            txtUpdateTime.setText(currentTime);
            currentOrder = honorResponse.getOrder();
            String picUrl = honorResponse.getGloryPicUrl();
            Glide.with(this).load(picUrl).into(imgAddHonor);

        } else {

            uploadType = 1;
            flagUpLoad = false;
            imgAddHonor.setImageResource(R.drawable.bg_add_honor);
            txtUpdateTime.setText(honorTimeList[0]);
        }

    }


    public void showHonorDialog() {


        final AlertDialog dialogAddHonor = new AlertDialog.Builder(this, R.style.dialog_bottom).create();
        dialogAddHonor.show();
        dialogAddHonor.getWindow().setContentView(R.layout.dialog_mine_avatar);

        Window window = dialogAddHonor.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);


        btnTakePhoto = (Button) dialogAddHonor.findViewById(R.id.btn_take_photo);
        btnFromAlbum = (Button) dialogAddHonor.findViewById(R.id.btn_from_album);
        btnCancel = (Button) dialogAddHonor.findViewById(R.id.btn_cancel);

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialogAddHonor.isShowing()) {
                    dialogAddHonor.dismiss();
                }

                Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takeIntent, TAKE_PHOTO_FLAG);
            }
        });

        btnFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialogAddHonor.isShowing()) {
                    dialogAddHonor.dismiss();
                }

                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, SET_IMG_FLAG);

            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialogAddHonor.isShowing()) {
                    dialogAddHonor.dismiss();
                }
            }
        });
    }


    /**
     * 返回选择的图片
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SET_IMG_FLAG && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            filePath = picturePath;
            cursor.close();

            imgAddHonor.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            flagUpLoad = true;

        } else if (requestCode == TAKE_PHOTO_FLAG && resultCode == RESULT_OK && null != data) {

            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            imgAddHonor.setImageBitmap(bitmap);

            flagUpLoad = true;
        }

    }


    /**
     * 确认修改点击事件
     */
    @OnClick(R.id.btn_honor_confirm_edit)
    public void onBtnConfirmClicked() {

        if (isDataTrue()) {

            uploadHonorInformation();
        } else {
            ToastUtil.showToast(getString(R.string.toast_lack_information));
        }

    }


    public void uploadHonorInformation() {


        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("glory_pic", file.getName(), requestBody);


        //修改证书网络请求
        if (uploadType == 0) {

            RestClient.getService().uploadHonor(LoginActivity.phoneNumber,
                    honorResponse.setHonorMap(), part).enqueue(new DataCallback<APIResponse>() {

                @Override
                public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {

                    ToastUtil.showToast(getString(R.string.toast_edit_successful));
                    finish();
                }

                @Override
                public void onDataFailure(Call<APIResponse> call, Throwable t) {

                }

                @Override
                public void dismissDialog() {

                }
            });


        } else {


            /**
             * 添加证书网络请求
             */
            honorResponse = new ShowHonorResponse(editEventName.getText().toString(),
                    txtUpdateTime.getText().toString(), filePath);

            RestClient.getService().addHonor(LoginActivity.phoneNumber,
                    honorResponse.setHonorMap(), part).enqueue(new DataCallback<APIResponse>() {

                @Override
                public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {

                    ToastUtil.showToast("添加成功");
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
    }


    /**
     * 动态申请权限
     *
     * @param activity
     */
    private void verifyStoragePermissions(Activity activity) {
        int permissionWrite = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionWrite != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_EXTERNAL_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }


    /**
     * 判断信息是否完整
     *
     * @return
     */
    private boolean isDataTrue() {
        boolean flag = true;

        if (editEventName.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
            flag = false;
        } else if (!flagUpLoad) {
            flag = false;
        }

        return flag;
    }


    @OnClick(R.id.liner_update_time)
    public void onLinerUpdateTimeClicked() {

        showTimeList();
    }


    public void showTimeList() {


        for (int i = 0; i < honorTimeList.length; i++) {

            if (honorTimeList[i].equals(currentTime)) {
                flagTime = i;
            }
        }


        DialogUtil.NativeDialog nativeDialog = new DialogUtil().new NativeDialog().singleInit(HonorUpdateActivity.this);

        nativeDialog.setSingleChoice(honorTimeList, flagTime, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                flagTempTime = which;

            }
        });

        nativeDialog.setNegativeButton("取消");
        nativeDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                flagTime = flagTempTime;
                txtUpdateTime.setText(honorTimeList[flagTime].trim());
            }
        });

        nativeDialog.showNativeDialog();


    }

}
