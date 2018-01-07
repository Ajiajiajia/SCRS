package cn.abtion.neuqercc.team.models;

import android.os.Parcel;
import android.os.Parcelable;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author lszr
 * @since 2017/11/26 下午8:22
 * email wsyglszr@gmail.com
 */

public class AllTeamListModel extends BaseModel  {

    private String teamName;
    private String contestName;
    private String wantDirection;
    private String declaration;
    private int id;


    public AllTeamListModel(String teamName, String contestName, String wantDirection, String declaration, int id) {
        this.teamName = teamName;
        this.contestName = contestName;
        this.wantDirection = wantDirection;
        this.declaration = declaration;
        this.id = id;
    }



    public int getId() {

        return id;
    }

    public void setId(int id) {
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



}
