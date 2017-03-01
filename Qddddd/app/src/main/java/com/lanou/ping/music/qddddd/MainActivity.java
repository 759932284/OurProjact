package com.lanou.ping.music.qddddd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.lanou.ping.music.qddddd.net.OkHttpManager;
import com.lanou.ping.music.qddddd.net.OnNetResultListener;
import com.lanou.ping.music.qddddd.net.Url;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
    }

    private void initData() {
        OkHttpManager.getInstance().startGetRequest(Url.Url, Url.URL_REQUESTCODE, new OnNetResultListener() {
            @Override
            public void onSuccessListener(String result, int requestCode) {
                Gson gson = new Gson();
                DataBean db = gson.fromJson(result, DataBean.class);

                List<DataBean.T1348648517839Bean> datas;
                datas = db.getT1348648517839();

                List<DataBean.T1348648517839Bean> hor = new ArrayList<DataBean.T1348648517839Bean>();
                for (int i = 0; i < 10; i++) {
                    hor.add(datas.get(i));
                }
                adapter.setHor(hor);


                List<DataBean.T1348648517839Bean> ver = new ArrayList<DataBean.T1348648517839Bean>();
                for (int i = 10; i < 20; i++) {
                    ver.add(datas.get(i));
                }
                adapter.setVer(ver);
            }

            @Override
            public void onFailureListener(String errMsg) {

            }
        });
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(this);
        rv.setAdapter(adapter);
    }



}
