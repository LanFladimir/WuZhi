package wuzhi.fladimir.com.wuzhi.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;
import wuzhi.fladimir.com.wuzhi.R;
import wuzhi.fladimir.com.wuzhi.model.entity.Now;
import wuzhi.fladimir.com.wuzhi.ui.activity.DiaryActivity;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by Sc_Ji on 2018-01-03.
 * now adapter
 */

public class LastAdapter extends RecyclerView.Adapter<LastAdapter.nowHolder> {
    private ArrayList<Now> mNows = new ArrayList<>();
    private Context mContext;

    public LastAdapter(ArrayList<Now> mNows, Context mContext) {
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
        final Now item = mNows.get(position);

        holder.item_now_name.setText(item.getUserName());
        holder.item_now_sign.setText(item.getUserSign());
        Glide.with(mContext).load(item.getUserImg()).into(holder.item_now_img);
        Glide.with(mContext).load(item.getUserImg())
                .apply(bitmapTransform(new BlurTransformation(25)))
                .into(holder.item_now_bg);
        holder.item_now_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DiaryActivity.class);
                intent.putExtra("id", item.getUserId());
                intent.putExtra("name",
                        item.getUserName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNows.size();
    }

    class nowHolder extends RecyclerView.ViewHolder {
        ImageView item_now_bg;
        ImageView item_now_img;
        TextView item_now_name;
        TextView item_now_sign;

        public nowHolder(View itemView) {
            super(itemView);
            item_now_bg = itemView.findViewById(R.id.item_now_bg);
            item_now_img = itemView.findViewById(R.id.item_now_img);
            item_now_name = itemView.findViewById(R.id.item_now_name);
            item_now_sign = itemView.findViewById(R.id.item_now_sign);
        }
    }
}
