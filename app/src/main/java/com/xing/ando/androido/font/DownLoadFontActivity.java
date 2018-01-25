package com.xing.ando.androido.font;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.provider.FontRequest;
import android.support.v4.provider.FontsContractCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xing.ando.androido.R;

public class DownLoadFontActivity extends AppCompatActivity {

    private static final String TAG = DownLoadFontActivity.class.getSimpleName();
    private TextView mProTx;
    private View mLoadBtn;
    private Handler mHandler;
    private HandlerThread mHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_font);
        mProTx = findViewById(R.id.tx_program);
        mLoadBtn = findViewById(R.id.btn_load);
        mLoadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLoadFont();
            }
        });
    }

    private void downLoadFont() {
        String fontName = "Acme";
        //需要机器中安装有googleservice,有fonts provider,否则在下面的callback中会收到失败的回调
        // gms 是 google mobile services 的缩写
        FontRequest fontRequest = new FontRequest(
                "com.google.android.gms.fonts"
                , "com.google.android.gms",
                fontName, R.array.com_google_android_gms_fonts_certs);

        FontsContractCompat.FontRequestCallback callback = new FontsContractCompat.FontRequestCallback() {

            @Override
            public void onTypefaceRequestFailed(int reason) {
                super.onTypefaceRequestFailed(reason);
                // reason = -1 FAIL_REASON_PROVIDER_NOT_FOUND the given provider is not found on the device
                // 在没有安装google service的小米4上收到了这个消息
                Log.i(TAG, "onTypefaceRequestFailed " + reason);
            }

            @Override
            public void onTypefaceRetrieved(Typeface typeface) {
                super.onTypefaceRetrieved(typeface);
                Log.i(TAG, "onTypefaceRetrieved " + typeface);
                mProTx.setTypeface(typeface);

            }
        };

        FontsContractCompat.requestFont(this,fontRequest,callback,getHandlerThreadHander());
    }

    private Handler getHandlerThreadHander() {
        if (mHandler == null) {
            mHandlerThread = new HandlerThread("fonts");
            mHandlerThread.start();
            mHandler = new Handler(mHandlerThread.getLooper());
        }
        return mHandler;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
        }
        if(mHandlerThread!=null){
            mHandlerThread.quit();
        }
    }
}
