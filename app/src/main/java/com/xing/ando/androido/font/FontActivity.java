package com.xing.ando.androido.font;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xing.ando.androido.R;

public class FontActivity extends AppCompatActivity {

    private TextView mNormalTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);
        mNormalTx = findViewById(R.id.tx_normal);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Typeface font = getResources().getFont(R.font.lato);
        }
        Typeface font = ResourcesCompat.getFont(this, R.font.lato);
        mNormalTx.setTypeface(font);
    }
}
