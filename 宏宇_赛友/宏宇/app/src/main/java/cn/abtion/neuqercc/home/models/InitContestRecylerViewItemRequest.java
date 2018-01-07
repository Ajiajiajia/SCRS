package cn.abtion.neuqercc.home.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author lszr
 * @since 2017/12/9 下午11:58
 * email wsyglszr@gmail.com
 */

public class InitContestRecylerViewItemRequest  extends BaseModel{

    private int id;
    private String name;
    private String desc;
    private String short_desc;
    private String registration_time;
    private String competition_time;
    private String pic;
    private String type;



    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public InitContestRecylerViewItemRequest() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public String getRegistration_time() {
        return registration_time;
    }

    public void setRegistration_time(String registration_time) {
        this.registration_time = registration_time;
    }

    public String getCompetition_time() {
        return competition_time;
    }

    public void setCompetition_time(String competition_time) {
        this.competition_time = competition_time;
    }
}
