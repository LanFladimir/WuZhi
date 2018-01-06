package wuzhi.fladimir.com.wuzhi.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Sc_Ji on 2018-01-03.
 * 用户日记
 */

public class Now implements Parcelable {
    private String userId;
    private String userName;
    private String userImg;
    private String userSign;
    private String date;
    private String flowers;
    private ArrayList<diary> diary;

    public ArrayList<diary> getDiary() {
        return diary;
    }

    public void setDiary(ArrayList<diary> diary) {
        this.diary = diary;
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

    public static class diary {
        String diaryTime;
        String diaryContent;

        public String getDiaryTime() {
            return diaryTime;
        }

        public void setDiaryTime(String diaryTime) {
            this.diaryTime = diaryTime;
        }

        public String getDiaryContent() {
            return diaryContent;
        }

        public void setDiaryContent(String diaryContent) {
            this.diaryContent = diaryContent;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.userImg);
        dest.writeString(this.userSign);
        dest.writeString(this.date);
        dest.writeString(this.flowers);
        dest.writeList(this.diary);
    }

    public Now() {
    }

    protected Now(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.userImg = in.readString();
        this.userSign = in.readString();
        this.date = in.readString();
        this.flowers = in.readString();
        this.diary = new ArrayList<Now.diary>();
        in.readList(this.diary, Now.diary.class.getClassLoader());
    }

    public static final Parcelable.Creator<Now> CREATOR = new Parcelable.Creator<Now>() {
        @Override
        public Now createFromParcel(Parcel source) {
            return new Now(source);
        }

        @Override
        public Now[] newArray(int size) {
            return new Now[size];
        }
    };
}
