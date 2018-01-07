package cn.abtion.neuqercc.mine.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author fhyPayaso
 * @since 2017/12/10 17:46
 * email fhyPayaso@qq.com
 */

public class MineTeamListResponse extends BaseModel {

    private int team_id;
    private String team_name;
    private String competition_desc;
    private int team_member_num;


    public void MineTeamListRequest() {

    }


    /**
     * 获取队伍id
     * @return
     */

    public int getTeamId() {

        return team_id;
    }

    public void setTeamId(int teamId) {

        this.team_id = teamId;
    }

    /**
     * 获取队伍名称
     * @return
     */

    public String getTeamName() {

        return team_name;
    }

    public void setTeamName(String teamName) {
        this.team_name = teamName;
    }


    /**
     * 获取比赛名称
     * @return
     */
    public String getCompetitionDesc() {
        return competition_desc;
    }

    public void setCompetitionDesc(String competitionDesc) {
        this.competition_desc = competitionDesc;
    }




    public int getTeamMemberNum() {
        return team_member_num;
    }

    public void setTeamMemberNum(int teamMemberNum) {
        this.team_member_num = teamMemberNum;
    }

}
