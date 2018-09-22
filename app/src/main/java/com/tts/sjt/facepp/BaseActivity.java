package com.tts.sjt.facepp;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    public void tip(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }
}
