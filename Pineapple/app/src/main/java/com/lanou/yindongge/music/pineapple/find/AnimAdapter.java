package com.lanou.yindongge.music.pineapple.find;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/23.
 */

public class AnimAdapter extends RecyclerView.Adapter {
    private Context context;

    public AnimAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return 0;
            case 1:
                return 1;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View itemView;
        switch (viewType) {
            case 0:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_gossip_text, parent, false);
                holder = new AnimTextHolder(itemView);
                break;
            case 1:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_gossip, parent, false);
                holder = new AnimImageHolder(itemView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 0:
                AnimTextHolder animTextHolder = (AnimTextHolder) holder;
                animTextHolder.text.setText(context.getResources().getString(R.string.laugh));
                break;
            case 1:
                AnimImageHolder animImageHolder = (AnimImageHolder) holder;
                AnimImageAdapter animImageAdapter = new AnimImageAdapter(context);
                List<String> datas = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    datas.add("文字" + i);
                }
                animImageAdapter.setData(datas);
                animImageHolder.rv.setLayoutManager(new LinearLayoutManager
                        (context, LinearLayoutManager.HORIZONTAL, false));
                animImageHolder.rv.setAdapter(animImageAdapter);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class AnimTextHolder extends RecyclerView.ViewHolder {
        TextView text;
        public AnimTextHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.gossip_text_text);
        }
    }
    class AnimImageHolder extends RecyclerView.ViewHolder {
        RecyclerView rv;
        public AnimImageHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.gossip_rv);
        }
    }
}
