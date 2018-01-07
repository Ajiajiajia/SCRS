package cn.abtion.neuqercc.snow.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * Created by heaijia on 2018/1/5.
 */

public class AllTeamListModel extends BaseModel{

    private int id;
    private String teamName;
    private String contestName;
    private String declaration;
    private String wantDirection;

    public AllTeamListModel(int id,String teamName,String contestName,String declaration,String wantDirection){
        this.id=id;
        this.teamName=teamName;
        this.contestName=contestName;
        this.wantDirection=wantDirection;
        this.declaration=declaration;
    }

    public void setId(int id){
        this.id=id;
    }
    public void setTeamName(){
        this.teamName=teamName;
    }
    public void setContestName(){
        this.contestName=contestName;
    }
    public void setDeclaration(){
        this.declaration=declaration;
    }
    public void setWantDirection(){
        this.wantDirection=wantDirection;
    }




    public int getId(){
        return id;
    }
    public String getContestName() {
        return contestName;
    }
    public String getTeamName(){
        return teamName;
    }
    public String getDeclaration(){
        return declaration;
    }
    public String getWantDirection(){
        return wantDirection;
    }
}
