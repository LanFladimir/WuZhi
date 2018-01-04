package wuzhi.fladimir.com.wuzhi.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import wuzhi.fladimir.com.wuzhi.model.entity.Now;

/**
 * Created by Sc_Ji on 2018-01-03.
 * Jsoup Util
 */

public class Jsouper {
    private String AvAddress = "http://1024.2048xd.biz/pw/thread.php?fid=22&page=";
    private static String Url_LastDiary = "https://wuzhi.me/last";
    private static String Url_UserDiary = "https://wuzhi.me/u/";

    public static ArrayList<Now> getLastDiary() {
        ArrayList<Now> mNows = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(Url_LastDiary).get();
            Logger.e("getLastDiary", doc.text());
            Elements table = doc.getElementsByTag("table");
            Element tbody = table.get(0);
            Elements trs = tbody.getElementsByTag("tr");
            for (Element tr : trs) {
                Elements tds = tr.getElementsByTag("td");
                for (Element td : tds) {
                    Now now = new Now();
                    String id = td.getElementsByTag("a").get(0).attr("href");
                    now.setUserId(id.substring(3, id.length()));
                    Element img = td.getElementsByTag("span").get(0).getElementsByTag("img").get(0);
                    now.setUserImg(img.attr("src"));
                    now.setUserName(img.attr("alt"));
                    mNows.add(now);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mNows;
    }

    /**
     * 获取内容
     *
     * @return 255148
     */
    public static Now getCompleteNovel(String userId, String html) throws Exception {
        Now now = new Now();
        ArrayList<Now.novels> novelList = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        String sign;
        try {
            sign = doc.getElementsByClass("quote").get(0)
                    .getElementsByTag("span").get(0).text();
        } catch (Exception e) {
            sign = "";
        }
        String img = doc.getElementsByClass("img_shadow").get(0)
                .getElementsByTag("img").get(0).attr("src");
        Element content = doc.getElementsByClass("main_right").get(0);
        Elements dateInfo = content.getElementsByTag("span");
        String date = dateInfo.get(0).text();
        String flower = dateInfo.get(1).text();
        for (Element noteEach : content.getElementsByClass("note_each")) {
            Now.novels novel = new Now.novels();
            novel.setNovelContent(noteEach.getElementsByTag("div").get(0).text());
            novel.setNovelTime(noteEach.getElementsByTag("div").get(1).text());
            novelList.add(novel);
        }

        now.setUserId(userId);
        now.setUserName(doc.title());
        now.setUserImg(img);
        now.setDate(date);
        now.setFlowers(flower);
        now.setNovels(novelList);
        now.setUserSign(sign);
        return now;
    }
}
