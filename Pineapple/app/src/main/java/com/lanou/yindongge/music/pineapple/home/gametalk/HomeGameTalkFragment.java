package com.lanou.yindongge.music.pineapple.home.gametalk;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;

/**
 * Created by dllo on 17/2/21.
 */

public class HomeGameTalkFragment extends BaseFragment {

    private RecyclerView gameTalkRv;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_gametalk;
    }

    @Override
    public void initView(View view) {
        gameTalkRv = byView(R.id.game_talk_rv);
    }

    @Override
    public void initData() {
        HomeGameTalkAdapter gameTalkAdapter = new HomeGameTalkAdapter(context);
        gameTalkRv.setAdapter(gameTalkAdapter);
        gameTalkRv.setLayoutManager(new LinearLayoutManager(context));
//        List<String> data = new ArrayList<>();
//        for (int i = 0; i < 9; i++) {
//            data.add("123");
//        }
//        gameTalkAdapter.setData(data);
    }
}
