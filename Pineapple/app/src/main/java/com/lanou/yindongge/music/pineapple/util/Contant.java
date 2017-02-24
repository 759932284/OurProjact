package com.lanou.yindongge.music.pineapple.util;

/**
 * Created by dllo on 17/2/24.
 */

public class Contant {
    public static final String BASE_URL = "http://m.live.netease.com/bolo/api/";
    // 首页-推荐-菠萝菌力荐
    public static final String BACTERIA = BASE_URL + "index/videoRecommend.htm?pageNum=1";
    // 首页-推荐-人气榜
    public static final String POPULAR = BASE_URL + "index/dailyPopList.htm?pageNum=1&pageSize=9";
    // 首页-推荐-游戏杂谈
    public static final String GAME_TALK = BASE_URL + "index/showZoneList.htm?len=5";
    // 首页-推荐-菠萝专辑
    public static final String ALBUM = BASE_URL + "index/hotVideoAlbum.htm";
    // 首页-推荐-为您推荐
    public static final String RECOMMOND = BASE_URL + "index/personalizedRecommend.htm?pageNum=1&userId=5702015542626208498&pageSize=20";


    // 请求码
    private static int index = 0;
    // 首页-推荐-菠萝菌力荐 请求码
    public static final int BACTERIA_REQUESTCODE = index++;
    // 首页-推荐-人气榜
    public static final int POPULAR_REQUESTCODE = index++;
    // 首页-推荐-游戏杂谈
    public static final int GAME_TALK_REQUESTCODE = index++;
    // 首页-推荐-菠萝专辑
    public static final int ALBUM_REQUESTCODE = index++;
    // 首页-推荐-为您推荐
    public static final int RECOMMOND_REQUESTCODE = index++;
}
