package wuzhi.fladimir.com.wuzhi.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;
import wuzhi.fladimir.com.wuzhi.R;
import wuzhi.fladimir.com.wuzhi.model.entity.Now;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by Sc_Ji on 2018-01-03.
 * now adapter
 */

public class NowAdapter extends RecyclerView.Adapter<NowAdapter.nowHolder> {
    private ArrayList<Now> mNows = new ArrayList<>();
    private Context mContext;

    public NowAdapter(ArrayList<Now> mNows, Context mContext) {
        this.mNows = mNows;
        this.mContext = mContext;
    }

    @Override
    public nowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new nowHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_now, null));
    }

    public void notify(ArrayList<Now> nows) {
        mNows = nows;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(nowHolder holder, int position) {
        Now item = mNows.get(position);

        holder.item_now_name.setText(item.getUserName());
        Glide.with(mContext).load(item.getUserImg()).into(holder.item_now_img);
        Glide.with(mContext).load(item.getUserImg())
                .apply(bitmapTransform(new BlurTransformation(25)))
                .into(holder.item_now_bg);
    }

    @Override
    public int getItemCount() {
        return mNows.size();
    }

    class nowHolder extends RecyclerView.ViewHolder {
        ImageView item_now_bg;
        QMUIRadiusImageView item_now_img;
        TextView item_now_name;
        TextView item_now_sign;
        TextView item_now_novel;

        public nowHolder(View itemView) {
            super(itemView);
            item_now_bg = itemView.findViewById(R.id.item_now_bg);
            item_now_img = itemView.findViewById(R.id.item_now_img);
            item_now_name = itemView.findViewById(R.id.item_now_name);
            item_now_sign = itemView.findViewById(R.id.item_now_sign);
            item_now_novel = itemView.findViewById(R.id.item_now_novel);
        }
    }
}
