package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.base.util.FoxBaseCommonUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.view.FoxListener;
import com.lechuan.midunovel.view.FoxWallView;

/**
 *  关于原生插屏对接
 *  关于AndroidX开发的项目:支持包请使用androidx.appcompat:appcompat:1.2.0-alpha02
 *
 *  原因:https://stackoverflow.com/questions/41025200/android-view-inflateexception-error-inflating-class-android-webkit-webview?answertab=active#tab-top
 */
public class AppWallActivity extends BaseActivity {

    private FoxWallView mOxWallView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_wall);
        mOxWallView1 = (FoxWallView) findViewById(R.id.app1);
        String userId = getIntent().getStringExtra("userId");
        mOxWallView1.setAdListener(new FoxListener() {
            @Override
            public void onReceiveAd() {
                Log.d("========", "onReceiveAd");
            }

            @Override
            public void onFailedToReceiveAd() {
                Log.d("========", "onFailedToReceiveAd");
            }

            @Override
            public void onLoadFailed() {
                Log.d("========", "onLoadFailed");
            }

            @Override
            public void onCloseClick() {
                Log.d("========", "onCloseClick");
            }

            @Override
            public void onAdClick() {
                Log.d("========", "onAdClick");
            }

            @Override
            public void onAdExposure() {
                Log.d("========", "onAdExposure");
            }

            @Override
            public void onAdActivityClose(String s) {
                Log.d("========", "onAdActivityClose"+s);
                if (!FoxBaseCommonUtils.isEmpty(s)){
                    ToastUtils.showShort(s);
                }
            }
        });
        mOxWallView1.loadAd(301972,userId);
    }

    @Override
    protected void onDestroy() {
        if (mOxWallView1 != null) {
            mOxWallView1.destroy();
        }
        super.onDestroy();
    }
}
