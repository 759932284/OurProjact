package com.lanou.yindongge.music.pineapple.hot;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.StackView;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;

/**
 * Created by dllo on 17/2/18.
 */

public class HotFragment extends BaseFragment {

    private StackView stackView;
    private Button previousButon, nextButton;
    private int[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.RED};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void initView(View view) {
        stackView = (StackView) view.findViewById(R.id.hot_sv);
        previousButon = (Button) view.findViewById(R.id.previousButton);
        nextButton = (Button) view.findViewById(R.id.nextButton);
    }

    @Override
    public void initData() {
        HotAdapter hotAdapter = new HotAdapter(context, colors);
        stackView.advance();
        stackView.setAdapter(hotAdapter);

        previousButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stackView.showPrevious();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stackView.showNext();
            }
        });
    }
}
