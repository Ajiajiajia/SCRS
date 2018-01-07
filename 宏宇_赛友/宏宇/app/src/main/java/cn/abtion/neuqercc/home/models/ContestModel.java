package cn.abtion.neuqercc.home.models;

/**
 * @author lszr
 * @since 2017/10/1 下午3:13
 * email wsyglszr@gmail.com
 */

public class ContestModel {
    private String title;
    private String cotestTime;
    private String signTime;

    public ContestModel(String title, String cotestTime, String signTime) {
        this.title = title;
        this.cotestTime = cotestTime;
        this.signTime = signTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCotestTime() {
        return cotestTime;
    }

    public void setCotestTime(String cotestTime) {
        this.cotestTime = cotestTime;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }
}
