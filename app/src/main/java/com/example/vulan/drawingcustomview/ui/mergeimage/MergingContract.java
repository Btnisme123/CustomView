package com.example.vulan.drawingcustomview.ui.mergeimage;

import android.graphics.Bitmap;

import com.example.vulan.drawingcustomview.ui.base.BaseView;

/**
 * Created by vulan on 07/02/2017.
 */

public interface MergingContract {
     interface View extends BaseView{
        void start();
        void initData();
        void saveSuccess();
        void saveError();
    }

    interface Presenter {
        void saveImage(Bitmap bitmap);
    }
}
