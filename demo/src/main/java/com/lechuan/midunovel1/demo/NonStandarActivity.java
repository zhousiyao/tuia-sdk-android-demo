package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.lechuan.midunovel.base.util.FoxBaseCommonUtils;
import com.lechuan.midunovel.base.util.FoxBaseGsonUtil;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.view.FoxCustomerTm;
import com.lechuan.midunovel.view.FoxNsTmListener;
import com.lechuan.midunovel.view.video.bean.FoxResponseBean;


/**
 * 自定义广告：
 *  1.在AndroidManifest.xml中配置TUIA_APPKEY和TUIA_APPSECRET
 *  2.媒体后台创建广告id，通知运营配置广告
 *  3.调试可以使用demo提供的测试的
 *  注意：
 *    1.要确保TUIA_APPKEY和TUIA_APPSECRET以及广告id在同一个媒体下并开启广告配置
 *    2.关于AndroidX开发的项目:支持包请使用（androidx.appcompat:appcompat:1.2.0-alpha02）
 *    原因:https://stackoverflow.com/questions/41025200/android-view-inflateexception-error-inflating-class-android-webkit-webview?answertab=active#tab-top
 *
 * 活动处理：
 * SDK内部处理：
 *     素材展示媒体自己加载并在加载成功时调用素材曝光mOxCustomerTm.adExposed()，
 *     素材点击请调用mOxCustomerTm.adClicked()，同时传入返回的活动链接url调用
 *     mOxCustomerTm.openFoxActivity(mDataBean.getActivityUrl());
 */
public class NonStandarActivity extends BaseActivity {
    private FoxCustomerTm mOxCustomerTm;

    private TextView textView;
    private FoxResponseBean.DataBean mDataBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_standar);
        textView = (TextView)findViewById(R.id.content_text);
        String userId = getIntent().getStringExtra("userId");

        mOxCustomerTm = new FoxCustomerTm(this);

        mOxCustomerTm.setAdListener(new FoxNsTmListener() {
            @Override
            public void onReceiveAd(String result) {
                Log.d("========", "onReceiveAd:"+result);
                if (!FoxBaseCommonUtils.isEmpty(result)){
                    FoxResponseBean.DataBean dataBean = FoxBaseGsonUtil.GsonToBean(result,FoxResponseBean.DataBean.class);
                    if (dataBean!=null){
                        mDataBean = dataBean;
                    }
                    //素材加载成功时候调用素材加载曝光方法
                    mOxCustomerTm.adExposed();
                }
                //展示使用
                textView.setText(result);
            }

            @Override
            public void onFailedToReceiveAd() {
                Log.d("========", "onFailedToReceiveAd");
            }

            @Override
            public void onAdActivityClose(String s) {
                Log.d("========", "onAdActivityClose"+s);
                if (!FoxBaseCommonUtils.isEmpty(s)){
                    ToastUtils.showShort(s);
                }
            }

        });
        mOxCustomerTm.loadAd(323780,userId);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOxCustomerTm!=null && mDataBean!=null && !FoxBaseCommonUtils.isEmpty(mDataBean.getActivityUrl())){
                    //素材点击时候调用素材点击曝光方法
                    mOxCustomerTm.adClicked();
                    mOxCustomerTm.openFoxActivity(mDataBean.getActivityUrl());
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        if (mOxCustomerTm != null) {
            mOxCustomerTm.destroy();
        }
        super.onDestroy();
    }
}
