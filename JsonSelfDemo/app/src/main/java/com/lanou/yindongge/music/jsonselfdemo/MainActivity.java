package com.lanou.yindongge.music.jsonselfdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lanou.yindongge.music.jsonselfdemo.normal.JokeBean;
import com.lanou.yindongge.music.jsonselfdemo.normal.JokeInfoBean;
import com.lanou.yindongge.music.jsonselfdemo.normal.NodesBean;
import com.lanou.yindongge.music.jsonselfdemo.normal.NotesBean;
import com.lanou.yindongge.music.jsonselfdemo.normal.RootBean;
import com.lanou.yindongge.music.jsonselfdemo.normal.Trip_days;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // 段子接口
    private String jokeUrl = "http://c.m.163.com/recommend/getChanListNews?channel=T1419316284722&size=20";
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
//        简单一层的实体类();

//        复杂实体类自动生成创建();

        // 测试自己创建实体类
        测试自己创建的复杂实体类();
    }
    String 复杂网址 = "http://chanyouji.com/api/trips/295105.json";
    List<NotesBean> myUseList = new ArrayList<>();
    private void 测试自己创建的复杂实体类() {
        StringRequest sr = new StringRequest(复杂网址, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 手动创建的实体类,解析使用最外层Bean
                RootBean root = JSON.parseObject(response, RootBean.class);
                // 第二层
                List<Trip_days> trip_dayses = root.getTrip_days();
                // 由于是list, 获取数据需要循环
                for (int i = 0; i < trip_dayses.size(); i++) {
                    // 获取显示数据所在的第三层级
              //      Trip_days trip_days = trip_dayses.get(i);
                    List<NodesBean> nodesBeen = trip_dayses.get(i).getNodes();
                    for (int j = 0; j < nodesBeen.size(); j++) {
                    //    NodesBean nodesBean = nodesBeen.get(j);
                        List<NotesBean> notesBeen = nodesBeen.get(j).getNotes();

                            for (int k = 0; k < notesBeen.size(); k++) {
                                NotesBean bean = notesBeen.get(k);
                                myUseList.add(bean);
                            }
                    }
                }
                for (int i = 0; i < myUseList.size(); i++) {
                    Log.d("MainActivity", myUseList.get(i).getDescription() + "--");
                    Log.d("MainActivity", "myUseList.size():" + myUseList.size());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(sr);
    }


    private void 复杂实体类手动创建() {
        StringRequest sr = new StringRequest(复杂网址, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//            //    自动生成的类 我的类 = new Gson().fromJson(response, 自动生成的类.class);
//                自动生成的类 我的类 = JSON.parseObject(response, 自动生成的类.class);
//                List<自动生成的类.TripDaysBean> tripDaysBeen = 我的类.getTrip_days();
//                for (int i = 0; i < tripDaysBeen.size(); i++) {
//
//                    List<自动生成的类.TripDaysBean.NodesBean> nodes = tripDaysBeen.get(i).getNodes();
//                    for (int j = 0; j < nodes.size(); j++) {
//                        List<自动生成的类.TripDaysBean.NodesBean.NotesBean> notes = nodes.get(j).getNotes();
//                        for (int k = 0; k < notes.size(); k++) {
//                            Log.d("xxx", notes.get(k).getDescription() + "1");
//                        }
//                    }
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(sr);
    }

    private void 简单一层的实体类() {
        // 解析实体类
        StringRequest sr = new StringRequest(jokeUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("MainActivity", response);
            //    JokeBean jokeBean = new Gson().fromJson(response, JokeBean.class);
                // 另一种解析框架:FastJson ---> 还有一种国外爱用的JackSon
                // 是阿里巴巴退出的解析框架:大数据解析速度快
                // 加依赖选择 alibaba
                JokeBean jokeBean = JSON.parseObject(response, JokeBean.class);

                List<JokeInfoBean> list = jokeBean.getList();

                for (int i = 0; i < list.size(); i++) {
                    Log.d("MainActivity", list.get(i).getTitle());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(sr);
    }
}
