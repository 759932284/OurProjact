package com.lanou.yindongge.music.pineapple.find;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.net.OkHttpManager;
import com.lanou.yindongge.music.pineapple.net.OnNetResultListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

public class GossipAdapter extends RecyclerView.Adapter<GossipAdapter.GossipImageViewHolder> {
    private Context context;

    private List<ZoneListBean.VideoSetListBean> gossipData;

    public GossipAdapter(Context context) {
        this.context = context;
    }

    public void setGossipData(List<ZoneListBean.VideoSetListBean> gossipData) {
        this.gossipData = gossipData;
        notifyDataSetChanged();
    }

    @Override
    public GossipImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gossip_image, null);
        GossipImageViewHolder holder = new GossipImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GossipImageViewHolder holder, int position) {
        holder.gossipTv.setText(gossipData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return gossipData != null ? gossipData.size() : 0;
    }

    class GossipImageViewHolder extends RecyclerView.ViewHolder {
        ImageView gossipIv;
        TextView gossipTv;
        public GossipImageViewHolder(View itemView) {
            super(itemView);
            gossipIv =  (ImageView)itemView.findViewById(R.id.gossip_image_image);
            gossipTv = (TextView)itemView.findViewById(R.id.gossip_image_text);
        }
    }


}
