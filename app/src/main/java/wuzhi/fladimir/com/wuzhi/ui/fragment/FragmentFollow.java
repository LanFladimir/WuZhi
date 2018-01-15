package wuzhi.fladimir.com.wuzhi.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import wuzhi.fladimir.com.wuzhi.R;
import wuzhi.fladimir.com.wuzhi.model.adapter.FollowAdapter;
import wuzhi.fladimir.com.wuzhi.model.database.MySqliteHelper;
import wuzhi.fladimir.com.wuzhi.model.database.SqliteManager;
import wuzhi.fladimir.com.wuzhi.model.entity.Follow;

/**
 * Created by Sc_Ji on 2018-01-02.
 * 关注列表
 */

public class FragmentFollow extends BaseFragment {
    private RecyclerView frg_follow_recycler;
    private SwipeRefreshLayout frg_follow_refresh;
    private ArrayList<Follow> mFollower = new ArrayList<>();
    private MySqliteHelper mySqliteHelper;
    private FollowAdapter mAdapter;

    @Override
    protected int setRootView() {
        return R.layout.fragment_follow;
    }

    @Override
    protected void initView() {
        frg_follow_recycler = mRootView.findViewById(R.id.frg_follow_recycler);
        frg_follow_refresh = mRootView.findViewById(R.id.frg_follow_refresh);
        frg_follow_refresh.setRefreshing(true);
        frg_follow_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    @Override
    protected void initData() {
        mySqliteHelper = SqliteManager.getIntance(mContext);
        mFollower = mySqliteHelper.getFollowList();

        mAdapter = new FollowAdapter(mFollower);
        frg_follow_recycler.setLayoutManager(new LinearLayoutManager(mContext));
        frg_follow_recycler.setAdapter(mAdapter);
        frg_follow_refresh.setRefreshing(false);
    }
}