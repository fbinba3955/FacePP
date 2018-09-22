package com.tts.sjt.library;

public class LibContants {

    public static String appId = "";

    public static String appKey = "";

    //基础URL
    public final static String baseUrl = "https://api-cn.faceplusplus.com/";

    //https://api-cn.faceplusplus.com/cardpp/v1/ocridcard

    //身份证相关
    public final static String IdCardPath = "cardpp/v1/";

    //OCR识别身份证
    public final static String OcrIdCardCmd = "ocridcard";

    //https://api-cn.faceplusplus.com/facepp/v3/detect

    //人脸相关
    public final static String facePath = "facepp/v3/";

    //人脸识别
    public final static String faceDetectCmd = "detect";

    //https://api-cn.faceplusplus.com/facepp/v3/compare

    //人脸对比
    public final static String faceCompareCmd = "compare";
}
