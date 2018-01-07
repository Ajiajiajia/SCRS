package cn.abtion.neuqercc.team.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author lszr
 * @since 2017/12/12 下午11:19
 * email wsyglszr@gmail.com
 */

public class InitRecommendTeamResponse extends BaseModel{

    private int id;
    private String team_name;
    private String competition_desc;
    private String declaration;
    private String good_at;
    private String team_member;
    private String team_position;

    public InitRecommendTeamResponse() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTeam_member() {
        return team_member;
    }

    public void setTeam_member(String team_member) {
        this.team_member = team_member;
    }

    public String getTeam_position() {
        return team_position;
    }

    public void setTeam_position(String team_position) {
        this.team_position = team_position;
    }
}
