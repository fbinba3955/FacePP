package com.tts.sjt.facepp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.tts.sjt.library.Utils.LogUtils;
import com.tts.sjt.library.Utils.Util;
import com.tts.sjt.library.bean.OcrIdCardBean;
import com.tts.sjt.library.net.NetCallBack;
import com.tts.sjt.library.net.NetManager;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class OCRIdCardActivity extends Activity {

    Button handleBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr_id_card);
        handleBtn = (Button) findViewById(R.id.btn_handle);
        handleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetManager netManager = new NetManager(new SoftReference<Context>(OCRIdCardActivity.this));
                netManager.initRetrofit().isHandleInUI(true);
                String imageBase64 = Util.getImageStr(OCRIdCardActivity.this, "idcard_sample_down.png");
                imageBase64 = imageBase64.replace("\n", "");
                imageBase64 = imageBase64.replace("\u003d","=");
                HashMap<String, String> params = new HashMap<>();
                params.put("image_base64", imageBase64);
                netManager.doSendOcrIdCardRequest(params, new NetCallBack<OcrIdCardBean>() {
                    @Override
                    public void onSuccess(OcrIdCardBean response) {
                        LogUtils.logjson(new Gson().toJson(response));
                    }

                    @Override
                    public void onFail(String failMsg) {
                        LogUtils.logd(failMsg);
                    }
                });
            }
        });
    }
}
