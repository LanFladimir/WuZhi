package wuzhi.fladimir.com.wuzhi.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import wuzhi.fladimir.com.wuzhi.R;
import wuzhi.fladimir.com.wuzhi.model.database.MySqliteHelper;
import wuzhi.fladimir.com.wuzhi.model.entity.Follow;
import wuzhi.fladimir.com.wuzhi.model.entity.Now;
import wuzhi.fladimir.com.wuzhi.util.Jsouper;
import wuzhi.fladimir.com.wuzhi.util.Logger;

/**
 * Created by Sc_Ji on 2018-01-02.
 * Now
 */

public class FragmentNow extends BaseFragment {
    private RecyclerView frg_now_recycler;
    private ArrayList<Now> mNow = new ArrayList<>();

    @Override
    protected int setRootView() {
        return R.layout.fragment_now;
    }

    @Override
    protected void initView() {
        frg_now_recycler = mRootView.findViewById(R.id.frg_now_recycler);
        frg_now_recycler.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mNow = Jsouper.getLastDiary();

            }
        }).start();
        //mNow = Jsouper.getLastDiary();
    }
}
