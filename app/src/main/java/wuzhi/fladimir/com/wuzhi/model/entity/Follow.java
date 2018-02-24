package wuzhi.fladimir.com.wuzhi.model.entity;

/**
 * Created by Sc_Ji on 2018-01-03.
 * 关注
 */

public class Follow {
    private String userName;
    private String userSign;
    private String userId;
    private String userImg;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
