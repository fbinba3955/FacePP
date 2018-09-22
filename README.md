# FacePP

![](http://881023.top/image/facepp1.jpg)

## 内容

- 个人联系，实现身份证识别，人脸识别，人脸对比。
- 需要申请face++的试用key与secret
- 正式使用需要收费

## 框架
face++框架
> https://www.faceplusplus.com.cn/

## 身份证识别
可以识别正反面

```
netManager.doSendOcrIdCardRequest(imageBase64, NetManager.IMAGE_TYPE_BASE64, new NetCallBack<OcrIdCardBean>() {});

```

![](http://881023.top/image/facepp2.jpg)

## 人脸识别

```
netManager.doSendFaceDetectRequest(imageBase64, NetManager.IMAGE_TYPE_BASE64, new NetCallBack<FaceDetect83Bean>() {});
```

![](http://881023.top/image/facepp3.jpg)


## 人脸对比

```
netManager.doSendFaceCompareRequest(imageBase64_1, imageBase64_2, new NetCallBack<FaceCompareBean>() {});
```

![](http://881023.top/image/facepp4.jpg)