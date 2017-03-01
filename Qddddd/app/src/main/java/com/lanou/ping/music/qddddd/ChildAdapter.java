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

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.CHolder> {
    private Context context;

    private List<DataBean.T1348648517839Bean> hor;


    public void setHor(List<DataBean.T1348648517839Bean> hor) {
        this.hor = hor;
        notifyDataSetChanged();
    }

    private OnChildClickListener onChildClickListener;

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.onChildClickListener = onChildClickListener;
    }

    public ChildAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_one, null);
        final CHolder holder = new CHolder(itemView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getLayoutPosition();
                onChildClickListener.onItemClick(pos);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(CHolder holder, int position) {
        holder.textView.setText(hor.get(position).getTitle());
        GlideManager.getGlideManager().loadImageView(context, hor.get(position).getImgsrc(), holder.imageView);
    }

    @Override
    public int getItemCount() {
        return hor != null && hor.size() > 0 ? hor.size() : 0;
    }


    class CHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public CHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_iv);
            textView = (TextView) itemView.findViewById(R.id.text_tv);
        }
    }

    interface OnChildClickListener{
        void onItemClick(int position);
    }
}
