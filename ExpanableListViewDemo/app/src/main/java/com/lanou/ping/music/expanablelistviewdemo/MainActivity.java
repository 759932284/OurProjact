package com.lanou.ping.music.expanablelistviewdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    /**
     * ExpandableListView: 二级列表
     * expand: 展开
     * expandable: 有展开能力的
     * 专有名词: group(父级), child(子级)
     *
     * @param savedInstanceState
     */

    /**
     * 在Android中
     * 有两个地方可以存储大一些的资源文件 1MB
     * assets : 与src和res同级
     * raw : 在res目录下
     *
     * 区别:
     * 1. 存在res/raw中的文件会在R文件里映射出一个ID, 通过ID去使用
     *    存在assets中的文件, 通过open方法传入文件名去使用
     * 2. 在打包时, raw下的文件会编译成二进制码
     *             assets中的文件会存在apk中, 不会被编译成二进制
     * 3. 虽然两个文件夹都能放置不大于1MB的文件,
     *    但assets存在apk中, 因此不要过大
     *    但raw会编译为二进制, 因此3, 4, 5MB的音视频文件都可以存
     * 4. assets可以创建子目录
     *    raw不能, 因为res目录结构是Android规定好的, 名字和附属关系是定死的
     */

    private ExpandableListView eListView;

    // 父级列表 A, B, C, D
    private List<String> groupList;
    // 子级列表 ListBean存储的是车的名字和图标
    private Map<String, List<CarEntity.ResultBean.BrandlistBean.ListBean>> childList;

    private ExAdapter exAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eListView = (ExpandableListView) findViewById(R.id.expand_list_view);
        // 获取数据
        caeateData();

        exAdapter = new ExAdapter();
        exAdapter.setData(groupList, childList);
        eListView.setAdapter(exAdapter);

        eListView.setGroupIndicator(null);
        // 实现ExpandableListView进入以后默认展开
        for (int i = 0; i < exAdapter.getGroupCount(); i++) {
            eListView.expandGroup(i);
        }

        eListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                intent.setData(Uri.parse(exAdapter.)
                startActivity(intent);
                return true;
            }
        });
    }

    private void jsonData(String string) {
        CarEntity carEntity = new Gson().fromJson(string, CarEntity.class);
        // 解析完成, 拆分数据: 做成父 子二级的嵌套数据
        groupList = new ArrayList<>();
        childList = new HashMap<>();
        // 获取分组List
        List<CarEntity.ResultBean.BrandlistBean> brandList =
                carEntity.getResult().getBrandlist();
        for (int i = 0; i < brandList.size(); i++) {
            groupList.add(brandList.get(i).getLetter());
            childList.put(brandList.get(i).getLetter(), brandList.get(i).getList());
        }
    }

    private void caeateData() {
        // 1. 从assets中获取文件
        try {
            InputStream is = getAssets().open("car.text");
            // 读流
            InputStreamReader isr = new InputStreamReader(is);
            // BufferedReader可逐行读取的字符流
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }

            br.close();
            isr.close();
            is.close();
            Log.d("MainActivity", "--->" + result);

            jsonData(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
