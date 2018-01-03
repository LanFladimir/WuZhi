package wuzhi.fladimir.com.wuzhi.model.entity;

/**
 * Created by Sc_Ji on 2018-01-03.
 * 用户日记
 */

public class Now {
    private int userId;
    private String userName;
    private String userDiary;
    private String userImg;
    private String userSign;
    private String date;
    private String time;
    private String flowers;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDiary() {
        return userDiary;
    }

    public void setUserDiary(String userDiary) {
        this.userDiary = userDiary;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFlowers() {
        return flowers;
    }

    public void setFlowers(String flowers) {
        this.flowers = flowers;
    }
}
