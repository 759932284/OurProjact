package com.lanou.ping.music.qddddd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/3/1.
 */

public class MainAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<DataBean.T1348648517839Bean> hor;

    private List<DataBean.T1348648517839Bean> ver;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void setVer(List<DataBean.T1348648517839Bean> ver) {
        this.ver = ver;
        notifyDataSetChanged();
    }

    public void setHor(List<DataBean.T1348648517839Bean> hor) {
        this.hor = hor;
        notifyDataSetChanged();
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
        switch (viewType) {
            case 0:
                View itemView1 = LayoutInflater.from(context).inflate(R.layout.item, null);
                holder = new MHolder(itemView1);
                break;
            case 1:
                View itemView2 = LayoutInflater.from(context).inflate(R.layout.item_item, null);
                holder = new NHolder(itemView2);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                ChildAdapter childAdapter = new ChildAdapter(context);
                MHolder holder1 = (MHolder) holder;
                holder1.rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
                holder1.rv.setAdapter(childAdapter);
                childAdapter.setHor(hor);


                childAdapter.setOnChildClickListener(new ChildAdapter.OnChildClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                break;


            case 1:
                ItemAdapter itemAdapter = new ItemAdapter(context);
                NHolder holder2 = (NHolder) holder;
                holder2.recyclerView.setLayoutManager(new LinearLayoutManager(context));
                holder2.recyclerView.setAdapter(itemAdapter);

                itemAdapter.setVer(ver);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }


    class MHolder extends RecyclerView.ViewHolder {
        RecyclerView rv;
        public MHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.recyclerView_child);
        }
    }

    class NHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        public NHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView_item);
        }
    }

}
