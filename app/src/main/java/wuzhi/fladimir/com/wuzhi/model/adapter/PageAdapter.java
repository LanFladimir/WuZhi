package wuzhi.fladimir.com.wuzhi.model.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Sc_Ji on 2018-01-02.
 * Fragment adapter
 */

public class PageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;

    public PageAdapter(FragmentManager fm, ArrayList<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
