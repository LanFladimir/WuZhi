package wuzhi.fladimir.com.wuzhi.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import wuzhi.fladimir.com.wuzhi.R;
import wuzhi.fladimir.com.wuzhi.model.entity.Follow;

/**
 * Created by Sc_Ji on 2018-01-03.
 * follow adapter
 */

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.followHolder> {
    private ArrayList<Follow> mFollowList = new ArrayList<>();

    public FollowAdapter(ArrayList<Follow> mFollowList) {
        this.mFollowList = mFollowList;
    }

    @Override
    public followHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new followHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_follow, null));
    }

    @Override
    public void onBindViewHolder(followHolder holder, int position) {
        Follow follower = mFollowList.get(position);
        holder.userName.setText(follower.getUserName());
        holder.userSign.setText(follower.getUserSign());
        holder.userImg.setImageResource(R.mipmap.ic_favicon);
        //holder.userImg.setBorderWidth(0);

    }

    @Override
    public int getItemCount() {
        return mFollowList.size();
    }

    class followHolder extends RecyclerView.ViewHolder {
       ImageView userImg;
        TextView userName;
        TextView userSign;

        public followHolder(View itemView) {
            super(itemView);
            userImg = itemView.findViewById(R.id.item_fo_img);
            userName = itemView.findViewById(R.id.item_fo_name);
            userSign = itemView.findViewById(R.id.item_fo_sign);
        }
    }
}
