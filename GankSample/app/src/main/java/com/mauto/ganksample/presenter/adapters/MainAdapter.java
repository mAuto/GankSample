package com.mauto.ganksample.presenter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mauto.ganksample.R;
import com.mauto.repository.model.pojo.GankBean;
import com.mauto.repository.model.pojo.MainModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by haohuidong on 18-9-26.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context mContext;
    public MainAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.main_item_layout, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder != null) {
            GankBean bean = mData.get(position);

            holder.tvTitle.setText(bean.type);
            holder.tvDesc.setText(bean.desc);

            if (bean.images != null && bean.images.length > 0){
                Glide.with(mContext)
                        .load(bean.images[0])
                        .into(holder.ivCover);
            } else {
                holder.ivCover.setImageResource(R.mipmap.ic_launcher_round);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            mData = new ArrayList<>();
        return mData.size();
    }

    private List<GankBean> mData;
    public void fillData(MainModel model) {
        if (model == null || model.results == null)
            return;

        if (mData == null)
            mData = new ArrayList<>();

        int size = mData.size();
        mData.clear();
        notifyItemRangeRemoved(0, size);
        mData.addAll((Collection<? extends GankBean>) model.results.clone());
        size = mData.size();
        notifyItemRangeChanged(0, size);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivCover;
        private TextView tvTitle, tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_cover);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc =  itemView.findViewById(R.id.tv_desc);
        }
    }
}
