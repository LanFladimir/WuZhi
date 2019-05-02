package wuzhi.fladimir.com.wuzhi.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import wuzhi.fladimir.com.wuzhi.R;
import wuzhi.fladimir.com.wuzhi.model.entity.Follow;
import wuzhi.fladimir.com.wuzhi.ui.activity.DiaryActivity;

/**
 * Created by Sc_Ji on 2018-01-03.
 * follow adapter
 */

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.followHolder> {
    private ArrayList<Follow> mFollowList = new ArrayList<>();
    private Context mContext;

    public FollowAdapter(ArrayList<Follow> mFollowList) {
        this.mFollowList = mFollowList;
    }

    public void notify(ArrayList<Follow> follows) {
        mFollowList = follows;
        notifyDataSetChanged();
    }

    @Override
    public followHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new followHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_follow, null));
    }

    @Override
    public void onBindViewHolder(followHolder holder, int position) {
        final Follow follower = mFollowList.get(position);
        holder.userName.setText(follower.getUserName());
        holder.userSign.setText(follower.getUserSign());
        holder.userImg.setImageResource(R.mipmap.ic_favicon);
        Glide.with(mContext)
                .load(follower.getUserImg())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.userImg);

        holder.item_fo_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DiaryActivity.class);
                intent.putExtra("id", follower.getUserId());
                intent.putExtra("name",
                        follower.getUserName());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFollowList.size();
    }

    class followHolder extends RecyclerView.ViewHolder {
        ImageView userImg;
        TextView userName;
        TextView userSign;
        RelativeLayout item_fo_parent;

        public followHolder(View itemView) {
            super(itemView);
            userImg = itemView.findViewById(R.id.item_fo_img);
            userName = itemView.findViewById(R.id.item_fo_name);
            userSign = itemView.findViewById(R.id.item_fo_sign);
            item_fo_parent = itemView.findViewById(R.id.item_fo_parent);
        }
    }
}
