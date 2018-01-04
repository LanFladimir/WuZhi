package wuzhi.fladimir.com.wuzhi.model.entity;

import java.util.ArrayList;

/**
 * Created by Sc_Ji on 2018-01-03.
 * 用户日记
 */

public class Now {
    private String userId;
    private String userName;
    private String userImg;
    private String userSign;
    private String date;
    private String flowers;
    private ArrayList<novels> novels;

    public ArrayList<Now.novels> getNovels() {
        return novels;
    }

    public void setNovels(ArrayList<Now.novels> novels) {
        this.novels = novels;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getFlowers() {
        return flowers;
    }

    public void setFlowers(String flowers) {
        this.flowers = flowers;
    }

    public static class novels {
        String novelTime;
        String novelContent;

        public String getNovelTime() {
            return novelTime;
        }

        public void setNovelTime(String novelTime) {
            this.novelTime = novelTime;
        }

        public String getNovelContent() {
            return novelContent;
        }

        public void setNovelContent(String novelContent) {
            this.novelContent = novelContent;
        }
    }
}
