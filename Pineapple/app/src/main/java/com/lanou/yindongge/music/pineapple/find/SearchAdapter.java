package com.lanou.yindongge.music.pineapple.find;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lanou.yindongge.music.pineapple.R;

import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder>{
    private Context context;

    private List<String> search;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setSearch(List<String> search) {
        this.search = search;
        notifyDataSetChanged();
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_search_search, parent, false);
        SearchHolder holder = new SearchHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        holder.searchBtn.setText(search.get(position));
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class SearchHolder extends RecyclerView.ViewHolder {
        Button searchBtn;
        public SearchHolder(View itemView) {
            super(itemView);
            searchBtn = (Button) itemView.findViewById(R.id.search_btn);
        }
    }
}
