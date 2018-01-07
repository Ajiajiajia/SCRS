package cn.abtion.neuqercc.rain.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * Created by heaijia on 2017/12/29.
 */

public class InitAllTeamResponse extends BaseModel{

    private int id;
    private String team_name;
    private String competition_desc;
    private String declaration;
    private String good_at;

    public InitAllTeamResponse(){

    }

    public void setId(int id){
        this.id=id;
    }
    public void setTeam_name(String team_name){
        this.team_name=team_name;
    }
    public void setCompetition_desc(String competition_desc){
        this.competition_desc=competition_desc;
    }
    public void setDeclaration(String declaration){
        this.declaration=declaration;
    }
    public void setGood_at(String good_at){
        this.good_at=good_at;
    }



    public int getId(){
        return id;
    }
    public String getTeam_name(){
        return team_name;
    }
    public String getCompetition_desc(){
        return competition_desc;
    }
    public String getDeclaration(){
        return declaration;
    }
    public String getGood_at(){
        return good_at;
    }


}
