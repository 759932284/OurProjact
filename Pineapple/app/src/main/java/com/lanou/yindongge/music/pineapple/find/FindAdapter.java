package com.lanou.yindongge.music.pineapple.find;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.net.OkHttpManager;
import com.lanou.yindongge.music.pineapple.net.OnNetResultListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import okhttp3.Response;

/**
 * Created by dllo on 17/2/22.
 */

public class FindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    private List<ZoneListBean> allList;
    private List<ZoneListBean.VideoSetListBean> gossip;
    private List<ZoneListBean.VideoSetListBean> fun;
    private List<ZoneListBean.VideoSetListBean> anim;
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
        OkHttpManager.getInstance().startGetRequest(Url.ZONELIST, new OnNetResultListener() {

            @Override
            public void onSuccessListener(String result) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<ZoneListBean>>(){}.getType();
                allList = gson.fromJson(result, type);
                gossip = allList.get(0).getVideoSetList();
                fun = allList.get(1).getVideoSetList();
                anim = allList.get(2).getVideoSetList();
            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });
        switch (viewType) {
            case SEARCH:
                final SearchAdapter searchAdapter = new SearchAdapter(context);
                SearchHolder searchHolder = (SearchHolder) holder;

                OkHttpManager.getInstance().startGetRequest(Url.SEARCH, new OnNetResultListener() {
                    @Override
                    public void onSuccessListener(String result) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<SearchBean>>(){}.getType();
                        List<SearchBean> searchDatas = gson.fromJson(result, type);
                        searchAdapter.setSearchList(searchDatas);
                    }
                    @Override
                    public void onFailureListener(String errMsg) {
                        Log.d("FindAdapter", errMsg + "");
                    }
                });
                searchHolder.searchRv.setLayoutManager(new GridLayoutManager(context, 3));
                searchHolder.searchRv.setAdapter(searchAdapter);
                break;
            case GOSSIP:
                final GossipAdapter gossipAdapter = new GossipAdapter(context);
                GossipHolder gossipHolder = (GossipHolder) holder;
                gossipHolder.gossipTv.setText("游戏杂谈");
                gossipHolder.gossipRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                gossipHolder.gossipRv.setAdapter(gossipAdapter);
                gossipAdapter.setGossipData(gossip);
                break;
            case FUN:
                FunAdapter funAdapter = new FunAdapter(context);
                FunHolder funHolder = (FunHolder) holder;
                funHolder.funTv.setText("搞笑");
                funHolder.funRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                funHolder.funRv.setAdapter(funAdapter);
                funAdapter.setFunList(fun);
                break;
            case ANIM:
                AnimAdapter animAdapter = new AnimAdapter(context);
                AnimHolder animHolder = (AnimHolder) holder;
                animHolder.animTv.setText("动画");
                animHolder.animRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                animHolder.animRv.setAdapter(animAdapter);
                animAdapter.setAnimData(anim);
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
        TextView gossipTv;
        RecyclerView gossipRv;
        public GossipHolder(View itemView) {
            super(itemView);
            gossipRv = (RecyclerView) itemView.findViewById(R.id.gossip_rv);
            gossipTv = (TextView)itemView.findViewById(R.id.kind_tv);
        }
    }

    class FunHolder extends RecyclerView.ViewHolder {
        TextView funTv;
        RecyclerView funRv;
        public FunHolder(View itemView) {
            super(itemView);
            funTv = (TextView)itemView.findViewById(R.id.kind_tv);
            funRv = (RecyclerView) itemView.findViewById(R.id.gossip_rv);
        }
    }

    class AnimHolder extends RecyclerView.ViewHolder {
        TextView animTv;
        RecyclerView animRv;
        public AnimHolder(View itemView) {
            super(itemView);
            animTv = (TextView)itemView.findViewById(R.id.kind_tv);
            animRv = (RecyclerView) itemView.findViewById(R.id.gossip_rv);
        }
    }
}
