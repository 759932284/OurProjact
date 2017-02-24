package com.lanou.ping.music.expanablelistviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/2/23.
 */

public class ExAdapter extends BaseExpandableListAdapter {
    private List<String> groupList;
    private Map<String, List<CarEntity.ResultBean.BrandlistBean.ListBean>> childList;

    public void setData(List<String> groupList, Map<String, List<CarEntity.ResultBean.BrandlistBean.ListBean>> childList) {
        this.groupList = groupList;
        this.childList = childList;
        notifyDataSetChanged();
    }

    // 父级和子级Item的条数
    @Override
    public int getGroupCount() {
        return groupList == null ? 0 : groupList.size();
    }

    @Override
    public int getChildrenCount(int i) { // i: groupPosition
        // 获得到A, B, C, D
//        String abcd = groupList.get(i);
        // 根据A, B, C, D去map中获取对应的List
//        return childList.get(abcd).size();
        return childList.get(groupList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        String abcd = groupList.get(i);
        // 根据abcd获取List
        return childList.get(abcd).get(i);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupHolder = null;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group, null);
            groupHolder = new GroupViewHolder(view);
            view.setTag(groupHolder);
        } else {
            groupHolder = (GroupViewHolder) view.getTag();
        }
        groupHolder.letterTv.setText(groupList.get(i));

        //判断isExpanded就可以控制是按下还是关闭，同时更换图片
//        if(isExpanded){
//            parentImageViw.setBackgroundResource(R.drawable.arrow_down);
//        }else{
//            parentImageViw.setBackgroundResource(R.drawable.arrow_up); }


        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childHolder = null;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_child, null);
            childHolder = new ChildViewHolder(view);
            view.setTag(childHolder);
        } else {
            childHolder = (ChildViewHolder) view.getTag();
        }
        String a_z = groupList.get(groupPosition);
        CarEntity.ResultBean.BrandlistBean.ListBean bean = childList.get(a_z).get(childPosition);
        childHolder.nameTv.setText(bean.getName());
        Picasso.with(viewGroup.getContext())
                .load(bean.getImgurl())
                // 重设大小
                .resize(100, 100)
                // 旋转
                .rotate(360, 0.5f, 0.5f)
                // 占位
                .placeholder(R.mipmap.ic_launcher)
                .into(childHolder.iconTv);
        return view;
    }

    /**ExpandableListView 如果子条目需要响应click事件,必需返回true*/
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class GroupViewHolder {
        TextView letterTv;

        public GroupViewHolder(View groupView) {
            letterTv = (TextView) groupView.findViewById(R.id.item_group_tv);
        }
    }

    class ChildViewHolder {
        TextView nameTv;
        ImageView iconTv;

        public ChildViewHolder(View childView) {
            nameTv = (TextView) childView.findViewById(R.id.item_child_tv);
            iconTv = (ImageView) childView.findViewById(R.id.item_child_iv);
        }
    }
}
