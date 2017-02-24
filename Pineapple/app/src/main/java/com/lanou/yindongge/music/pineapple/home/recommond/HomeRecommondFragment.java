package com.lanou.yindongge.music.pineapple.home.recommond;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;

/**
 * Created by dllo on 17/2/18.
 */

public class HomeRecommondFragment extends BaseFragment {

    private RecyclerView recommondRec;
    private HomeRecommondAdapter homeRecommondAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_recommond;
    }

    @Override
    public void initView(View view) {

        recommondRec = byView(R.id.recommond_rec);
    }

    @Override
    public void initData() {
        homeRecommondAdapter = new HomeRecommondAdapter(context);
        recommondRec.setAdapter(homeRecommondAdapter);
        recommondRec.setLayoutManager(new LinearLayoutManager(context));

    }
}
