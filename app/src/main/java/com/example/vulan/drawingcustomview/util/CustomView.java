package com.example.vulan.drawingcustomview.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by vulan on 06/02/2017.
 */

public class CustomView extends ImageView {

    private Paint mPaint;
    private Context mContext;
    private static final int sWidth = 500, sHeight = 500;
    private Rect mRectFirstImage = new Rect();
    private Rect mRectSecondImage = new Rect();
    private Rect mRectThreeImage = new Rect();
    private Bitmap mBitmapOne;
    private Bitmap mBitmapTwo;
    private Bitmap mBitmapThree;
    private int mNumberLayout;
    private EventImageView mEventImageView;

    public void setNumberLayout(int numberLayout) {
        mNumberLayout = numberLayout;
    }

    public void setEventImageView(EventImageView eventImageView) {
        mEventImageView = eventImageView;
    }

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    public void drawFrame(Canvas canvas) {
        canvas.drawLine(sWidth / 2, 0, sWidth / 2, sHeight, mPaint);
        canvas.drawLine(0, sHeight / 3, sWidth / 2, sHeight / 3, mPaint);
        canvas.drawRect(0, 0, sWidth, sHeight, mPaint);
        mRectFirstImage.set(0, 0, sWidth / 2, sHeight / 3);
        mRectSecondImage.set(0, sHeight / 3, sWidth / 2, sHeight);
        mRectThreeImage.set(sWidth / 2, 0, sWidth, sHeight);
        canvas.drawBitmap(mBitmapOne, null, mRectFirstImage, mPaint);
        canvas.drawBitmap(mBitmapTwo, null, mRectSecondImage, mPaint);
        canvas.drawBitmap(mBitmapThree, null, mRectThreeImage, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mEventImageView != null) mEventImageView.clickPickImage();
                break;
        }
        return super.onTouchEvent(event);
    }
    private void setImageItem(float x, float y) {
        switch (mNumberLayout) {
            case Constant.LAYOUT_OLD:
                if (x <= sWidth / 2 && y <= sHeight / 3) mType = Constant.LAYOUT_ONE;
                if (x <= sWidth / 2 && y >= sHeight / 3) mType = Constant.LAYOUT_TWO;
                if (x >= sWidth / 2 && y <= sHeight) mType = Constant.LAYOUT_THREE;
                break;
            case Constant.LAYOUT_ONE:
                if (x <= sWidth / 2 && y <= sHeight) mType = Constant.LAYOUT_ONE;
                else mType = Constant.LAYOUT_TWO;
                break;
            case Constant.LAYOUT_TWO:
                if (x <= sWidth && y <= sHeight / 2) mType = Constant.LAYOUT_ONE;
                else mType = Constant.LAYOUT_TWO;
                break;
            case Constant.LAYOUT_THREE:
                if (x <= sWidth / 2 && y <= sHeight / 2) mType = Constant.LAYOUT_ONE;
                if (x >= sWidth / 2 && y <= sHeight / 2) mType = Constant.LAYOUT_TWO;
                if (x <= sWidth / 2 && y >= sHeight / 2) mType = Constant.LAYOUT_THREE;
                if (x > sWidth / 2 && y > sHeight / 2) mType = Constant.LAYOUT_FOUR;
                break;
            case Constant.LAYOUT_FOUR:
                if (x <= sWidth && y < sHeight / 3) mType = Constant.LAYOUT_ONE;
                if (x <= sWidth && y > sHeight / 3) mType = Constant.LAYOUT_TWO;
                break;
            default:
                break;
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(sWidth, sHeight);
    }

    public interface EventImageView {
        void clickPickImage();
    }
}
