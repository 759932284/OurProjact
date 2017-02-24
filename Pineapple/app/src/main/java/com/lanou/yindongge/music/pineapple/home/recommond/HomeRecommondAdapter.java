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

import com.lanou.yindongge.music.pineapple.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/18.
 */

public class HomeRecommondAdapter extends RecyclerView.Adapter {
    private Context context;

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
                HomeRecommondBacteriaAdapter bacteriaAdapter = new HomeRecommondBacteriaAdapter(context);
                List<String> data =  new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    data.add("题目");
                }
                bacteriaAdapter.setData(data);
                holderBacteria.bacteriaRv.setLayoutManager(new GridLayoutManager(context, 2,
                                                          LinearLayoutManager.VERTICAL, false));
                holderBacteria.bacteriaRv.setAdapter(bacteriaAdapter);
                break;
            case 2:
                HomeRecommondPopularViewHolder holderPopular = (HomeRecommondPopularViewHolder)holder;
                holderPopular.popularTv.setText("人气周榜");
                HomeRecommondPopularAdapter popularAdapter = new HomeRecommondPopularAdapter(context);
                List<String> dataPopular = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    dataPopular.add("题目");
                }
                popularAdapter.setDataPopular(dataPopular);
                holderPopular.popularRv.setLayoutManager(new GridLayoutManager(context, 3,
                                                         LinearLayoutManager.HORIZONTAL, false));
                holderPopular.popularRv.setAdapter(popularAdapter);
                break;
            case 3:
                View gameHeaderView = LayoutInflater.from(context).inflate(R.layout.item_home_header, null);
                HomeRecommondGameViewHolder holderGame = (HomeRecommondGameViewHolder)holder;
                holderGame.gameTv.setText("游戏杂谈...");
                final HomeRecommondGameAdapter gameAdapter = new HomeRecommondGameAdapter(context, gameHeaderView);
                List<String> dataGame =  new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    dataGame.add("题目");
                }
                gameAdapter.setDataGame(dataGame);
//                holderGame.gameRv.setLayoutManager(new GridLayoutManager(context, 2,
//                                                         LinearLayoutManager.VERTICAL, false));
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
                HomeRecommondAlbumViewHolder holderAlbum = (HomeRecommondAlbumViewHolder)holder;
                holderAlbum.albumTv.setText("菠萝专辑");
                // 获取网络图片后,用解析工具铺建
                holderAlbum.albumIv.setImageResource(R.mipmap.ic_launcher);
                HomeRecommondAlbumAdapter albumAdapter = new HomeRecommondAlbumAdapter(context);
                List<String> dataAlbum = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    dataAlbum.add("专辑");
                }
                albumAdapter.setDataAlbum(dataAlbum);
                holderAlbum.albumRv.setLayoutManager(new LinearLayoutManager(context,
                                                        LinearLayoutManager.HORIZONTAL, false));
                holderAlbum.albumRv.setAdapter(albumAdapter);
                break;
            case 5:
                HomeRecommondRecommondHolder holderRecommond = (HomeRecommondRecommondHolder)holder;
                holderRecommond.recommondTv.setText("为您推荐");
                HomeRecommondRecommondAdapter recommondAdapter = new HomeRecommondRecommondAdapter(context);
                List<String> dataRecommond = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    dataRecommond.add("推荐");
                }
                recommondAdapter.setDataRecommond(dataRecommond);
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
        public HomeRecommondAlbumViewHolder(View itemView) {
            super(itemView);
            albumTv = (TextView)itemView.findViewById(R.id.recommond_album_tv);
            albumIv = (ImageView)itemView.findViewById(R.id.recommond_album_iv);
            albumRv = (RecyclerView)itemView.findViewById(R.id.recommond_album_rv);
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
