package com.tts.sjt.facepp;

import android.app.Application;

import com.tts.sjt.library.FacePP;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacePP.init("2kIxaKKBIosr2hX6t0-gkNPmYTsko-S5", "8KDZnPQSB8Zd8kEsS7GqS8NBR6uzygL9");
    }
}
