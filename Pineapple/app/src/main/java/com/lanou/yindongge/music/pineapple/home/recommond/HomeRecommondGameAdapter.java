package com.lanou.yindongge.music.pineapple.home.recommond;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;

import java.util.List;

/**
 * Created by dllo on 17/2/21.
 */

public class HomeRecommondGameAdapter extends RecyclerView.Adapter<HomeRecommondGameAdapter.HomeRecommondGameViewHolder> {

    private Context context;
    private List<String> dataGame;
    private View gameHeaderView;
    public void setDataGame(List<String> dataGame) {
        this.dataGame = dataGame;
        notifyDataSetChanged();
    }

    public HomeRecommondGameAdapter(Context context, View gameHeaderView) {
        this.context = context;
        this.gameHeaderView = gameHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return 0;
        }
        else return 1;
    }

    @Override
    public HomeRecommondGameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeRecommondGameViewHolder holder = null;
        switch (viewType) {
            case 0:
                holder = new HomeRecommondGameViewHolder(gameHeaderView);
                break;
            case 1:
                View view = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_game_detail, null);
                holder = new HomeRecommondGameViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeRecommondGameViewHolder holder, int position) {

        if (isHeader(position)) {
            holder.gameHeaderTitleTv.setText(dataGame.get(position));
            holder.gameHeaderAuthorTv.setText(dataGame.get(position));
            Log.d("HomeRecommondGameAdapte", dataGame.get(position));
            return;
        }
        holder.gameDetailTitleTv.setText(dataGame.get(position - 1));
        holder.gameDetailAuthorTv.setText(dataGame.get(position - 1));
    }

    @Override
    public int getItemCount() {
        return dataGame != null ? dataGame.size() : 0 ;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    class HomeRecommondGameViewHolder extends RecyclerView.ViewHolder{
        TextView gameHeaderTitleTv;
        TextView gameHeaderAuthorTv;
        TextView gameDetailTitleTv;
        TextView gameDetailAuthorTv;
        public HomeRecommondGameViewHolder(View itemView) {
            super(itemView);
            gameHeaderTitleTv = (TextView)itemView.findViewById(R.id.home_recommond_title_tv);
            gameHeaderAuthorTv = (TextView)itemView.findViewById(R.id.home_recommond_author_tv);
            gameDetailTitleTv = (TextView)itemView.findViewById(R.id.recommond_game_detail_title_tv);
            gameDetailAuthorTv = (TextView)itemView.findViewById(R.id.recommond_game_detail_author_tv);
        }
    }
}
