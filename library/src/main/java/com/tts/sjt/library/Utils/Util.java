package com.tts.sjt.library.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Base64;

import java.io.IOException;
import java.io.InputStream;

public class Util {

    /**
     * @return
     * @Description: 根据图片地址转换为base64编码字符串
     * @Author:
     * @CreateTime:
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

        return Base64.encodeToString(data,Base64.DEFAULT);

    }

}
