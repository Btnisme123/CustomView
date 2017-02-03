package com.example.vulan.drawingcustomview.ui.draganddrop;

/**
 * Created by vulan on 03/02/2017.
 */

import android.graphics.Bitmap;

import com.example.vulan.drawingcustomview.ui.base.BasePresenter;
import com.example.vulan.drawingcustomview.ui.base.BaseView;

public interface DragingContract {
    interface View extends BaseView {
        void saveOnSuccess();
        void showError();
        void updateImage();
    }

    interface Presenter extends BasePresenter {
        void handleSaving(Bitmap bitmap);
        void getBitmapfromFile();
    }
}
