package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.base.util.FoxBaseCommonUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.view.FoxListener;
import com.lechuan.midunovel.view.FoxTbScreen;

/**
 * 关于普通插屏对接
 * 1.在AndroidManifest.xml中配置TUIA_APPKEY和TUIA_APPSECRET
 * 2.媒体后台创建广告id，通知运营配置广告
 * 3.调试可以使用demo提供的测试的
 * 注意：
 *   要确保TUIA_APPKEY和TUIA_APPSECRET以及广告id在同一个媒体下并开启广告配置
 *
 *  关于AndroidX开发的项目:支持包请使用（androidx.appcompat:appcompat:1.2.0-alpha02）
 *  原因:https://stackoverflow.com/questions/41025200/android-view-inflateexception-error-inflating-class-android-webkit-webview?answertab=active#tab-top
 */
public class InterstitialActivity extends BaseActivity {
    private FoxTbScreen mTMItAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        String userId = getIntent().getStringExtra("userId");
        mTMItAd = new FoxTbScreen(this);
        mTMItAd.setAdListener(new FoxListener() {
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
        mTMItAd.loadAd(323776,userId);
    }
    @Override
    protected void onDestroy() {
        if (mTMItAd != null) {
            mTMItAd.destroy();
        }
        super.onDestroy();
    }
}
