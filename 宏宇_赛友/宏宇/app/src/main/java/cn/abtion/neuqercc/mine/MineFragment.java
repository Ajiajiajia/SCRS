package cn.abtion.neuqercc.mine;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.account.activities.LoginActivity;
import cn.abtion.neuqercc.base.fragments.BaseFragment;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.mine.activities.HonorInformationActivity;
import cn.abtion.neuqercc.mine.activities.MineTeamListActivity;
import cn.abtion.neuqercc.mine.activities.SettingActivity;
import cn.abtion.neuqercc.mine.activities.UpdateInformationActivity;
import cn.abtion.neuqercc.mine.adapters.GridHonorAdapter;
import cn.abtion.neuqercc.mine.models.PersonInformationResponse;
import cn.abtion.neuqercc.mine.models.ShowHonorResponse;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.utils.DensityUtil;
import cn.abtion.neuqercc.widget.GradientScrollView;
import cn.abtion.neuqercc.widget.HonorGridView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author abtion.
 * @since 17/9/24 上午2:15.
 * email caiheng@hrsoft.net.
 */

public class MineFragment extends BaseFragment {


    @BindView(R.id.rlayout_mine_team)
    RelativeLayout rlayoutMineTeam;
    @BindView(R.id.scroll_mine_information)
    GradientScrollView scrollMineInformation;
    @BindView(R.id.rlayout_mine_title)
    RelativeLayout rlayoutMineTitle;
    @BindView(R.id.mine_header_bg)
    LinearLayout mineHeaderBg;
    @BindView(R.id.txt_team_title)
    TextView txtTeamTitle;
    @BindView(R.id.title_mine_edit)
    ImageView titleMineEdit;
    @BindView(R.id.mine_grid_honor)
    HonorGridView gridHonor;
    @BindView(R.id.mine_avatar)
    CircleImageView imgAvatar;
    @BindView(R.id.txt_mine_major)
    TextView txtMajor;
    @BindView(R.id.txt_mine_grade)
    TextView txtGrade;
    @BindView(R.id.txt_mine_student_id)
    TextView txtStudentId;
    @BindView(R.id.txt_mine_good_at)
    TextView txtGoodAt;
    @BindView(R.id.txt_mine_team_num)
    TextView txtTeamNum;
    @BindView(R.id.txt_mine_name)
    TextView txtName;
    @BindView(R.id.txt_mine_phone_number)
    TextView txtPhoneNumber;
    @BindView(R.id.txt_mine_user_name)
    TextView txtUserName;
    @BindView(R.id.img_mine_gender)
    ImageView imgGender;
    @BindView(R.id.mine_team)
    TextView mineTeam;
    @BindView(R.id.mine_information)
    RelativeLayout mineInformation;
    @BindView(R.id.ic_name)
    ImageView icName;
    @BindView(R.id.ic_phone)
    ImageView icPhone;
    @BindView(R.id.mine_phone)
    RelativeLayout minePhone;
    @BindView(R.id.mine_profession)
    TextView mineProfession;
    @BindView(R.id.mine_grade)
    TextView mineGrade;
    @BindView(R.id.mine_id)
    TextView mineId;
    @BindView(R.id.mine_message)
    RelativeLayout mineMessage;
    @BindView(R.id.mine_txt_honor)
    TextView mineTxtHonor;
    @BindView(R.id.mine_honor)
    RelativeLayout mineHonor;


