package com.lanou.yindongge.music.pineapple.find;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lanou.yindongge.music.pineapple.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

public class FindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    private static final int SEARCH = 0;
    private static final int GOSSIP = 1;
    private static final int FUN = 2;
    private static final int ANIM = 3;


    public FindAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return SEARCH;
            case 1:
                return GOSSIP;
            case 2:
                return FUN;
            case 3:
                return ANIM;
        }
        return GOSSIP;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View itemView;
        switch (viewType) {
            case SEARCH:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
                holder = new SearchHolder(itemView);
                break;
            case GOSSIP:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_gossip, parent, false);
                holder = new GossipHolder(itemView);
                break;
            case FUN:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_gossip, parent, false);
                holder = new FunHolder(itemView);
                break;
            case ANIM:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_gossip, parent, false);
                holder = new AnimHolder(itemView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case SEARCH:
                SearchAdapter searchAdapter = new SearchAdapter(context);
                SearchHolder searchHolder = (SearchHolder) holder;
                List<String> search = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    search.add("搜索" + i);
                }
                searchAdapter.setSearch(search);
                searchHolder.searchRv.setLayoutManager(new GridLayoutManager(context, 3));
                searchHolder.searchRv.setAdapter(searchAdapter);
                break;
            case GOSSIP:
                GossipAdapter gossipAdapter = new GossipAdapter(context);
                GossipHolder gossipHolder = (GossipHolder) holder;
                gossipHolder.gossipRv.setLayoutManager(new LinearLayoutManager(context));
                gossipHolder.gossipRv.setAdapter(gossipAdapter);
                break;
            case FUN:
                FunAdapter funAdapter = new FunAdapter(context);
                FunHolder funHolder = (FunHolder) holder;
                funHolder.funRv.setLayoutManager(new LinearLayoutManager(context));
                funHolder.funRv.setAdapter(funAdapter);
                break;
            case ANIM:
                AnimAdapter animAdapter = new AnimAdapter(context);
                AnimHolder animHolder = (AnimHolder) holder;
                animHolder.animRv.setLayoutManager(new LinearLayoutManager(context));
                animHolder.animRv.setAdapter(animAdapter);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class SearchHolder extends RecyclerView.ViewHolder {
        RecyclerView searchRv;
        public SearchHolder(View itemView) {
            super(itemView);
            searchRv = (RecyclerView) itemView.findViewById(R.id.search_rv);
        }
    }

    class GossipHolder extends RecyclerView.ViewHolder {
        RecyclerView gossipRv;
        public GossipHolder(View itemView) {
            super(itemView);
            gossipRv = (RecyclerView) itemView.findViewById(R.id.gossip_rv);
        }
    }

    class FunHolder extends RecyclerView.ViewHolder {
        RecyclerView funRv;
        public FunHolder(View itemView) {
            super(itemView);
            funRv = (RecyclerView) itemView.findViewById(R.id.gossip_rv);
        }
    }

    class AnimHolder extends RecyclerView.ViewHolder {
        RecyclerView animRv;
        public AnimHolder(View itemView) {
            super(itemView);
            animRv = (RecyclerView) itemView.findViewById(R.id.gossip_rv);
        }
    }
}
