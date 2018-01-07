package cn.abtion.neuqercc.mine.models;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cn.abtion.neuqercc.base.models.BaseModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author fhyPayaso
 * @since 2017/12/5 17:02
 * email fhyPayaso@qq.com
 */

public class UpdatePersonInformationRequest extends BaseModel {


    private String phone;
    private String username;
    private String name;
    private String good_at;
    private String major;
    private int studentid;
    private int gender;
    private int grade;
    private int namesee;
    private int phonesee;


    public UpdatePersonInformationRequest(String phone, String username, String name, String good_at, String major,
                                          int studentid, int gender, int grade, int namesee, int phonesee) {
        this.phone = phone;
        this.username = username;
        this.name = name;
        this.good_at = good_at;
        this.major = major;
        this.studentid = studentid;
        this.gender = gender;
        this.grade = grade;
        this.namesee = namesee;
        this.phonesee = phonesee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGood_at() {
        return good_at;
    }

    public void setGood_at(String good_at) {
        this.good_at = good_at;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getNamesee() {
        return namesee;
    }

    public void setNamesee(int namesee) {
        this.namesee = namesee;
    }

    public int getPhonesee() {
        return phonesee;
    }

    public void setPhonesee(int phonesee) {
        this.phonesee = phonesee;
    }

    public Map<String,Object> createCommitParams() {

        Map<String,Object> map = new HashMap<>();
        map.put("phone",phone);
        map.put("username",username);
        map.put("name",name);
        map.put("gender",gender);
        map.put("good_at",good_at);
        map.put("grade",grade);
        map.put("studentid",studentid);
        map.put("major",major);
        map.put("namesee",namesee);
        map.put("phonesee",phonesee);

        return map;
    }

}
