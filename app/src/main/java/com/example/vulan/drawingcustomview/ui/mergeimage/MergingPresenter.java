package com.example.vulan.drawingcustomview.ui.mergeimage;

import android.graphics.Bitmap;

import com.example.vulan.drawingcustomview.util.ImageHandling;

/**
 * Created by vulan on 07/02/2017.
 */

public class MergingPresenter implements MergingContract.Presenter {

    private MergingContract.View mView;

    public MergingPresenter(MergingContract.View view) {
        mView = view;
        mView.start();
    }

    @Override
    public void saveImage(Bitmap bitmap) {
        if (ImageHandling.saveImage(bitmap)) {
            mView.saveSuccess();
        }else{
            mView.saveError();
        }
    }
}
