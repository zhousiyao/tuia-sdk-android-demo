package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.base.util.FoxBaseCommonUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.view.FoxListener;
import com.lechuan.midunovel.view.FoxWallView;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于浮标对接
 * 1.在AndroidManifest.xml中配置TUIA_APPKEY和TUIA_APPSECRET
 * 2.媒体后台创建广告id，通知运营配置广告
 * 3.调试可以使用demo提供的测试的
 * 注意：
 *   要确保TUIA_APPKEY和TUIA_APPSECRET以及广告id在同一个媒体下并开启广告配置
 *
 *  关于AndroidX开发的项目:支持包请使用（androidx.appcompat:appcompat:1.2.0-alpha02）
 *  原因:https://stackoverflow.com/questions/41025200/android-view-inflateexception-error-inflating-class-android-webkit-webview?answertab=active#tab-top
 */
public class DobberActivity extends BaseActivity {

    private ListView mListView;
    private FoxWallView mOxWallView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dobber);
        mListView = (ListView) findViewById(R.id.list);
        mOxWallView = (FoxWallView) findViewById(R.id.TMAw1);
        String userId = getIntent().getStringExtra("userId");
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
        mOxWallView.setAdListener(new FoxListener() {
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
        mOxWallView.loadAd(323779,userId);
    }

    @Override
    protected void onDestroy() {
        if (mOxWallView != null) {
            mOxWallView.destroy();
        }
        super.onDestroy();
    }

    private List<String> getData() {

        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 40; i++) {
            data.add("测试数据" + i);
        }
        return data;
    }
}
