package com.tts.sjt.library.net;

import com.tts.sjt.library.LibContants;
import com.tts.sjt.library.bean.OcrIdCardBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IDCardRequest {


    @FormUrlEncoded
    @POST(LibContants.IdCardPath + LibContants.OcrIdCardCmd)
    Call<OcrIdCardBean> postOcrIdCardRequest(@FieldMap HashMap<String, String> params);

}
