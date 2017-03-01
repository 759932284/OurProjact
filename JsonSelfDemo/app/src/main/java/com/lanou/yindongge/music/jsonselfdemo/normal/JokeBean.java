package com.lanou.yindongge.music.jsonselfdemo.normal;

/**
 * Created by dllo on 17/2/28.
 */

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 最外层的实体类
 */
public class JokeBean {
//    @SerializedName("段子")
//    private List<JokeInfoBean> list;
//
//    public List<JokeInfoBean> getList() {
//        return list;
//    }
//
//    public void setList(List<JokeInfoBean> list) {
//        this.list = list;
//    }

    @JSONField(name="段子")
    private List<JokeInfoBean> list;

    public List<JokeInfoBean> getList() {
        return list;
    }

    public void setList(List<JokeInfoBean> list) {
        this.list = list;
    }
}
