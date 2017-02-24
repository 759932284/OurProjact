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

public class HomeRecommondAlbumAdapter extends RecyclerView.Adapter<HomeRecommondAlbumAdapter.HomeRecommondAlbumViewHolder> {

    private Context context;
    private List<String> dataAlbum;

    public void setDataAlbum(List<String> dataAlbum) {
        this.dataAlbum = dataAlbum;
        notifyDataSetChanged();
    }

    public HomeRecommondAlbumAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HomeRecommondAlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_album_detail, null);
        HomeRecommondAlbumViewHolder holder = new HomeRecommondAlbumViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeRecommondAlbumViewHolder holder, int position) {
        holder.albumTitleTv.setText(dataAlbum.get(position));
        holder.albumAuthorTv.setText(dataAlbum.get(position));
    }

    @Override
    public int getItemCount() {
        return dataAlbum != null ? dataAlbum.size() : 0;
    }

    class HomeRecommondAlbumViewHolder extends RecyclerView.ViewHolder{
        ImageView albumIv;
        TextView albumTitleTv;
        TextView albumAuthorTv;
        public HomeRecommondAlbumViewHolder(View itemView) {
            super(itemView);
            albumIv = (ImageView)itemView.findViewById(R.id.recommond_album_detail_iv);
            albumTitleTv = (TextView)itemView.findViewById(R.id.recommond_album_detail_title_tv);
            albumAuthorTv = (TextView)itemView.findViewById(R.id.recommond_album_detail_author_tv);
        }
    }
}
