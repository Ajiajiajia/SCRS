package cn.abtion.neuqercc.team.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author lszr
 * @since 2017/12/13 下午2:19
 * email wsyglszr@gmail.com
 */

public class EstablishTeamRequest extends BaseModel {
    private String phone;
    private String team_name;
    private String competition_desc;
    private String declaration;
    private String good_at;

    public EstablishTeamRequest(String phone, String team_name, String competition_desc, String declaration, String good_at) {
        this.phone = phone;
        this.team_name = team_name;
        this.competition_desc = competition_desc;
        this.declaration = declaration;
        this.good_at = good_at;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
