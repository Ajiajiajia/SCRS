package cn.abtion.neuqercc.team.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.main.MainActivity;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.mine.models.TeamMemberResponse;
import cn.abtion.neuqercc.team.adapters.TeamMemberListAdapter;
import cn.abtion.neuqercc.team.models.EstablishTeamRequest;
import cn.abtion.neuqercc.team.models.TeamMemberListModel;
import cn.abtion.neuqercc.utils.ToastUtil;
import cn.abtion.neuqercc.widget.CustomLinearLayoutManager;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author lszr
 * @since 2017/11/22 下午9:40
 * email wsyglszr@gmail.com
 */

public class EstablishTeamFinishActivity extends ToolBarActivity {

    private ArrayList<TeamMemberResponse> teamMemberListModels;

    @BindView(R.id.recylerview_team_member)
    RecyclerView recylerviewTeamMember;
    @BindView(R.id.txt_team_name)
    TextView txtTeamName;
    @BindView(R.id.txt_contest_name)
    TextView txtContestName;
    @BindView(R.id.txt_team_declaration)
    TextView txtTeamDeclaration;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_establish_team_finish;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {


        initTeamInformation();

        initTitle();


        recylerviewTeamMember.setNestedScrollingEnabled(false);
        teamMemberListModels = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            TeamMemberResponse teamMemberResponse = new TeamMemberResponse("asfmca", 12, "asfhnc", "ausfa");
            teamMemberListModels.add(teamMemberResponse);
        }

        TeamMemberListAdapter teamMemberListAdapter = new TeamMemberListAdapter(this, teamMemberListModels);
        recylerviewTeamMember.setLayoutManager(new CustomLinearLayoutManager(this, CustomLinearLayoutManager.VERTICAL, false));
        recylerviewTeamMember.setAdapter(teamMemberListAdapter);
    }

    private void initTeamInformation() {

        Intent intent=getIntent();
        String gson=intent.getStringExtra("establishTeamRequest");
        EstablishTeamRequest establishTeamRequest= new Gson().fromJson(gson,EstablishTeamRequest.class);

        txtTeamName.setText(establishTeamRequest.getTeam_name());
        txtContestName.setText(establishTeamRequest.getCompetition_desc());
        txtTeamDeclaration.setText(establishTeamRequest.getDeclaration());

    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.btn_invite_friend)
    public void onInviteFriendViewClicked() {

    }

    public void initTitle() {
        setActivityTitle(getString(R.string.title_team_information));
        setTextOver(getString(R.string.title_confirm));
        TextView textView = (TextView) getToolbar().findViewById(R.id.txt_toolbar_over);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processEstablishTeam();
                Intent intent = new Intent(EstablishTeamFinishActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void processEstablishTeam() {
        //弹出progressDialog
        progressDialog.setMessage(getString(R.string.dialog_wait_moment));
        progressDialog.show();

        Intent intent = getIntent();
        String gson = intent.getStringExtra("establishTeamRequest");
        EstablishTeamRequest establishTeamRequest = new Gson().fromJson(gson, EstablishTeamRequest.class);

        //网络请求
        RestClient.getService().establishTeam(establishTeamRequest).enqueue(new DataCallback<APIResponse>() {
            @Override
            public void onDataResponse(Call<APIResponse> call, Response<APIResponse> response) {
                ToastUtil.showToast(getString(R.string.establish_team_success));
            }

            @Override
            public void onDataFailure(Call<APIResponse> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {
                if (progressDialog.isShowing()) {
                    disMissProgressDialog();
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
