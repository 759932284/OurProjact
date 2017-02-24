package com.lanou.yindongge.music.pineapple.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;

import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class HomeCommentFragment extends BaseFragment {

    private TextView contentTv;
    private String url;
    private String channel;

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
        contentTv = byView(R.id.content_tv);
    }

    @Override
    public void initData() {
        // 区分频道
        Bundle bundle = getArguments();
        if (bundle != null){
            url = bundle.getString("url");
            channel = bundle.getString("channel");
            contentTv.setText(channel);
//            if (channel.equals("游戏杂谈")){
//
//            } else if (channel.equals("搞笑")){
//
//            } else if (channel.equals("动画")){
//
//            }
        }
//      写适配器时,使用的Bean"

//        发出网络请求
//        网络请求成功
//         if (channel.equals("游戏杂谈")){
//                  使用游戏杂谈的实体类解析
//                    GameBean gb = new GameBean();
//                    AdapterBean ab = new AdapterBean();
//        ab.title = gb.sss;
//        ab.imgUrl = gb.picture;
//            } else if (channel.equals("搞笑")){
//                  使用搞笑的实体类解析
//                    LaughBean lb = new LaughBean();
//                    new AdapterBean().title = lb.xxx;

//            } else if (channel.equals("动画")){
//
//            }
    }

    class GameBean {
        String sss;
        String picture;
    }

    class LaughBean{
        List<String> imgUrl;
        String xxx;
    }

    class AdapterBean {
        String title;
        String subTitle;
        String imgUrl;

    }
}
