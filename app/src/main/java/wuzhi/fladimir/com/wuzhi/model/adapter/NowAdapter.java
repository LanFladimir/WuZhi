package wuzhi.fladimir.com.wuzhi.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import wuzhi.fladimir.com.wuzhi.R;
import wuzhi.fladimir.com.wuzhi.model.entity.Now;

/**
 * Created by Sc_Ji on 2018-01-03.
 */

public class NowAdapter extends RecyclerView.Adapter<NowAdapter.nowHolder> {
    private ArrayList<Now> mNows;

    public NowAdapter(ArrayList<Now> mNows) {
        this.mNows = mNows;
    }

    @Override
    public nowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new nowHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_now, null));
    }

    @Override
    public void onBindViewHolder(nowHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mNows.size();
    }

    class nowHolder extends RecyclerView.ViewHolder {

        public nowHolder(View itemView) {
            super(itemView);
        }
    }
}
