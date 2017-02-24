package com.lanou.yindongge.music.pineapple.home.recommond;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.bean.AlbumResponse;
import com.lanou.yindongge.music.pineapple.bean.BacteriaResponse;
import com.lanou.yindongge.music.pineapple.bean.GameTalkResponse;
import com.lanou.yindongge.music.pineapple.bean.PopularResponse;
import com.lanou.yindongge.music.pineapple.bean.RecommondResponse;
import com.lanou.yindongge.music.pineapple.net.ImageManagerFactory;
import com.lanou.yindongge.music.pineapple.net.OkHttpManager;
import com.lanou.yindongge.music.pineapple.net.OnNetResultListener;
import com.lanou.yindongge.music.pineapple.util.Contant;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class HomeRecommondAdapter extends RecyclerView.Adapter implements OnNetResultListener{
    private Context context;
    private HomeRecommondBacteriaAdapter bacteriaAdapter;
    private HomeRecommondPopularAdapter popularAdapter;
    private HomeRecommondGameAdapter gameAdapter;
    private HomeRecommondAlbumAdapter albumAdapter;
    private HomeRecommondAlbumViewHolder holderAlbum;
    private HomeRecommondRecommondAdapter recommondAdapter;

    public HomeRecommondAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                View viewHead = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_head, null);
                holder = new HomeRecommondHeadViewHolder(viewHead);
                break;
            case 1:
               // Bacteria 菌
                View viewBacteria = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_bacteria, null);
                holder = new HomeRecommondBacteriaViewHolder(viewBacteria);
                break;
            case 2:
                // 人气周榜
                View viewPopular = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_popular, null);
                holder = new HomeRecommondPopularViewHolder(viewPopular);
                break;
            case 3:
                // 游戏杂谈
                View viewGame = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_game, null);
                holder = new HomeRecommondGameViewHolder(viewGame);
                break;
            case 4:
                // 菠萝专辑
                View viewAlbum = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_album, null);
                holder = new HomeRecommondAlbumViewHolder(viewAlbum);
                break;
            case 5:
                // 为您推荐
                View viewRecommond = LayoutInflater.from(context).inflate(R.layout.item_home_recommond_recommond, null);
                holder = new HomeRecommondRecommondHolder(viewRecommond);
                break;


        }
        return holder;
    }

    int[] imgRes = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d,
            R.mipmap.e, R.mipmap.f, R.mipmap.g, R.mipmap.h, R.mipmap.i};
    PagerAdapter mAdapter;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                HomeRecommondHeadViewHolder holderHead = (HomeRecommondHeadViewHolder)holder;

                holderHead.mViewPager.setPageMargin(20);
                holderHead.mViewPager.setOffscreenPageLimit(3);
                holderHead.mViewPager.setAdapter(mAdapter = new PagerAdapter()
                {
                    @Override
                    public Object instantiateItem(ViewGroup container, int position)
                    {
                        position %= imgRes.length;
                        if (position<0) {
                            position = imgRes.length + position;
                        }
                        ImageView view = new ImageView(context);
                        view.setScaleType(ImageView.ScaleType.FIT_XY);
                        view.setImageResource(imgRes[position]);

                        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。

                        ViewParent viewParent = view.getParent();
                        if (viewParent!=null){
                            ViewGroup parent = (ViewGroup)viewParent;
                            parent.removeView(view);
                        }
                        container.addView(view);
                        return view;
                    }

                    @Override
                    public void destroyItem(ViewGroup container, int position, Object object)
                    {
                        container.removeView((View) object);
                    }

                    @Override
                    public int getCount()
                    {
                     //   return imgRes.length;
                        return Integer.MAX_VALUE;
                    }

                    @Override
                    public boolean isViewFromObject(View view, Object o)
                    {
                        return view == o;
                    }
                });
                holderHead.mViewPager.setCurrentItem(10000);
                holderHead.mViewPager.setPageTransformer(true, new ScaleInTransformer());
                break;
            case 1:
                HomeRecommondBacteriaViewHolder holderBacteria = (HomeRecommondBacteriaViewHolder)holder;
                holderBacteria.bacteriaTv.setText("菠萝菌力荐");
                bacteriaAdapter = new HomeRecommondBacteriaAdapter(context);
                OkHttpManager.getInstance().startGetRequest(Contant.BACTERIA, Contant.BACTERIA_REQUESTCODE, this);
                holderBacteria.bacteriaRv.setLayoutManager(new GridLayoutManager(context, 2,
                                                          LinearLayoutManager.VERTICAL, false));
                holderBacteria.bacteriaRv.setAdapter(bacteriaAdapter);
                break;
            case 2:
                HomeRecommondPopularViewHolder holderPopular = (HomeRecommondPopularViewHolder)holder;
                holderPopular.popularTv.setText("人气周榜");
                popularAdapter = new HomeRecommondPopularAdapter(context);
                // 获取人气榜网络数据
                OkHttpManager.getInstance().startGetRequest(Contant.POPULAR, Contant.POPULAR_REQUESTCODE, this);
                holderPopular.popularRv.setLayoutManager(new GridLayoutManager(context, 3,
                                                         LinearLayoutManager.HORIZONTAL, false));
                holderPopular.popularRv.setAdapter(popularAdapter);
                break;
            case 3:
                View gameHeaderView = LayoutInflater.from(context).inflate(R.layout.item_home_header, null);
                HomeRecommondGameViewHolder holderGame = (HomeRecommondGameViewHolder)holder;
                holderGame.gameTv.setText("游戏杂谈...");
                gameAdapter = new HomeRecommondGameAdapter(context, gameHeaderView);
                // 获取人气榜网络数据
                OkHttpManager.getInstance().startGetRequest(Contant.GAME_TALK, Contant.GAME_TALK_REQUESTCODE, this);
                GridLayoutManager gameManager = new GridLayoutManager(context, 2);
                gameManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return gameAdapter.isHeader(position) ? 2 : 1;
                    }
                });
                holderGame.gameRv.setLayoutManager(gameManager);
                holderGame.gameRv.setAdapter(gameAdapter);
                break;
            case 4:
                holderAlbum = (HomeRecommondAlbumViewHolder)holder;
                holderAlbum.albumTv.setText("菠萝专辑");
                // 获取网络图片后,用解析工具铺建
              //  holderAlbum.albumIv.setImageResource(R.mipmap.ic_launcher);
                albumAdapter = new HomeRecommondAlbumAdapter(context);
                OkHttpManager.getInstance().startGetRequest(Contant.ALBUM, Contant.ALBUM_REQUESTCODE, this);
                holderAlbum.albumRv.setLayoutManager(new LinearLayoutManager(context,
                                                        LinearLayoutManager.HORIZONTAL, false));
                holderAlbum.albumRv.setAdapter(albumAdapter);
                break;
            case 5:
                HomeRecommondRecommondHolder holderRecommond = (HomeRecommondRecommondHolder)holder;
                holderRecommond.recommondTv.setText("为您推荐");
                recommondAdapter = new HomeRecommondRecommondAdapter(context);
                OkHttpManager.getInstance().startGetRequest(Contant.RECOMMOND, Contant.RECOMMOND_REQUESTCODE, this);
                holderRecommond.recommondRv.setLayoutManager(new GridLayoutManager(context, 2,
                                                        LinearLayoutManager.VERTICAL, false));
                holderRecommond.recommondRv.setAdapter(recommondAdapter);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public void onSuccessListener(String result, int requestCode) {
        Gson gson = new Gson();
        // 推荐-菠萝菌力荐-解析
        if (Contant.BACTERIA_REQUESTCODE == requestCode) {
            Type type = new TypeToken<List<BacteriaResponse>>(){}.getType();
            List<BacteriaResponse> dataBacteria = gson.fromJson(result, type);
            bacteriaAdapter.setDataBacteria(dataBacteria);
        }
        // 推荐-人气榜-解析
        if (Contant.POPULAR_REQUESTCODE == requestCode) {
            Type type = new TypeToken<List<PopularResponse>>(){}.getType();
            List<PopularResponse> dataPopular = gson.fromJson(result, type);
            popularAdapter.setDataPopular(dataPopular);
        }
        // 推荐-游戏杂谈-解析
        if (Contant.GAME_TALK_REQUESTCODE == requestCode) {
            Type type = new TypeToken<List<GameTalkResponse>>(){}.getType();
            List<GameTalkResponse> dataGameTalkAll = gson.fromJson(result, type);
            List<GameTalkResponse.VideoListBean> dataGameTalk = dataGameTalkAll.get(0).getVideoList();
            gameAdapter.setDataGameTalk(dataGameTalk);
        }
        // 推荐-菠萝专辑-解析
        if (Contant.ALBUM_REQUESTCODE == requestCode) {
            Type type = new TypeToken<List<AlbumResponse>>(){}.getType();
            List<AlbumResponse> dataAlbumAll = gson.fromJson(result, type);
            ImageManagerFactory.getImageManager(ImageManagerFactory.GLIDE).loadImageView(context,
                    dataAlbumAll.get(0).getCover(), holderAlbum.albumIv);
            holderAlbum.albumInnerTv.setText(dataAlbumAll.get(0).getName());
            List<AlbumResponse.VideoListBean> dataAlbum = dataAlbumAll.get(0).getVideoList();
            albumAdapter.setDataAlbum(dataAlbum);
        }
        // 推荐-为您推荐-解析
        if (Contant.RECOMMOND_REQUESTCODE == requestCode) {
            Type type = new TypeToken<List<RecommondResponse>>(){}.getType();
            List<RecommondResponse> dataRecommond = gson.fromJson(result, type);
            recommondAdapter.setDataRecommond(dataRecommond);
        }
    }

    @Override
    public void onFailureListener(String errMsg) {
        Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show();
    }

    class HomeRecommondBacteriaViewHolder extends RecyclerView.ViewHolder {
        RecyclerView bacteriaRv;
        TextView bacteriaTv;
        public HomeRecommondBacteriaViewHolder(View itemView) {
            super(itemView);
            bacteriaRv = (RecyclerView)itemView.findViewById(R.id.bacteria_rv);
            bacteriaTv = (TextView)itemView.findViewById(R.id.bacteria_tv);
        }
    }

    class HomeRecommondHeadViewHolder extends RecyclerView.ViewHolder {
        ViewPager mViewPager;

        public HomeRecommondHeadViewHolder(View itemView) {
            super(itemView);
            mViewPager = (ViewPager) itemView.findViewById(R.id.id_viewpager);
        }
    }
    class HomeRecommondPopularViewHolder extends RecyclerView.ViewHolder {
        RecyclerView popularRv;
        TextView popularTv;
        public HomeRecommondPopularViewHolder(View itemView) {
            super(itemView);
            popularRv = (RecyclerView)itemView.findViewById(R.id.recommond_popular_rv);
            popularTv = (TextView)itemView.findViewById(R.id.recommond_popular_tv);
        }
    }
    class HomeRecommondGameViewHolder extends RecyclerView.ViewHolder {
        RecyclerView gameRv;
        TextView gameTv;
        public HomeRecommondGameViewHolder(View itemView) {
            super(itemView);
            gameRv = (RecyclerView)itemView.findViewById(R.id.recommond_game_rv);
            gameTv = (TextView)itemView.findViewById(R.id.recommond_game_tv);
        }
    }
    class HomeRecommondAlbumViewHolder extends RecyclerView.ViewHolder {
        TextView albumTv;
        ImageView albumIv;
        RecyclerView albumRv;
        TextView albumInnerTv;
        public HomeRecommondAlbumViewHolder(View itemView) {
            super(itemView);
            albumTv = (TextView)itemView.findViewById(R.id.recommond_album_tv);
            albumIv = (ImageView)itemView.findViewById(R.id.recommond_album_iv);
            albumRv = (RecyclerView)itemView.findViewById(R.id.recommond_album_rv);
            albumInnerTv = (TextView)itemView.findViewById(R.id.recommond_album_inner_tv);
        }
    }
    class HomeRecommondRecommondHolder extends RecyclerView.ViewHolder{
        TextView recommondTv;
        RecyclerView recommondRv;
        public HomeRecommondRecommondHolder(View itemView) {
            super(itemView);
            recommondTv = (TextView)itemView.findViewById(R.id.recommond_tv);
            recommondRv = (RecyclerView)itemView.findViewById(R.id.recommond_rv);
        }
    }
}
