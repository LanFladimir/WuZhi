package wuzhi.fladimir.com.wuzhi.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import java.util.ArrayList;

import wuzhi.fladimir.com.wuzhi.model.adapter.PageAdapter;
import wuzhi.fladimir.com.wuzhi.R;
import wuzhi.fladimir.com.wuzhi.ui.fragment.FragmentLast;
import wuzhi.fladimir.com.wuzhi.ui.fragment.FragmentFollow;

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
        mTopBar.addRightImageButton(R.mipmap.icon_topbar_overflow, R.id.main_topbar)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showBottomSheetList();
                    }
                });
        mTopBar.setTitle("吾志");
        mTopBar.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_color_blue));
        mTabSegment = findViewById(R.id.main_segment);
        mContentViewPager = findViewById(R.id.main_viewpager);

        mFragments.add(new FragmentLast());
        mFragments.add(new FragmentFollow());
        mPagerAdapter = new PageAdapter(getSupportFragmentManager(), mFragments);

        mContentViewPager.setAdapter(mPagerAdapter);
        mContentViewPager.setCurrentItem(0, false);
        mTabSegment.setHasIndicator(true);
        mTabSegment.setIndicatorWidthAdjustContent(true);
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

    private void showBottomSheetList() {
        new QMUIBottomSheet.BottomListSheetBuilder(mContext)
                .addItem("Login")
                .setOnSheetItemClickListener(new QMUIBottomSheet.
                        BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView,
                                        int position, String tag) {
                        dialog.dismiss();
                        switch (position) {
                            case 0:
                                startActivity(new Intent(mContext, LoginActivity.class));
                                break;
                            default:
                                break;
                        }
                    }
                })
                .build()
                .show();
    }
}
