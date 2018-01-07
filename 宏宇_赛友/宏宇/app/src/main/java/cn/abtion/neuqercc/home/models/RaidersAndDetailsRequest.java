package cn.abtion.neuqercc.home.models;

import cn.abtion.neuqercc.base.models.BaseModel;

/**
 * @author lszr
 * @since 2017/12/11 下午3:12
 * email wsyglszr@gmail.com
 */

public class RaidersAndDetailsRequest extends BaseModel {
    private int id;
    private String name;
    private String desc;
    private String pic;
    private String type;
    private String god_name;
    private String god_desc;
    private String god_pic;

    public RaidersAndDetailsRequest() {
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGod_name() {
        return god_name;
    }

    public void setGod_name(String god_name) {
        this.god_name = god_name;
    }

    public String getGod_desc() {
        return god_desc;
    }

    public void setGod_desc(String god_desc) {
        this.god_desc = god_desc;
    }

    public String getGod_pic() {
        return god_pic;
    }

    public void setGod_pic(String god_pic) {
        this.god_pic = god_pic;
    }



}
