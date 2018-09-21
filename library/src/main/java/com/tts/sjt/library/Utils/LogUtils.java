package com.tts.sjt.library.Utils;

import com.orhanobut.logger.Logger;

public class LogUtils {

    public static void logd (String content) {
        Logger.d(content);
    }

    public static void loge (String content) {
        Logger.e(content);
    }

    public static void logjson(String jsonContent) {
        Logger.json(jsonContent);
    }
}
