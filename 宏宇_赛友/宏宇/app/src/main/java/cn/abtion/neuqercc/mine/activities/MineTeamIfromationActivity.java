package cn.abtion.neuqercc.mine.activities;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.mine.models.MineTeamInformationRequest;
import cn.abtion.neuqercc.mine.models.TeamMemberResponse;
import cn.abtion.neuqercc.network.APIResponse;
import cn.abtion.neuqercc.network.DataCallback;
import cn.abtion.neuqercc.network.RestClient;
import cn.abtion.neuqercc.team.adapters.TeamMemberListAdapter;
import cn.abtion.neuqercc.team.models.TeamMemberListModel;
import cn.abtion.neuqercc.widget.CustomLinearLayoutManager;
import retrofit2.Call;
import retrofit2.Response;

public class MineTeamIfromationActivity extends ToolBarActivity {


    @BindView(R.id.txt_mine_team_name)
    TextView txtTeamName;
    @BindView(R.id.txt_contest_name)
    TextView txtContestName;
    @BindView(R.id.txt_team_declaration)
    TextView txtTeamDeclaration;
    @BindView(R.id.recylerview_team_member)
    RecyclerView recylerviewTeamMember;

    private List<TeamMemberResponse> teamMemberResponseList = new ArrayList<TeamMemberResponse>();
    MineTeamInformationRequest teamInformationRequest;
    public static int teamId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_team_ifromation;
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

        loadTeamMemberInformation();
    }


    protected void initTitle() {

        setActivityTitle(getString(R.string.title_team_information));
        setTextOver(getString(R.string.txt_edit));
        TextView txtTitleEdit = (TextView) getToolbar().findViewById(R.id.txt_toolbar_over);

        txtTitleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MineTeamIfromationActivity.this, UpdateTeamInformationActivity.class);
                intent.putExtra("teamInformation", new Gson().toJson(teamInformationRequest));
                startActivity(intent);
            }
        });
    }


    /**
     * 加载队伍信息
     */
    protected void loadTeamInformation() {

        Intent intent = getIntent();
        teamId = intent.getIntExtra("teamId", -1);

        RestClient.getService().mineTeamInformation(teamId).enqueue(new DataCallback<APIResponse<MineTeamInformationRequest>>() {
            @Override
            public void onDataResponse(Call<APIResponse<MineTeamInformationRequest>> call, Response<APIResponse<MineTeamInformationRequest>> response) {

                teamInformationRequest = response.body().getData();
                txtTeamName.setText(teamInformationRequest.getTeamName());
                txtContestName.setText(teamInformationRequest.getCompetitionDesc());
                txtTeamDeclaration.setText(teamInformationRequest.getDeclaration());

            }

            @Override
            public void onDataFailure(Call<APIResponse<MineTeamInformationRequest>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });


    }


    /**
     * 加载成员信息
     */
    protected void loadTeamMemberInformation() {


        RestClient.getService().mineTeamMember(teamId).enqueue(new DataCallback<APIResponse<List<TeamMemberResponse>>>() {
            @Override
            public void onDataResponse(Call<APIResponse<List<TeamMemberResponse>>> call, Response<APIResponse<List<TeamMemberResponse>>> response) {

                teamMemberResponseList = response.body().getData();
                initRecyclerView();

            }

            @Override
            public void onDataFailure(Call<APIResponse<List<TeamMemberResponse>>> call, Throwable t) {

            }

            @Override
            public void dismissDialog() {

            }
        });

    }


    protected void initRecyclerView() {

        recylerviewTeamMember.setNestedScrollingEnabled(false);
        TeamMemberListAdapter teamMemberListAdapter = new TeamMemberListAdapter(this, teamMemberResponseList);
        recylerviewTeamMember.setLayoutManager(new CustomLinearLayoutManager(this, CustomLinearLayoutManager.VERTICAL, false));
        recylerviewTeamMember.setAdapter(teamMemberListAdapter);

    }


    @OnClick(R.id.btn_join_team)
    public void onButtonJoinTeamClicked() {

    }

}
