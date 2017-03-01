package com.lanou.yindongge.music.jsonselfdemo.normal;

import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */
// 第三层实体类
    // nodes
public class NodesBean {
    private int id;
    private long updated_at;
    private List<NotesBean> notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public List<NotesBean> getNotes() {
        return notes;
    }

    public void setNotes(List<NotesBean> notes) {
        this.notes = notes;
    }
}
