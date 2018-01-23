package com.xing.ando.androido.font;

import android.support.v4.provider.FontRequest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xing.ando.androido.R;

public class DownLoadFontActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_font);
        downLoadFont();
    }

    private void downLoadFont() {
//        FontRequest fontRequest = new FontRequest("com.example.fontprovider.authority",
//                "com.example.fontprovider","my font")
    }
}
