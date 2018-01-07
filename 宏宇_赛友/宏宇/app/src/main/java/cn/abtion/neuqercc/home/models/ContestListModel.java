package cn.abtion.neuqercc.home.models;

/**
 * @author lszr
 * @since 2017/10/1 下午3:13
 * email wsyglszr@gmail.com
 */

public class ContestListModel {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String title;
    private String summary;
    private String contestTime;
    private String signTime;

    public ContestListModel(int id, String title, String summary, String contestTime, String signTime) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.contestTime = contestTime;
        this.signTime = signTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContestTime() {
        return contestTime;
    }

    public void setContestTime(String contestTime) {
        this.contestTime = contestTime;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }
}
