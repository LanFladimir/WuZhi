package wuzhi.fladimir.com.wuzhi.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sc_Ji on 2018-01-03.
 * BaseFragment
 */

public abstract class BaseFragment extends Fragment {
    public View mRootView;
    public Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setRootView(), container, false);
        mContext = getActivity();
        initView();
        initData();
        return mRootView;
    }

    protected abstract int setRootView();

    protected abstract void initView();

    protected abstract void initData();
}
