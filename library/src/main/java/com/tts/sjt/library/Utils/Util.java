package com.tts.sjt.library.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Base64;

import com.tts.sjt.library.bean.FaceDetect83Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Util {

    /**
     * 从assets里面读取图片，转换为base64
     * @param context
     * @param imgFile
     * @return
     */
    public static String getImageStr(Context context, String imgFile) {

        AssetManager am = context.getResources().getAssets();
        InputStream inputStream = null;
        byte[] data = null;
        try
        {
            inputStream = am.open(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return Base64.encodeToString(data,Base64.NO_WRAP);

    }

    /**
     * 返回bitmap
     * @param context
     * @param imgFile
     * @return
     */
    public static Bitmap getImageStream(Context context, String imgFile) {

        AssetManager am = context.getResources().getAssets();
        try
        {
            InputStream inputStream = am.open(imgFile);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return bitmap;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 在bitmap上画矩形
     * @param bitmap
     * @throws Exception
     */
    public static Bitmap drawRectOnBitmap(Bitmap bitmap, List<FaceDetect83Bean.FacesBean.FaceRectangleBean> rects) throws Exception {
        Bitmap tempBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(tempBitmap);
        //图像上画矩形
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        //不填充
        paint.setStyle(Paint.Style.STROKE);
        //线的宽度
        paint.setStrokeWidth(4);
        for (FaceDetect83Bean.FacesBean.FaceRectangleBean rect: rects
                ) {
            canvas.drawRect(rect.getLeft(),
                    rect.getTop(),
                    rect.getLeft() + rect.getWidth(),
                    rect.getTop() + rect.getHeight(),
                    paint);
        }
        return tempBitmap;
    }
}
