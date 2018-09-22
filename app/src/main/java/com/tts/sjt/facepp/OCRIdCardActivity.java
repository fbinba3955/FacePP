package com.tts.sjt.facepp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tts.sjt.library.Utils.Util;
import com.tts.sjt.library.bean.OcrIdCardBean;
import com.tts.sjt.library.net.NetCallBack;
import com.tts.sjt.library.net.NetManager;

import java.lang.ref.SoftReference;
import java.util.List;

public class OCRIdCardActivity extends BaseActivity {

    Button handleUpBtn,handleDownBtn;

    ImageView upIdCardIv,downIdCardIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr_id_card);
        handleUpBtn = (Button) findViewById(R.id.btn_handle1);
        handleDownBtn = (Button) findViewById(R.id.btn_handle2);
        upIdCardIv = (ImageView) findViewById(R.id.iv_idcard_up);
        downIdCardIv = (ImageView) findViewById(R.id.iv_idcard_down);
        handleUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetManager netManager = new NetManager(new SoftReference<Context>(OCRIdCardActivity.this));
                netManager.initRetrofit().isHandleInUI(true);
                //base64转换是个耗时操作
                String imageBase64 = Util.getImageStr(OCRIdCardActivity.this, "idcard_sample_up.png");
                netManager.doSendOcrIdCardRequest(imageBase64, NetManager.IMAGE_TYPE_BASE64, new NetCallBack<OcrIdCardBean>() {
                    @Override
                    public void onSuccess(OcrIdCardBean response) {
                        List<OcrIdCardBean.CardsBean> cards = response.getCards();
                        if (cards.size() > 0) {
                            OcrIdCardBean.CardsBean bean = cards.get(0);
                            tip(bean.getName()
                                    + "\n"
                                    + bean.getAddress()
                                    + "\n"
                                    + bean.getBirthday()
                                    + "\n"
                                    + bean.getId_card_number()
                                    + "\n"
                                    + bean.getGender());
                        }
                    }

                    @Override
                    public void onFail(String failMsg) {
                        tip(failMsg);
                    }
                });
            }
        });
        handleDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetManager netManager = new NetManager(new SoftReference<Context>(OCRIdCardActivity.this));
                netManager.initRetrofit().isHandleInUI(true);
                String imageBase64 = Util.getImageStr(OCRIdCardActivity.this, "idcard_sample_down.png");
                netManager.doSendOcrIdCardRequest(imageBase64, NetManager.IMAGE_TYPE_BASE64, new NetCallBack<OcrIdCardBean>() {
                    @Override
                    public void onSuccess(OcrIdCardBean response) {
                        List<OcrIdCardBean.CardsBean> cards = response.getCards();
                        if (cards.size() > 0) {
                            OcrIdCardBean.CardsBean bean = cards.get(0);
                            tip(bean.getValid_date() + "\n" + bean.getIssued_by());
                        }
                    }

                    @Override
                    public void onFail(String failMsg) {
                        tip(failMsg);
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initIdCardShow();
    }

    private void initIdCardShow() {
        Glide.with(this)
                .load("file:///android_asset/idcard_sample_up.png")
                .into(upIdCardIv);
        Glide.with(this)
                .load("file:///android_asset/idcard_sample_down.png")
                .into(downIdCardIv);
    }
}
