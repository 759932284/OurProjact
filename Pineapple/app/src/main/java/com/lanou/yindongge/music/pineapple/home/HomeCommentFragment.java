package com.lanou.yindongge.music.pineapple.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;
import com.lanou.yindongge.music.pineapple.bean.HomeGameTalkResponse;
import com.lanou.yindongge.music.pineapple.detail.PlayDetailActivity;
import com.lanou.yindongge.music.pineapple.net.OkHttpManager;
import com.lanou.yindongge.music.pineapple.net.OnNetResultListener;
import com.lanou.yindongge.music.pineapple.util.Contant;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class HomeCommentFragment extends BaseFragment implements OnNetResultListener{

    private String url;
    private String channel;
    private RecyclerView commentRv;
    private HomeCommentAdapter homeCommentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_comment;
    }

    public static HomeCommentFragment newInstance(String url, String channelStr) {

        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("channel", channelStr);
        HomeCommentFragment fragment = new HomeCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(View view) {
        commentRv = (RecyclerView)view.findViewById(R.id.comment_rv);
    }

    @Override
    public void initData() {
        // 区分频道
        Bundle bundle = getArguments();
        if (bundle != null){
            url = bundle.getString("url");
            channel = bundle.getString("channel");
        }
        homeCommentAdapter = new HomeCommentAdapter(context);
        commentRv.setAdapter(homeCommentAdapter);
        commentRv.setLayoutManager(new LinearLayoutManager(context));
        //    "搞笑", "动画", "萌宠", "二次元", "娱乐", "网剧", "英雄联盟", "炉石传说"
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_GAME_TALK_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_LAUGH_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_CATOON_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_ANIMAL_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_FOOD_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_QUADRATIC_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_AMUSE_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_NET_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_HERO_REQUESTCODE, this);
            OkHttpManager.getInstance().startGetRequest(url, Contant.HOME_LEGEND_REQUESTCODE, this);

    }

    @Override
    public void onSuccessListener(String result, int requestCode) {
        Gson gson = new Gson();

        if (requestCode == Contant.HOME_GAME_TALK_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_LAUGH_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_CATOON_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_ANIMAL_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_FOOD_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_QUADRATIC_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_AMUSE_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_NET_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_HERO_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
        if (requestCode == Contant.HOME_LEGEND_REQUESTCODE) {
            Type type = new TypeToken<List<HomeGameTalkResponse>>(){}.getType();
            List<HomeGameTalkResponse> dataHomeGameTalk = gson.fromJson(result, type);
            homeCommentAdapter.setDataHomeGameTalk(dataHomeGameTalk);
        }
    }
    @Override
    public void onFailureListener(String errMsg) {
     //   Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show();
    }
}
