package com.tts.sjt.facepp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends BaseActivity implements View.OnClickListener{

    Button ocrBtn,faceDetectBtn, faceCompareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ocrBtn = (Button) findViewById(R.id.btn_ocr_idcard);
        ocrBtn.setOnClickListener(this);
        faceDetectBtn = (Button) findViewById(R.id.btn_face_detect);
        faceDetectBtn.setOnClickListener(this);
        faceCompareBtn = findViewById(R.id.btn_face_compare);
        faceCompareBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_ocr_idcard:
                startActivity(new Intent(HomeActivity.this, OCRIdCardActivity.class));
                break;
            case R.id.btn_face_detect:
                startActivity(new Intent(HomeActivity.this, FaceDetectActivity.class));
                break;
            case R.id.btn_face_compare:
                startActivity(new Intent(HomeActivity.this, FaceCompareActivity.class));
                break;
        }
    }
}
