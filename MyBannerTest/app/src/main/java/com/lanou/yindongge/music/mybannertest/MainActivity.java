package com.lanou.yindongge.music.mybannertest;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;

//    int[] imgRes = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d,
//            R.mipmap.e, R.mipmap.f, R.mipmap.g, R.mipmap.h, R.mipmap.i};

    public static int[] imgRes = new int[] {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d,
                R.mipmap.e, R.mipmap.f, R.mipmap.g, R.mipmap.h, R.mipmap.i};
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
            mViewPager.setPageMargin(50);
            mViewPager.setOffscreenPageLimit(3);


            mViewPager.setAdapter(mAdapter = new PagerAdapter()

            {
                @Override
                public Object instantiateItem(ViewGroup container, int position)
                {
//                    ImageView view = new ImageView(MainActivity.this);
//                    view.setScaleType(ImageView.ScaleType.FIT_XY);
//
//                    view.setImageResource(imgRes[position]);
//                    container.addView(view);
//                view.setAdjustViewBounds(false);


                    //对Viewpager页号求模去除View列表中要显示的项
//                    position %= imgRes.length;
//                    if (position<0) {
//                        position = imgRes.length + position;
//                    }
                    ImageView view = new ImageView(MainActivity.this);
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
                  //  return imgRes.length;
                    return Integer.MAX_VALUE;
                }

                @Override
                public boolean isViewFromObject(View view, Object o)
                {
                    return view == o;
                }
            });
            mViewPager.setCurrentItem(10000);
            mViewPager.setPageTransformer(true, new ScaleInTransformer());
    }
}
