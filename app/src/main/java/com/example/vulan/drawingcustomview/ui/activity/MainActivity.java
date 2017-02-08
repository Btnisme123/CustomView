package com.example.vulan.drawingcustomview.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.example.vulan.drawingcustomview.R;
import com.example.vulan.drawingcustomview.ui.draganddrop.DragingPresenter;
import com.example.vulan.drawingcustomview.ui.mergeimage.MergingContract;
import com.example.vulan.drawingcustomview.ui.mergeimage.MergingPresenter;
import com.example.vulan.drawingcustomview.util.CustomView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MergingContract.View, CustomView.EventImageView {

    @BindView(R.id.main_layout)
    RelativeLayout mRelativeLayout;

    private CustomView mCustomView;
    private MergingPresenter mMergingPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mCustomView=new CustomView(this);
        mMergingPresenter=new MergingPresenter(this);

    }

    @Override
    public void start() {
        mCustomView.setEventImageView(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void saveSuccess() {

    }

    @Override
    public void saveError() {

    }

    @Override
    public void clickPickImage() {
      /////
    }
}
