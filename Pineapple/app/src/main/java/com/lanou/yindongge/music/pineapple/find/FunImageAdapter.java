package com.lanou.yindongge.music.pineapple.find;

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
 * Created by dllo on 17/2/23.
 */

public class FunImageAdapter extends RecyclerView.Adapter<FunImageAdapter.FunHolder> {
    private Context context;

    private List<String> data;

    public FunImageAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public FunHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_gossip_image, parent,false);
        FunHolder holder = new FunHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(FunHolder holder, int position) {
        holder.textView.setText(data.get(position));
        holder.imageView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class FunHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public FunHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.gossip_image_text);
            imageView = (ImageView) itemView.findViewById(R.id.gossip_image_image);
        }
    }
}
