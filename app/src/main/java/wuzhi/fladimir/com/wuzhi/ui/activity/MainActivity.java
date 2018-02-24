package wuzhi.fladimir.com.wuzhi.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import wuzhi.fladimir.com.wuzhi.model.adapter.PageAdapter;
import wuzhi.fladimir.com.wuzhi.R;
import wuzhi.fladimir.com.wuzhi.ui.fragment.FragmentLast;
import wuzhi.fladimir.com.wuzhi.ui.fragment.FragmentFollow;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mContentViewPager;
    private PageAdapter mPagerAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> mTitles = new ArrayList<>();
    private Toolbar mTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        mTopBar = findViewById(R.id.main_topbar);
        //mTopBar.setTitle("吾志");
        mTopBar.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_color_blue));
        mTabLayout = findViewById(R.id.main_segment);
        mContentViewPager = findViewById(R.id.main_viewpager);

        mFragments.add(new FragmentLast());
        mFragments.add(new FragmentFollow());
        mTitles.add("此刻");
        mTitles.add("关注");
        mPagerAdapter = new PageAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mTabLayout.setupWithViewPager(mContentViewPager, false);

        mContentViewPager.setAdapter(mPagerAdapter);
        mContentViewPager.setCurrentItem(0, false);
    }
}
