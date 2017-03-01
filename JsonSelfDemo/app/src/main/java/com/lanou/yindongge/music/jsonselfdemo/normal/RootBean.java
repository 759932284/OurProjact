package com.lanou.yindongge.music.jsonselfdemo.normal;

import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */
// 复杂接口最外层
public class RootBean {
    private String name; // 温柔笃定的想你
    private int photos_count;// 71
    private long updated_at;// 1486816222
    // 需要填入类型:暂时未创建先空着
    private List<Trip_days> trip_days;//Array[3]
    private TipBean tip; // Object
    private UserBean user; // Object
    private Object password;//null

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotos_count() {
        return photos_count;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public List<Trip_days> getTrip_days() {
        return trip_days;
    }

    public void setTrip_days(List<Trip_days> trip_days) {
        this.trip_days = trip_days;
    }

    public TipBean getTip() {
        return tip;
    }

    public void setTip(TipBean tip) {
        this.tip = tip;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }
}
