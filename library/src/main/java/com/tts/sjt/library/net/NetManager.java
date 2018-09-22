package com.tts.sjt.library.net;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.tts.sjt.library.LibContants;
import com.tts.sjt.library.Utils.LogUtils;
import com.tts.sjt.library.bean.FaceCompareBean;
import com.tts.sjt.library.bean.FaceDetect83Bean;
import com.tts.sjt.library.bean.OcrIdCardBean;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
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
                //使用json解析器
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
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

    public static final int IMAGE_TYPE_BASE64 = 1;
    public static final int IMAGE_TYPE_FILE = 2;
    public static final int IMAGE_TYPE_URL = 3;
    /**
     * ocr身份证识别
     * @param imageContent
     * @param imageType 除了base64 其他类型图片没有尝试，方法是否可用并不清楚
     * @param callBack
     */
    public void doSendOcrIdCardRequest(String imageContent, int imageType, final NetCallBack<OcrIdCardBean> callBack) {

        HashMap<String, String> params = new HashMap<>();
        initParams(params);
        File file = null;
        initImageType(imageContent, imageType, params, file);
        IDCardRequest idCardRequest = retrofit.create(IDCardRequest.class);
        Call<OcrIdCardBean> call = null;
        if (null != file) {
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("image_file", file.getName(), imageBody);
            call = idCardRequest.postOcrIdCardRequestByFile(params, imageBodyPart);
        }
        else {
            call = idCardRequest.postOcrIdCardRequest(params);
        }

        startRequest(call, callBack);
    }

    /**
     * 人脸识别
     * @param imageContent
     * @param imageType
     * @param callBack
     */
    public void doSendFaceDetectRequest(String imageContent, int imageType, final NetCallBack<FaceDetect83Bean> callBack) {

        /**
         * 是否检测并返回根据人脸特征判断出的年龄、性别、情绪等属性。
         * 加入全部属性
         */
        String return_attributes = "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus";

        /**
         * 返回83个人脸关键点
         */
        String return_landmark = "1";

        HashMap<String, String> params = new HashMap<>();
        initParams(params);
        File file = null;
        initImageType(imageContent, imageType, params, file);
        params.put("return_attributes", return_attributes);
        params.put("return_landmark", return_landmark);

        FaceRequest request = retrofit.create(FaceRequest.class);
        Call<FaceDetect83Bean> call = null;
        if (null != file) {
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("image_file", file.getName(), imageBody);
            call = request.postFaceDetectRequestByFile(params, imageBodyPart);
        }
        else {
            call = request.postFaceDetectRequest(params);
        }
        startRequest(call, callBack);
    }

    /**
     * 对比两张人脸
     * @param imageContent1
     * @param imageContent2
     * @param callBack
     */
    public void doSendFaceCompareRequest(String imageContent1, String imageContent2, NetCallBack<FaceCompareBean> callBack) {
        HashMap<String, String> params = new HashMap<>();
        initParams(params);
        params.put("image_base64_1",imageContent1);
        params.put("image_base64_2",imageContent2);
        FaceRequest request = retrofit.create(FaceRequest.class);
        Call<FaceCompareBean> call = null;
        call = request.postFaceCompareRequest(params);
        startRequest(call, callBack);
    }

    /**
     * 根据图片类型初始化
     * 必选（三选一）
     * image_url String 图片的URL
     * 注：在下载图片时可能由于网络等原因导致下载图片时间过长，建议使用 image_file 或image_base64 参数直接上传图片。
     * image_file File 一个图片，二进制文件，需要用 post multipart/form-data 的方式上传。
     * image_base64 String base64编码的二进制图片数据
     * 如果同时传入了image_url、image_file和image_base64参数，本API使用顺序为image_file优先，image_url最低。
     * @param imageContent
     * @param imageType
     * @param params
     * @param file
     */
    private void initImageType(String imageContent, int imageType, HashMap<String, String> params, File file) {
        switch (imageType) {
            case IMAGE_TYPE_BASE64:
                params.put("image_base64",imageContent);
                break;
            case IMAGE_TYPE_FILE:
                file = new File(imageContent);
                break;
            case IMAGE_TYPE_URL:
                params.put("image_url",imageContent);
                break;
            default:
                break;
        }
    }

    /**
     * 开始发送请求
     * @param call
     * @param callBack
     * @param <T>
     */
    private <T> void startRequest(Call<T> call, final NetCallBack<T> callBack) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, final Response<T> response) {
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
            public void onFailure(Call<T> call, Throwable t) {
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

    /**
     * 初始化请求参数
     * 传入key和secret
     * @param params
     */
    private void initParams(HashMap<String, String> params) {
        params.put("api_key", LibContants.appId);
        params.put("api_secret", LibContants.appKey);
    }

    /**
     * 定制OkHttpClient,添加请求日志
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.logjson(message);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }
}
