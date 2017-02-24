package com.lanou.yindongge.music.pineapple.home.recommond;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;

import java.util.List;

/**
 * Created by dllo on 17/2/20.
 */

public class HomeRecommondBacteriaAdapter extends RecyclerView.Adapter<HomeRecommondBacteriaAdapter.BacteriaViewHolder> {

    private Context context;
    private List<String> data;

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public HomeRecommondBacteriaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BacteriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_bacteria_detail, null);
        BacteriaViewHolder holder = new BacteriaViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BacteriaViewHolder holder, int position) {
        holder.bacteriaTitleTv.setText(data.get(position));
        holder.bacteriaAuthorTv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class BacteriaViewHolder extends RecyclerView.ViewHolder{
        ImageView bacteriaIv;
        TextView bacteriaTitleTv;
        TextView bacteriaAuthorTv;
        public BacteriaViewHolder(View itemView) {
            super(itemView);
            bacteriaIv = (ImageView)itemView.findViewById(R.id.recommond_bacteria_iv);
            bacteriaTitleTv = (TextView)itemView.findViewById(R.id.recommond_bacteria_title_tv);
            bacteriaAuthorTv = (TextView)itemView.findViewById(R.id.recommond_bacteria_anthor_tv);
        }
    }
}
