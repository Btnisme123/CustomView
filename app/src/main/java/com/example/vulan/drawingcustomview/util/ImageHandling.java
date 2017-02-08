package com.example.vulan.drawingcustomview.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by vulan on 06/02/2017.
 */

public class ImageHandling {

    private static final int ANGLE_0 = 0;
    private static final int ANGLE_90 = 90;
    private static final int ANGLE_180 = 180;
    private static final int ANGLE_270 = 270;

    public static Bitmap decodeBitmap(String path, int width, int height) throws IOException {
        int angle = 0;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false;
        ExifInterface exifInterface = new ExifInterface(path);
        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                angle = ANGLE_90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                angle = ANGLE_180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                angle = ANGLE_270;
                break;
        }
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        if (angle == ANGLE_0 || angle == ANGLE_180) {
            outWidth = options.outHeight;
            outHeight = options.outWidth;
        }
        options.inSampleSize = optionSize(outWidth, outHeight, width, height);
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        bitmap = rotateImage(bitmap, angle);
        return bitmap;
    }

    private static Bitmap rotateImage(Bitmap bitmap, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private static int optionSize(int width, int height, int reqWidth, int reqHeight) {
        int inSampleSize = 1;
        if (reqHeight < height || reqWidth < width) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while ((halfWidth / inSampleSize) > reqWidth &&
                    (halfHeight / inSampleSize) > reqHeight) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private static Bitmap getBitmapAfterMerging(View view) {
        Bitmap resultBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(resultBitmap);
        Drawable drawable = view.getBackground();
        if (drawable != null) {
            drawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return resultBitmap;
    }

    //save image
    public static boolean saveImage(Bitmap bitmap) {
        String nameFile = getTime() + Constants.TypeImage.JPG;
        File file = new File(getFilePath(), nameFile);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        DateFormat date = new SimpleDateFormat("HHmmss", Locale.getDefault());
        return date.format(currentDate);
    }

    private static File getFilePath() {
        String location = Environment.getExternalStorageDirectory().getAbsolutePath();
        File dir = new File(location + Constants.Folder.FOLDER_NAME);
        if (!dir.exists()) dir.mkdirs();
        return dir;
    }


}
