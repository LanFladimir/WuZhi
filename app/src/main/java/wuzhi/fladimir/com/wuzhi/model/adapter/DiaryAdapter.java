package wuzhi.fladimir.com.wuzhi.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import wuzhi.fladimir.com.wuzhi.R;
import wuzhi.fladimir.com.wuzhi.model.entity.Now;

/**
 * Created by Sc_Ji on 2018-01-15.
 * 日记
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.diaryHolder> {
    private ArrayList<Now.diary> diaryList = new ArrayList<>();

    public DiaryAdapter(ArrayList<Now.diary> diaryList) {
        this.diaryList = diaryList;
    }

    public void notifyData(ArrayList<Now.diary> diaryList) {
        this.diaryList = diaryList;
        this.notifyDataSetChanged();
    }

    @Override
    public diaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new diaryHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_diary, null));
    }

    @Override
    public void onBindViewHolder(diaryHolder holder, int position) {
        holder.item_diary_time.setText(diaryList.get(position).getDiaryTime());
        holder.item_diary_content.setText(diaryList.get(position).getDiaryContent());
    }

    @Override
    public int getItemCount() {
        return diaryList.size();
    }

    class diaryHolder extends RecyclerView.ViewHolder {
        TextView item_diary_time;
        TextView item_diary_content;

        public diaryHolder(View itemView) {
            super(itemView);
            item_diary_time = itemView.findViewById(R.id.item_diary_time);
            item_diary_content = itemView.findViewById(R.id.item_diary_content);
        }
    }
}
