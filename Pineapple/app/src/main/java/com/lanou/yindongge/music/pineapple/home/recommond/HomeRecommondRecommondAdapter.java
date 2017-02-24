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
 * Created by dllo on 17/2/21.
 */

public class HomeRecommondRecommondAdapter extends RecyclerView.Adapter<HomeRecommondRecommondAdapter.HomeRecommondRecommondViewHolder> {

    private Context context;
    private List<String> dataRecommond;

    public void setDataRecommond(List<String> dataRecommond) {
        this.dataRecommond = dataRecommond;
        notifyDataSetChanged();
    }

    public HomeRecommondRecommondAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HomeRecommondRecommondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_recommond_detail, null);
        HomeRecommondRecommondViewHolder holder = new HomeRecommondRecommondViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeRecommondRecommondViewHolder holder, int position) {
        holder.recommondTiltleTv.setText(dataRecommond.get(position));
        holder.recommondAuthorTv.setText(dataRecommond.get(position));
    }

    @Override
    public int getItemCount() {
        return dataRecommond != null ? dataRecommond.size() : 0;
    }

    class HomeRecommondRecommondViewHolder extends RecyclerView.ViewHolder{
        ImageView recommondIv;
        TextView recommondTiltleTv;
        TextView recommondAuthorTv;
        public HomeRecommondRecommondViewHolder(View itemView) {
            super(itemView);
            recommondIv = (ImageView)itemView.findViewById(R.id.recommond_recommond_iv);
            recommondTiltleTv = (TextView)itemView.findViewById(R.id.recommond_recommond_title_tv);
            recommondAuthorTv = (TextView)itemView.findViewById(R.id.recommond_recommond_anthor_tv);
        }
    }
}