    private List<ShowHonorResponse> showHonorResponseList = new ArrayList<ShowHonorResponse>();
    private PersonInformationResponse informationResponse;
    private String imgAvatarUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        initScrollView();
    }

    @Override
    protected void loadData() {

        initPersonalInformation();
        initHonorWall();
    }

    @Override
    public void onResume() {

        initPersonalInformation();
        initHonorWall();
        super.onResume();
    }

    /**
     * 渐变式ToolBar
     */
    public void initScrollView() {

        scrollMineInformation.setScrollViewListener(new GradientScrollView.ScrollViewListener() {

            @Override
            public void onScrollChanged(GradientScrollView scrollView, int x, int y, int oldx, int oldy) {

                txtTeamTitle.setTextColor(Color.argb((int) Config.COLOR_MIN, Config.COLOR_MAX, Config.COLOR_MAX, Config.COLOR_MAX));

                if (y <= 0) {

                    rlayoutMineTitle.setBackgroundColor(Color.argb((int) Config.COLOR_MIN, Config.TITLE_BG_R, Config.TITLE_BG_G, Config.TITLE_BG_B));
                    txtTeamTitle.setTextColor(Color.argb((int) Config.COLOR_MIN, Config.COLOR_MAX, Config.COLOR_MAX, Config.COLOR_MAX));

                } else if (y > 0 && y < (mineHeaderBg.getHeight() - rlayoutMineTitle.getHeight())) {

                    float scale = (float) y / (mineHeaderBg.getHeight() - rlayoutMineTitle.getHeight());
                    float alpha = (Config.COLOR_MAX * scale);
                    rlayoutMineTitle.setBackgroundColor(Color.argb((int) alpha, Config.TITLE_BG_R, Config.TITLE_BG_G, Config.TITLE_BG_B));
                    txtTeamTitle.setTextColor(Color.argb((int) alpha, Config.COLOR_MAX, Config.COLOR_MAX, Config.COLOR_MAX));

                } else {
                    rlayoutMineTitle.setBackgroundColor(Color.argb((int) Config.COLOR_MAX, Config.TITLE_BG_R, Config.TITLE_BG_G, Config.TITLE_BG_B));
                    txtTeamTitle.setTextColor(Color.argb((int) Config.COLOR_MAX, Config.COLOR_MAX, Config.COLOR_MAX, Config.COLOR_MAX));
                }
            }
        });

    }

    /**
     * 获取个人信息网络请求
     */
    public void initPersonalInformation() {

        RestClient.getService().personalInformation(LoginActivity.phoneNumber).enqueue(new DataCallback<APIResponse<PersonInformationResponse>>() {

            @Override
            public void onDataResponse(Call<APIResponse<PersonInformationResponse>> call, Response<APIResponse<PersonInformationResponse>> response) {

                informationResponse = response.body().getData();
                setPersonalInformation();
            }

            @Override
            public void onDataFailure(Call<APIResponse<PersonInformationResponse>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }

        });
    }

    /**
     * 初始化个人信息
     */
    public void setPersonalInformation() {

        txtPhoneNumber.setText(informationResponse.getPhone().trim());
        txtUserName.setText(informationResponse.getUsername().trim());
        txtName.setText(informationResponse.getName().trim());
        txtGoodAt.setText(informationResponse.getGoodAt().trim());
        txtMajor.setText(informationResponse.getMajor().trim());
        txtTeamNum.setText(String.valueOf(informationResponse.getTeamNum()));
        txtGrade.setText(String.valueOf(informationResponse.getGrade()));
        txtStudentId.setText(String.valueOf(informationResponse.getStudentId()));

        if (informationResponse.getGender() == Config.FLAG_BOY) {
            imgGender.setImageResource(R.drawable.ic_mine_man);
        } else if (informationResponse.getGender() == Config.FLAG_GIRL) {
            imgGender.setImageResource(R.drawable.ic_mine_woman);
        }

        imgAvatarUrl = informationResponse.getPicture();
        Glide.with(this).load(imgAvatarUrl).into(imgAvatar);
    }


    /**
     * 初始化荣誉墙
     */
    public void initHonorWall() {

        RestClient.getService().showHonorRequest(LoginActivity.phoneNumber).enqueue(new DataCallback<APIResponse<List<ShowHonorResponse>>>() {

            //请求成功时回调
            @Override
            public void onDataResponse(Call<APIResponse<List<ShowHonorResponse>>> call, Response<APIResponse<List<ShowHonorResponse>>> response) {

                showHonorResponseList = response.body().getData();
                initGrid();
            }

            //请求失败时回调
            @Override
            public void onDataFailure(Call<APIResponse<List<ShowHonorResponse>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });
    }

    /**
     * 初始化Grid
     */
    public void initGrid() {

        GridHonorAdapter gridHonorAdapter = new GridHonorAdapter(getContext(), showHonorResponseList, false);
        gridHonor.setAdapter(gridHonorAdapter);
        gridHonor.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ShowHonorResponse showHonorResponse = showHonorResponseList.get(position);
                Intent intent = new Intent(getContext(), HonorInformationActivity.class);
                intent.putExtra(Config.INTENT_EXTRA_HONOR, new Gson().toJson(showHonorResponse));
                startActivity(intent);
            }
        });
    }


    /**
     * 更多信息图标点击事件
     */
    @OnClick(R.id.title_mine_edit)
    public void onImgEditClicked() {

        showDialog();
    }

    /**
     * 显示右上角弹窗
     */
    public void showDialog() {

        final AlertDialog dialog = new AlertDialog.Builder(getContext(), R.style.dialog_bottom).create();
        dialog.show();
        dialog.getWindow().setContentView(R.layout.pop_window_edit);
        dialog.setCancelable(true);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.END | Gravity.TOP);
        window.getDecorView().setPadding(0, DensityUtil.dip2px(getContext(), Config.DIALOG_PADDING_RIGHT_DP),
                DensityUtil.dip2px(getContext(), Config.DIALOG_PADDING_BOTTOM_DP), 0);

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = DensityUtil.dip2px(getContext(), Config.LP_WIDTH);
        lp.height = DensityUtil.dip2px(getContext(), Config.LP_HEIGHT);
        window.setAttributes(lp);

        CardView txtEditInformation = (CardView) dialog.findViewById(R.id.card_update_information);
        CardView txtMineSetting = (CardView) dialog.findViewById(R.id.card_setting);
        CardView txtMineTeam = (CardView) dialog.findViewById(R.id.card_mine_team);

        txtEditInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                Intent intent = new Intent(getContext(), UpdateInformationActivity.class);
                intent.putExtra(Config.INTENT_EXTRA_PERSON_INFORMATION, new Gson().toJson(informationResponse));
                startActivity(intent);
            }
        });

        txtMineSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

        txtMineTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                Intent intent = new Intent(getContext(), MineTeamListActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * 查看头像大图点击事件
     */
    @OnClick(R.id.mine_avatar)
    public void onImgAvaterClicked() {

        showFullImg();
    }

    public void showFullImg() {

        View view = View.inflate(getContext(), R.layout.item_dialog_full_img, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_full_dialog);
        Glide.with(this).load(imgAvatarUrl).into(imageView);
        builder.show();

    }

}
