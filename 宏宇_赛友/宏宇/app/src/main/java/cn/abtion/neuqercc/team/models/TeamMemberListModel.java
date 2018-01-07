package cn.abtion.neuqercc.team.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author lszr
 * @since 2017/11/21 下午3:06
 * email wsyglszr@gmail.com
 */

public class TeamMemberListModel extends BaseModel {



    private String position;
    private String name;
    private String goodAt;


    public TeamMemberListModel(String position,String name,String goodAt) {

        this.position = position;
        this.goodAt = goodAt;
        this.name = name;
    }


    /**
     * 获取队内职务
     * @return
     */
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 获取姓名
     * @return
     */
    public String getName() {
        return name;
    }


    public void setName(String name) {

        this.name = name;
    }


    /**
     * 获取擅长方向
     * @return
     */
    public String getGoodAt() {
        return goodAt;
    }

    public void setGoodAt(String goodAt) {
        this.goodAt = goodAt;
    }

}
