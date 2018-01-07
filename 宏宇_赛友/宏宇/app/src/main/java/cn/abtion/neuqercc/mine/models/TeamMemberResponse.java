package cn.abtion.neuqercc.mine.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author fhyPayaso
 * @since 2017/12/10 20:22
 * email fhyPayaso@qq.com
 */

public class TeamMemberResponse extends BaseModel{


    private String name;
    private int namesee;
    private String good_at;
    private  String team_position;

    public TeamMemberResponse(String name, int namesee, String good_at, String team_position) {
        this.name = name;
        this.namesee = namesee;
        this.good_at = good_at;
        this.team_position = team_position;
    }

    /**
     * 获取队员姓名
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取姓名是否可见
     * @return
     */
    public int getNamesee() {
        return namesee;
    }

    /**
     * 获取擅长方向
     * @return
     */
    public String getGoodAt() {
        return good_at;
    }

    public void setGoodAt(String goodAt) {
        this.good_at = goodAt;
    }


    /**
     * 获取队内职务
     * @return
     */
    public String getTeamPosition() {
        return team_position;
    }

    public void setTeamPosition(String teamPosition) {

        this.team_position = teamPosition;
    }



}
