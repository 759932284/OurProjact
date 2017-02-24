package com.lanou.yindongge.music.pineapple.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;
import com.lanou.yindongge.music.pineapple.home.recommond.HomeRecommondFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class HomeFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeViewPagerAdapter mainAdapter;
    private String[] title = {"推荐", "游戏杂谈", "搞笑", "动画", "萌宠", "二次元", "娱乐", "网剧", "英雄联盟", "炉石传说", "守望先锋" };

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        tabLayout = byView(R.id.tabLayout);
        viewPager = byView(R.id.viewPager);

    }
//    "搞笑", "动画", "萌宠", "二次元", "娱乐", "网剧", "英雄联盟", "炉石传说", "守望先锋"
    @Override
    public void initData() {
        mainAdapter = new HomeViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mainAdapter);
        mainAdapter.setTitle(title);
        tabLayout.setupWithViewPager(viewPager);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeRecommondFragment());
        fragments.add(HomeCommentFragment.newInstance("", "游戏杂谈"));
        fragments.add(HomeCommentFragment.newInstance("", "搞笑"));
        fragments.add(HomeCommentFragment.newInstance("", "动画"));
        fragments.add(HomeCommentFragment.newInstance("", "萌宠"));
        fragments.add(HomeCommentFragment.newInstance("", "二次元"));
        fragments.add(HomeCommentFragment.newInstance("", "娱乐"));
        fragments.add(HomeCommentFragment.newInstance("", "网剧"));

        mainAdapter.setFragments(fragments);
    }
}
