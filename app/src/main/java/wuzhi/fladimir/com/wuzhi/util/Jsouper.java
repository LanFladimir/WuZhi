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

    public static ArrayList<Now> getLastDiary() {
        ArrayList<Now> mNows = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://wuzhi.me/last").get();
            Elements table = doc.getElementsByTag("table");
            Element tbody = table.get(0);
            Elements trs = tbody.getElementsByTag("tr");
            for (Element tr : trs) {
                Elements tds = tr.getElementsByTag("td");
                for (Element td : tds) {
                    Now now = new Now();
                    Element img = td.getElementsByTag("span").get(0).getElementsByTag("img").get(0);
                    now.setUserImg(img.attr("src"));
                    now.setUserImg(img.attr("alt"));
                    mNows.add(now);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mNows;
    }
}
