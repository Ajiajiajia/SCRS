package cn.abtion.neuqercc.team.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author lszr
 * @since 2017/11/27 下午6:27
 * email wsyglszr@gmail.com
 */

public class RecommendTeamListModel extends BaseModel {
    private String teamName;
    private String contestName;
    private String wantDirection;
    private String declaration;
    private int id;

    public RecommendTeamListModel(String teamName, String contestName, String wantDirection, String declaration, int id) {
        this.teamName = teamName;
        this.contestName = contestName;
        this.wantDirection = wantDirection;
        this.declaration = declaration;
        this.id = id;
    }

    public String getTeamName() {

        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getWantDirection() {
        return wantDirection;
    }

    public void setWantDirection(String wantDirection) {
        this.wantDirection = wantDirection;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
