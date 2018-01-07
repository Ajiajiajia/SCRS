package cn.abtion.neuqercc.mine.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author fhyPayaso
 * @since 2017/12/10 21:54
 * email fhyPayaso@qq.com
 */

public class UpdateTeamInformationRequest extends BaseModel {

    private int team_id;
    private String team_name;
    private String competition_desc;
    private String declaration;
    private String good_at;

    public UpdateTeamInformationRequest(int team_id, String team_name, String competition_desc, String declaration, String good_at) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.competition_desc = competition_desc;
        this.declaration = declaration;
        this.good_at = good_at;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getCompetition_desc() {
        return competition_desc;
    }

    public void setCompetition_desc(String competition_desc) {
        this.competition_desc = competition_desc;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getGood_at() {
        return good_at;
    }

    public void setGood_at(String good_at) {
        this.good_at = good_at;
    }
}
