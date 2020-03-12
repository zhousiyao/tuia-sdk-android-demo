package com.lechuan.midunovel1.demo;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.nativead.Ad;
import com.lechuan.midunovel.nativead.AdCallBack;

/**
 * 关于原生插屏对接
 *  1.在AndroidManifest.xml中配置TUIA_APPKEY和TUIA_APPSECRET
 *  2.媒体后台创建广告id，通知运营配置广告
 *  3.调试可以使用demo提供的测试的
 *  注意：
 *    1.要确保TUIA_APPKEY和TUIA_APPSECRET以及广告id在同一个媒体下并开启广告配置
 *
 *    2.关于AndroidX开发的项目:支持包请使用（androidx.appcompat:appcompat:1.2.0-alpha02）
 *
 *    原因:https://stackoverflow.com/questions/41025200/android-view-inflateexception-error-inflating-class-android-webkit-webview?answertab=active#tab-top
 */
public class NativeInterstitialActivity extends AppCompatActivity {

    private Ad ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_interstitial);
        //s appkey s1：slotId  s2:userId  s3:deviceId
        ad = new Ad("4UycwwZv41rwzne1ZXgtQBgDSnPH","325021", "", "", Ad.AD_NEW_LOADING_HIDE);
        ad.init(NativeInterstitialActivity.this, null, Ad.AD_URL_NEW, new AdCallBack() {

            @Override
            public void onReceiveAd() {
                Log.d("========", "onReceiveAd");
            }

            @Override
            public void onFailedToReceiveAd() {
                Log.d("========", "onFailedToReceiveAd");
            }

            @Override
            public void onActivityClose() {
                Log.d("========", "onActivityClose");
            }

            @Override
            public void onActivityShow() {
                Log.d("========", "onActivityShow");
            }

            @Override
            public void onRewardClose() {
                Log.d("========", "onRewardClose");
            }

            @Override
            public void onRewardShow() {
                Log.d("========", "onRewardShow");
            }

            @Override
            public void onPrizeClose() {
                Log.d("========", "onPrizeClose");
            }

            @Override
            public void onPrizeShow() {
                Log.d("========", "onPrizeShow");
            }
        });
        ad.loadAd(NativeInterstitialActivity.this, false);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (ad != null) {
            ad.resetAdSize(newConfig.orientation);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isConsume = false;
        if (ad != null) {
            isConsume = ad.onKeyBack(keyCode, event);
        }
        if (!isConsume) {
            return super.onKeyDown(keyCode, event);
        }
        return isConsume;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ad != null) {
            ad.destroy();
        }
    }
}
