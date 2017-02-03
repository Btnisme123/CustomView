package com.example.vulan.drawingcustomview.ui.draganddrop;

import android.graphics.Bitmap;

/**
 * Created by vulan on 03/02/2017.
 */

public class DragingPresenter implements DragingContract.Presenter {
    private DragingContract.View mView;
    private String mImagePath;

    public DragingPresenter(DragingContract.View view, String imagePath) {
        mView = view;
        mImagePath=imagePath;
    }

    @Override
    public void handleSaving(Bitmap bitmap) {

    }

    @Override
    public void getBitmapfromFile() {

    }

    @Override
    public void saveImage() {

    }
}
