package com.example.vulan.drawingcustomview.ui.draganddrop;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.example.vulan.drawingcustomview.util.ImageHandling;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vulan on 03/02/2017.
 */

public class DragingPresenter implements DragingContract.Presenter {
    private DragingContract.View mView;
    private String mImagePath;

    public DragingPresenter(DragingContract.View view, String imagePath) {
        mView = view;
        mImagePath = imagePath;
    }

    @Override
    public void handleSaving(Bitmap bitmap) {

    }

    @Override
    public void getBitmapfromFile() {
        handleBitmapfromFile()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        if (bitmap != null) mView.updateImage();
                    }
                });
    }

    @Override
    public void saveImage() {

    }

    public Observable<Bitmap> handleBitmapfromFile() {
        Point point = mView.getDisplaySize();
        Bitmap bitmap = null;
        try {
            bitmap = ImageHandling.decodeBitmap(mImagePath, point.x, point.y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Observable.just(bitmap);
    }
}
