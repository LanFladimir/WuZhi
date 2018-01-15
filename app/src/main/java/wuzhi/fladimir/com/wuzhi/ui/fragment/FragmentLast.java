package wuzhi.fladimir.com.wuzhi.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import wuzhi.fladimir.com.wuzhi.R;
import wuzhi.fladimir.com.wuzhi.model.adapter.LastAdapter;
import wuzhi.fladimir.com.wuzhi.model.entity.Now;
import wuzhi.fladimir.com.wuzhi.util.Jsouper;

/**
 * Created by Sc_Ji on 2018-01-02.
 * Now
 */

public class FragmentLast extends BaseFragment {
    private RecyclerView frg_now_recycler;
    private ArrayList<Now> mNow = new ArrayList<>();
    private LastAdapter mAdapter;
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(mContext, "更新列表!", Toast.LENGTH_LONG).show();
            mAdapter.notify(mNow);
        }
    };

    @Override
    protected int setRootView() {
        return R.layout.fragment_last;
    }

    @Override
    protected void initView() {
        frg_now_recycler = mRootView.findViewById(R.id.frg_now_recycler);
    }

    @Override
    protected void initData() {
        mAdapter = new LastAdapter(mNow, mContext);
        frg_now_recycler.setLayoutManager(new GridLayoutManager(mContext,3));
        frg_now_recycler.setAdapter(mAdapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mNow = Jsouper.getLastDiary();
                /*for (int i = 0; i < mNow.size(); i++) {
                    mNow.set(i, Jsouper.getCompleteNovel(mNow.get(i).getUserId()));
                }*/
                mHandler.sendEmptyMessageDelayed(0, 1000 * 3);
            }
        }).start();
    }
}
