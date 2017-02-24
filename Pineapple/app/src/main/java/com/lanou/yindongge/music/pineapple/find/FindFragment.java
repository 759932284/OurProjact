package com.lanou.yindongge.music.pineapple.find;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;

/**
 * Created by dllo on 17/2/18.
 */

public class FindFragment extends BaseFragment {

    private RecyclerView rv;
    private FindAdapter findAdapter;

    // 下拉刷新
    private SwipeRefreshLayout refresh;
    private static final int REFRESH = 1;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initView(View view) {
        rv = (RecyclerView) view.findViewById(R.id.find_rv);

        refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
    }

    @Override
    public void initData() {
        findAdapter = new FindAdapter(context);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(findAdapter);

        // 下拉刷新
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(REFRESH, 2000);
            }
        });
        refresh.setColorSchemeColors(Color.RED, Color.GREEN);
    }

    /**
     *  下拉刷新
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == REFRESH) {
                // 重新请求数据

                findAdapter.notifyDataSetChanged();
                refresh.setRefreshing(false);
            }
        }
    };

}
