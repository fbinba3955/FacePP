package com.tts.sjt.facepp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tts.sjt.library.Utils.Util;
import com.tts.sjt.library.bean.FaceCompareBean;
import com.tts.sjt.library.bean.FaceDetect83Bean;
import com.tts.sjt.library.net.NetCallBack;
import com.tts.sjt.library.net.NetManager;

import java.io.ByteArrayOutputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class FaceCompareActivity extends BaseActivity implements View.OnClickListener {

    Bitmap bitmap1, bitmap2;

    ImageView face1Iv,face2Iv;

    Button compareBtn;

    /**
     * 橙红年代
     * 正好摘取两张马思纯的照片
     */
    private String imageName1 = "facec1.jpg";
    private String imageName2 = "facec2.jpg";
    /**
     * glide使用
     */
    private final String assetsPath = "file:///android_asset/";
    private String assetsImage1 = assetsPath + imageName1;
    private String assetsImage2 = assetsPath + imageName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_compare);
        face1Iv = (ImageView) findViewById(R.id.iv1);
        face2Iv = (ImageView) findViewById(R.id.iv2);
        compareBtn = findViewById(R.id.btn_compare);
        compareBtn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Glide.with(FaceCompareActivity.this).load(assetsImage1).into(face1Iv);
        Glide.with(FaceCompareActivity.this).load(assetsImage2).into(face2Iv);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_compare:
                handleCompare();
                break;
        }
    }

    /**
     * 开始比较
     */
    private void handleCompare() {
        NetManager netManager = new NetManager(new SoftReference<Context>(FaceCompareActivity.this));
        netManager.initRetrofit().isHandleInUI(true);
        String imageBase64_1 = Util.getImageStr(FaceCompareActivity.this, imageName1);
        String imageBase64_2 = Util.getImageStr(FaceCompareActivity.this, imageName2);
        netManager.doSendFaceCompareRequest(imageBase64_1, imageBase64_2, new NetCallBack<FaceCompareBean>() {
            @Override
            public void onSuccess(FaceCompareBean response) {
                //获取相似度
                String confidence = response.getConfidence() + "";
                tip("相似度："+confidence);
                ArrayList<FaceDetect83Bean.FacesBean.FaceRectangleBean> rects1 = new ArrayList<>();
                ArrayList<FaceDetect83Bean.FacesBean.FaceRectangleBean> rects2 = new ArrayList<>();
                rects1.add(response.getFaces1().get(0).getFace_rectangle());
                rects2.add(response.getFaces2().get(0).getFace_rectangle());
                findFaceInImage(rects1, rects2);
            }

            @Override
            public void onFail(String failMsg) {
                tip(failMsg);
            }
        });
    }

    /**
     * 把两张图片中的人脸标示出来
     */
    private void findFaceInImage(List<FaceDetect83Bean.FacesBean.FaceRectangleBean> rects1,
                                 List<FaceDetect83Bean.FacesBean.FaceRectangleBean> rects2) {
        try {
            //对识别的人脸1加标示
            bitmap1 = Util.drawRectOnBitmap(Util.getImageStream(FaceCompareActivity.this, imageName1),
                    rects1);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();
            Glide.with(FaceCompareActivity.this)
                    .load(bytes)
                    .into(face1Iv);

            //对识别的人脸2加标示
            bitmap2 = Util.drawRectOnBitmap(Util.getImageStream(FaceCompareActivity.this, imageName2),
                    rects2);
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, baos2);
            byte[] bytes2 = baos2.toByteArray();
            Glide.with(FaceCompareActivity.this)
                    .load(bytes2)
                    .into(face2Iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            tip("标记完成");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != bitmap1) {
            bitmap1.recycle();
        }
        if (null != bitmap2) {
            bitmap2.recycle();
        }
    }
}
