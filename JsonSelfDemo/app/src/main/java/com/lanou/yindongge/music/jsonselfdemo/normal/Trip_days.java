package com.lanou.yindongge.music.jsonselfdemo.normal;

import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */
// 第二层 trip_days
    // CRUD操作
public class Trip_days {
    private String trip_date;// "2015-04-24"
    private int id;//837677
    private int day;
    private int updated_at; // 1434571646
    // 类型:暂定,未创建类
    // 因为该类属于第三层
    // 第二层处理完毕再一次创建
    private List<NodesBean> nodes;

    public String getTrip_date() {
        return trip_date;
    }

    public void setTrip_date(String trip_date) {
        this.trip_date = trip_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public List<NodesBean> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodesBean> nodes) {
        this.nodes = nodes;
    }
}
