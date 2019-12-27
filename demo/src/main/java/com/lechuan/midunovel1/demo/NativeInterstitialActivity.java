package com.lechuan.midunovel1.demo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import com.tuia.ad.Ad;
import com.tuia.ad.AdCallBack;

public class NativeInterstitialActivity extends BaseActivity {

    private static final String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_interstitial);
        if (hasPermission()) {
            Ad ad = new Ad("4UycwwZv41rwzne1ZXgtQBgDSnPH", "323774", "", "");
            ad.init(NativeInterstitialActivity.this, null, new AdCallBack() {

                @Override
                public void onActivityClose() {

                }

                @Override
                public void onActivityShow() {

                }

                @Override
                public void onRewardClose() {

                }

                @Override
                public void onRewardShow() {

                }

                @Override
                public void onPrizeClose() {

                }

                @Override
                public void onPrizeShow() {

                }
            });
            ad.show();
        }
    }


    /**
     * 权限处理
     *
     * @return
     */
    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!lacksPermissions(this, NEEDED_PERMISSIONS)) {
                ActivityCompat.requestPermissions(this, NEEDED_PERMISSIONS, 0);
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断权限集合
     * permissions 权限数组
     * return false-表示没有改权限  true-表示权限已开启
     */
    public boolean lacksPermissions(Context mContexts, String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(mContexts, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
