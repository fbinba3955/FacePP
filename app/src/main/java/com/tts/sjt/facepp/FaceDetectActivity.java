package com.tts.sjt.facepp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tts.sjt.library.Utils.Util;
import com.tts.sjt.library.bean.FaceDetect83Bean;
import com.tts.sjt.library.bean.OcrIdCardBean;
import com.tts.sjt.library.net.NetCallBack;
import com.tts.sjt.library.net.NetManager;

import java.io.ByteArrayOutputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class FaceDetectActivity extends BaseActivity implements View.OnClickListener{

    Button detectBtn;

    ImageView faceDetectIv;

    private String imageName = "face1.jpg";
    /**
     * glide使用
     */
    private final String assetsPath = "file:///android_asset/";
    private String assetsImage = assetsPath + imageName;

    private Bitmap faceBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detect);
        detectBtn = (Button) findViewById(R.id.btn_detect);
        detectBtn.setOnClickListener(this);
        faceDetectIv = (ImageView) findViewById(R.id.iv_face_detect);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Glide.with(this).load(assetsImage).into(faceDetectIv);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_detect:
                handleDetect();
                break;
        }
    }

    /**
     * 开始识别
     */
    private void handleDetect() {
        NetManager netManager = new NetManager(new SoftReference<Context>(FaceDetectActivity.this));
        netManager.initRetrofit().isHandleInUI(true);
        String imageBase64 = Util.getImageStr(FaceDetectActivity.this, imageName);
        netManager.doSendFaceDetectRequest(imageBase64, NetManager.IMAGE_TYPE_BASE64, new NetCallBack<FaceDetect83Bean>() {
            @Override
            public void onSuccess(FaceDetect83Bean response) {
                //识别到的人脸，可以是多张
                List<FaceDetect83Bean.FacesBean> faces = response.getFaces();
                if (faces.size() > 0) {
                    //每张人脸的矩形范围
                    List<FaceDetect83Bean.FacesBean.FaceRectangleBean> rects = new ArrayList<>();
                    for (FaceDetect83Bean.FacesBean faceTemp: faces) {
                        rects.add(faceTemp.getFace_rectangle());
                    }
                    try {
                        //对识别的人脸加标示
                        faceBitmap = Util.drawRectOnBitmap(Util.getImageStream(FaceDetectActivity.this, imageName),
                                rects);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        faceBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] bytes = baos.toByteArray();
                        Glide.with(FaceDetectActivity.this)
                                .load(bytes)
                                .into(faceDetectIv);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        tip("标记完成");
                    }
                }
            }

            @Override
            public void onFail(String failMsg) {
                tip(failMsg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (faceBitmap != null) {
            faceBitmap.recycle();
        }
    }


}
