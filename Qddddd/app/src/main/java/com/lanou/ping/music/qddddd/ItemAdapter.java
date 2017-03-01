package com.lanou.ping.music.qddddd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou.ping.music.qddddd.net.GlideManager;

import java.util.List;

/**
 * Created by dllo on 17/3/1.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder>{

    private Context context;

    private List<DataBean.T1348648517839Bean> ver;

    public ItemAdapter(Context context) {
        this.context = context;
    }

    public void setVer(List<DataBean.T1348648517839Bean> ver) {
        this.ver = ver;
        notifyDataSetChanged();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_image, null);
        ItemHolder holder = new ItemHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.textView.setText(ver.get(position).getTitle());
        GlideManager.getGlideManager().loadImageView(context, ver.get(position).getImgsrc(), holder.imageView);
    }

    @Override
    public int getItemCount() {
        return ver != null && ver.size() > 0 ? ver.size() : 0;
    }


    class ItemHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public ItemHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_child);
            imageView = (ImageView) itemView.findViewById(R.id.image_child);
        }
    }
}
