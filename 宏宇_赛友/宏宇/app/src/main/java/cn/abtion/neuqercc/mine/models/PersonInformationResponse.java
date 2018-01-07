package cn.abtion.neuqercc.mine.models;

import java.util.List;

import cn.abtion.neuqercc.base.models.BaseModel;
import cn.abtion.neuqercc.home.models.InitCrouselFigureRequest;

/**
 * @author fhyPayaso
 * @since 2017/11/29 18:07
 * email fhyPayaso@qq.com
 */

public class PersonInformationResponse extends BaseModel {


    private String username;
    private String phone;
    private int phonesee;
    private String pic;
    private String name;
    private int namesee;
    private String major;
    private int grade;
    private int studentid;
    private int gender;
    private String good_at;
    private int team_num;



    public PersonInformationResponse() {

    }

    /**
     * 获取手机号
     * @return
     */

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    /**
     * 获取用户名
     * @return
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取真实姓名
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别
     * @return
     */
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
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
     * 获取擅长方向
     * @return
     */
    public int getTeamNum() {
        return team_num;
    }

    public void setTeamNum(int teamNum) {
        this.team_num = teamNum;
    }


    /**
     * 获取图片url
     * @return
     */
    public String getPicture() {
        return pic;
        
    }

    public void setPicture(String picture) {
        this.pic = picture;
    }



    /**
     * 获取年级
     */
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


    /**
     * 获取学号
     */
    public int getStudentId() {
        return studentid;
    }

    public void setStudentId(int studentid) {
        this.studentid = studentid;
    }


    /**
     * 获取专业
     */
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


    /**
     * 获取电话是否可见
     * @return
     */
    public int getPhoneSee() {
        return phonesee;
    }

    public void setPhoneSee(int phoneSee) {

        this.phonesee = phoneSee;
    }


    /**
     * 获取姓名是否可见
     * @return
     */
    public int getNameSee() {
        return namesee;
    }

    public void setNameSee(int nameSee) {
        this.namesee = nameSee;
    }



}
