package com.tts.sjt.library;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class FacePP {

    public static void init(String facePPId, String facePPKey) {
         LibContants.appId = facePPId;
         LibContants.appKey = facePPKey;
        //初始化日志系统
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
