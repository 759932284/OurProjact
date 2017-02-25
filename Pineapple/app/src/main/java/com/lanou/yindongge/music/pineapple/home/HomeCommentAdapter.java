package com.lanou.yindongge.music.pineapple.home;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.HomeGameTalkResponse;

import java.util.List;

/**
 * Created by dllo on 17/2/25.
 */

public class HomeCommentAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<HomeGameTalkResponse> dataHomeGameTalk;
    private HomeCommentDetailAdapter commentDetailAdapter;

    public void setDataHomeGameTalk(List<HomeGameTalkResponse> dataHomeGameTalk) {
        this.dataHomeGameTalk = dataHomeGameTalk;
        notifyDataSetChanged();
    }

    public HomeCommentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                if (dataHomeGameTalk != null) {
                    View commentHeaderView = LayoutInflater.from(context).inflate(R.layout.item_home_header, null);
                    HomeCommentFirstViewHolder holderFirst = (HomeCommentFirstViewHolder)holder;
                    holderFirst.commentFirstTv.setText(dataHomeGameTalk.get(position).getVideoCategory().getName());
                    commentDetailAdapter = new HomeCommentDetailAdapter(context, commentHeaderView);
                    List<HomeGameTalkResponse.VideoListBean> dataDeatail = dataHomeGameTalk.get(position).getVideoList();
                    commentDetailAdapter.setDataDeatail(dataDeatail);
                    GridLayoutManager manager = new GridLayoutManager(context, 2);
                    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            return commentDetailAdapter.isHeader(position) ? 2 : 1;
                        }
                    });
                    holderFirst.commentFirstRv.setLayoutManager(manager);
                    holderFirst.commentFirstRv.setAdapter(commentDetailAdapter);
                }
    }

    @Override
    public int getItemCount() {
        return dataHomeGameTalk != null ? dataHomeGameTalk.size() : 0;
    }

    class HomeCommentFirstViewHolder extends RecyclerView.ViewHolder{
        TextView commentFirstTv;
        RecyclerView commentFirstRv;
        public HomeCommentFirstViewHolder(View itemView) {
            super(itemView);
            commentFirstTv = (TextView)itemView.findViewById(R.id.comment_first_tv);
            commentFirstRv = (RecyclerView)itemView.findViewById(R.id.comment_first_rv);
        }
    }
}


