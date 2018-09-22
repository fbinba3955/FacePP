package com.tts.sjt.library.net;

import com.tts.sjt.library.LibContants;
import com.tts.sjt.library.bean.FaceCompareBean;
import com.tts.sjt.library.bean.FaceDetect83Bean;
import com.tts.sjt.library.bean.OcrIdCardBean;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * 人脸识别相关
 * @author shijianting
 * 2018/9/22 上午10:43
 */
public interface FaceRequest {

    /**
     * 人脸识别接口
     * base64 url
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(LibContants.facePath + LibContants.faceDetectCmd)
    Call<FaceDetect83Bean> postFaceDetectRequest(@FieldMap HashMap<String, String> params);

    /**
     * 人脸识别接口
     * file
     * @param params
     * @param part
     * @return
     */
    @Multipart
    @POST(LibContants.facePath + LibContants.faceDetectCmd)
    Call<FaceDetect83Bean> postFaceDetectRequestByFile(@PartMap HashMap<String, String> params, @Part MultipartBody.Part part);


    /**
     * 人脸对比接口
     * base64 url
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(LibContants.facePath + LibContants.faceCompareCmd)
    Call<FaceCompareBean> postFaceCompareRequest(@FieldMap HashMap<String, String> params);

    /**
     * 人脸对比接口
     * file
     * @param params
     * @param parts
     * @return
     */
    @Multipart
    @POST(LibContants.facePath + LibContants.faceCompareCmd)
    Call<FaceCompareBean> postFaceCompareRequestByFile(@PartMap HashMap<String, String> params, @Part List<MultipartBody.Part> parts);


}
