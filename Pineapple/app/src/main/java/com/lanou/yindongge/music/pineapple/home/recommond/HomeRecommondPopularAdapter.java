package com.lanou.yindongge.music.pineapple.home.recommond;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;

import java.util.List;

/**
 * Created by dllo on 17/2/21.
 */

public class HomeRecommondPopularAdapter extends RecyclerView.Adapter<HomeRecommondPopularAdapter.HomeRecommondPopularViewHolder> {

    private Context context;
    private List<String> dataPopular;

    public void setDataPopular(List<String> dataPopular) {
        this.dataPopular = dataPopular;
        notifyDataSetChanged();
    }

    public HomeRecommondPopularAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HomeRecommondPopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_popular_detail, null);
        HomeRecommondPopularViewHolder holder = new HomeRecommondPopularViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeRecommondPopularViewHolder holder, int position) {
        holder.popularOrderTv.setText(position + 1 + "");
        holder.popularTitleTv.setText(dataPopular.get(position));
        holder.popularNumTv.setText(dataPopular.get(position));
    }

    @Override
    public int getItemCount() {
        return dataPopular != null ? dataPopular.size() : 0;
    }

    class HomeRecommondPopularViewHolder extends RecyclerView.ViewHolder{

        TextView popularOrderTv;
        TextView popularTitleTv;
        TextView popularNumTv;
        public HomeRecommondPopularViewHolder(View itemView) {
            super(itemView);
            popularOrderTv = (TextView)itemView.findViewById(R.id.popular_order_tv);
            popularTitleTv = (TextView)itemView.findViewById(R.id.popular_title_tv);
            popularNumTv = (TextView)itemView.findViewById(R.id.popular_number_tv);
        }
    }
}
