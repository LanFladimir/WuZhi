package wuzhi.fladimir.com.wuzhi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;

import wuzhi.fladimir.com.wuzhi.fragment.FragmentNow;
import wuzhi.fladimir.com.wuzhi.fragment.FragmentFollow;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private QMUITabSegment mTabSegment;
    private ViewPager mContentViewPager;
    private PageAdapter mPagerAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private QMUITopBar mTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        mTopBar = findViewById(R.id.main_topbar);
        mTopBar.setTitle("吾志");
        mTopBar.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_color_blue));
        mTabSegment = findViewById(R.id.main_segment);
        mContentViewPager = findViewById(R.id.main_viewpager);

        mFragments.add(new FragmentNow());
        mFragments.add(new FragmentFollow());
        mPagerAdapter = new PageAdapter(getSupportFragmentManager(), mFragments);

        mContentViewPager.setAdapter(mPagerAdapter);
        mContentViewPager.setCurrentItem(0, false);
        mTabSegment.addTab(new QMUITabSegment.Tab("cike"));
        mTabSegment.addTab(new QMUITabSegment.Tab("guanzhu"));
        mTabSegment.setupWithViewPager(mContentViewPager, false);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        mTabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                mTabSegment.hideSignCountView(index);
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {
                mTabSegment.hideSignCountView(index);
            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
    }
}
