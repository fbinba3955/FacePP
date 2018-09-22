package com.tts.sjt.library.net;

import com.tts.sjt.library.LibContants;
import com.tts.sjt.library.bean.OcrIdCardBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface IDCardRequest {

    /**
     * 识别身份证接口
     * base64 url
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(LibContants.IdCardPath + LibContants.OcrIdCardCmd)
    Call<OcrIdCardBean> postOcrIdCardRequest(@FieldMap HashMap<String, String> params);

    /**
     * 识别身份证接口
     * file
     * @param params
     * @param part
     * @return
     */
    @Multipart
    @POST(LibContants.IdCardPath + LibContants.OcrIdCardCmd)
    Call<OcrIdCardBean> postOcrIdCardRequestByFile(@PartMap HashMap<String, String> params, @Part MultipartBody.Part part);

}
