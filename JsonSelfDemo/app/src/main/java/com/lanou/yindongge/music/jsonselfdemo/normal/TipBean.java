package com.lanou.yindongge.music.jsonselfdemo.normal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */

public class TipBean implements Parcelable{
    private int id;
    // 类型:待定
    // 第二层处理完毕再处理第三层
    // 里面是String, 直接填入
    private List<String> texts;

    private TipBean() {

    }

    protected TipBean(Parcel in) {
        id = in.readInt();
        texts = in.createStringArrayList();
    }

    public static final Creator<TipBean> CREATOR = new Creator<TipBean>() {
        @Override
        public TipBean createFromParcel(Parcel in) {
            return new TipBean(in);
        }

        @Override
        public TipBean[] newArray(int size) {
            return new TipBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeStringList(texts);
    }
}
