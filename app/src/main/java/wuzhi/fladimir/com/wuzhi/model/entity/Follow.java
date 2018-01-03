package wuzhi.fladimir.com.wuzhi.model.entity;

/**
 * Created by Sc_Ji on 2018-01-03.
 * 关注
 */

public class Follow {
    private String userName;
    private String userSign;
    private int userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }
}
