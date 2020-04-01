package com.lechuan.midunovel1.demo;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lechuan.midunovel.base.util.FoxBaseCommonUtils;
import com.lechuan.midunovel.base.util.FoxBaseToastUtils;
import com.lechuan.midunovel.demo.R;
import com.lechuan.midunovel.view.FoxCustomerTm;
import com.lechuan.midunovel.view.FoxNsTmListener;
import com.lechuan.midunovel.view.video.bean.FoxResponseBean;
import com.lechuan.midunovel.view.video.utils.FoxGsonUtil;

public class NonStandardVideoActivity extends BaseActivity {
    private FoxCustomerTm mOxCustomerTm;

    private FoxResponseBean.DataBean dataBean;
    private TextView textView;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_standar);
        textView = (TextView) findViewById(R.id.content_text);

        mOxCustomerTm = new FoxCustomerTm(this);
        mOxCustomerTm.setAdListener(new FoxNsTmListener() {

            @Override
            public void onReceiveAd(String result) {
                if (!FoxBaseCommonUtils.isEmpty(result)) {
                    dataBean = FoxGsonUtil.GsonToBean(result, FoxResponseBean.DataBean.class);
                }
                Log.d("========", "onReceiveAd:" + result);
                textView.setText(result);
                if (mOxCustomerTm != null) {
                    mOxCustomerTm.adExposed();
                }
            }

            @Override
            public void onFailedToReceiveAd() {
                Log.d("========", "onFailedToReceiveAd");
            }

            @Override
            public void onAdActivityClose(String data) {
                Log.d("========", "onAdActivityClose" + data);
                if (!TextUtils.isEmpty(data)) {
                    FoxBaseToastUtils.showShort("发奖回调：" + data);
                }
            }
        });

        mOxCustomerTm.loadAdAndReport(323780, userId);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOxCustomerTm != null && dataBean != null && !FoxBaseCommonUtils.isEmpty(dataBean.getActivityUrl())) {
                    mOxCustomerTm.adClicked();
                    mOxCustomerTm.openFoxActivity(dataBean.getActivityUrl());
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
