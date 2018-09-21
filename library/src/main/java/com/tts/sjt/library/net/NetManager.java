package com.tts.sjt.library.net;

import android.app.Activity;
import android.content.Context;

import com.tts.sjt.library.LibContants;
import com.tts.sjt.library.Utils.LogUtils;
import com.tts.sjt.library.bean.OcrIdCardBean;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.ref.SoftReference;
import java.lang.reflect.Type;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManager {

    Context mContext;

    Retrofit retrofit;

    boolean handleInUi = true;

    public NetManager (SoftReference<Context> context) {
        this.mContext = context.get();
    }

    public NetManager initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(LibContants.baseUrl)
                //使用自定义string解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return this;
    }

    /**
     * 是否在UI线程处理回调
     * @param handleInUi
     * @return
     */
    public NetManager isHandleInUI(boolean handleInUi) {
        this.handleInUi = handleInUi;
        return this;
    }

    /**
     * ocr身份证识别
     * @param params
     * @param callBack
     */
    public void doSendOcrIdCardRequest(HashMap<String, String> params, final NetCallBack<OcrIdCardBean> callBack) {
        initParams(params);
        IDCardRequest idCardRequest = retrofit.create(IDCardRequest.class);
        Call<OcrIdCardBean> call = idCardRequest.postOcrIdCardRequest(params);
        call.enqueue(new Callback<OcrIdCardBean>() {
            @Override
            public void onResponse(Call<OcrIdCardBean> call, final Response<OcrIdCardBean> response) {
                if (!handleInUi) {
                    callBack.onSuccess(response.body());
                    return;
                }
                if (null != mContext && mContext instanceof Activity) {
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(response.body());
                        }
                    });
                }
                else {
                    LogUtils.logd("宿主已被销毁");
                }
            }

            @Override
            public void onFailure(Call<OcrIdCardBean> call, Throwable t) {
                if (!handleInUi) {
                    callBack.onFail(t.getMessage());
                    return;
                }
                if (null != mContext && mContext instanceof Activity) {
                    callBack.onFail(t.getMessage());
                }
                else {
                    LogUtils.logd("宿主已被销毁");
                }
            }
        });
    }

    private void initParams(HashMap<String, String> params) {
        params.put("api_key", LibContants.appId);
        params.put("api_secret", LibContants.appKey);
    }
}
