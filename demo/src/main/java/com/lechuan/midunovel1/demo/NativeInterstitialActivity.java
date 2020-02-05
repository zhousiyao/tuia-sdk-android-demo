package com.lechuan.midunovel1.demo;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.nativead.Ad;
import com.lechuan.midunovel.nativead.AdCallBack;

public class NativeInterstitialActivity extends AppCompatActivity {

    private Ad ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_interstitial);
        //s 传空字符串, s1：slotId  s2:userId  s3:deviceId
        ad = new Ad("","325021", "", "");
        //动态修改配置替换后台获取的TUIA_APPKEY和TUIA_APPSECRET的值 可以调用ad.resetSlotId()重置广告id
        //TUIA_APPKEY，TUIA_APPSECRET 和AdSlotId 保持在同一个媒体维度下  否则校验失败
        ad.setConfigInfo("2ZjLbhEBCFAzBbihEtxLEq25mXKw","3WjgVkeo3j4GFm9vdcZbYp48TdDncr3NcNNpkLu");
        ad.resetSlotId("326966");
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
        boolean isConsume = true;
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
