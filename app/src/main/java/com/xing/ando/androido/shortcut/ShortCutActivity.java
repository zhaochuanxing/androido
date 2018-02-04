package com.xing.ando.androido.shortcut;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.pm.ShortcutInfoCompat;
import android.support.v4.content.pm.ShortcutManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xing.ando.androido.R;

import java.util.Arrays;

public class ShortCutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_cut);
        addDynamicShortCut();
        addPinedShortCut();
    }

    private void addDynamicShortCut() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
            ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(this, "id")
                    .setShortLabel("web site")
                    .setLongLabel("open the web site")
                    .setIcon(Icon.createWithResource(this, R.drawable.tool))
                    .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")))
                    .build();
            shortcutManager.setDynamicShortcuts(Arrays.asList(shortcutInfo));
        }
    }

    private void addPinedShortCut(){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = this.getSystemService(ShortcutManager.class);
            if(ShortcutManagerCompat.isRequestPinShortcutSupported(this)){
                // Assumes there's already a shortcut with the ID "my-shortcut".
                // The shortcut must be enabled.
                ShortcutInfoCompat shortcutInfo = new ShortcutInfoCompat.Builder(this,"my_shortcut").build();
                // Create the PendingIntent object only if your app needs to be notified
                // that the user allowed the shortcut to be pinned. Note that, if the
                // pinning operation fails, your app isn't notified. We assume here that the
                // app has implemented a method called createShortcutResultIntent() that
                // returns a broadcast intent.
                Intent pinedIntent =ShortcutManagerCompat.createShortcutResultIntent(this,shortcutInfo);;
                // Configure the intent so that your app's broadcast receiver gets
                // the callback successfully.
                PendingIntent successCallBack = PendingIntent.getBroadcast(this,0,pinedIntent,0);

                ShortcutManagerCompat.requestPinShortcut(this,shortcutInfo,successCallBack.getIntentSender());

            }
        }
    }
}
