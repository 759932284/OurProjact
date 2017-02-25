package com.lanou.yindongge.music.pineapple.hot;

import android.content.pm.FeatureInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.StackView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Created by dllo on 17/2/18.
 */

public class HotFragment extends BaseFragment {

//    private StackView stackView;
//    private Button previousButon, nextButton;
//    private int[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.RED};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void initView(View view) {
//        stackView = (StackView) view.findViewById(R.id.hot_sv);
//        previousButon = (Button) view.findViewById(R.id.previousButton);
//        nextButton = (Button) view.findViewById(R.id.nextButton);
    }


//SavedState savedInstanceState =
//        getFragmentManager().saveFragmentInstanceState(new HotFragment());
    @Override
    public void initData() {
//        HotAdapter hotAdapter = new HotAdapter(context, colors);
//        stackView.advance();
//        stackView.setAdapter(hotAdapter);
//
//        previousButon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                stackView.showPrevious();
//            }
//        });
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                stackView.showNext();
//            }
//        });

/**
 *  设置当前的Activity 无Title并且全屏.
 *  调用这个方法有个限制: 即必须在setContentView(R.layout.main);之前调用,否则会抛出异常
 */
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        initImageLoader();

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new CardFragment())
//                    .commitAllowingStateLoss();

            getFragmentManager().beginTransaction()
                    .add(R.id.container, new CardFragment())
                    .commitAllowingStateLoss();

//        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {

        }
    }

    @SuppressWarnings("deprecation")
    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .memoryCacheExtraOptions(480, 800)
                // default = device screen dimensions
                .threadPoolSize(3)
                // default
                .threadPriority(Thread.NORM_PRIORITY - 1)
                // default
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13) // default
                .discCacheSize(50 * 1024 * 1024) // 缓冲大小
                .discCacheFileCount(100) // 缓冲文件数目
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs().build();

        // 2.单例ImageLoader类的初始化
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
    }
}
