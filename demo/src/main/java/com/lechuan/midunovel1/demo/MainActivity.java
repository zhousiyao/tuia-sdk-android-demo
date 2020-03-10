package com.lechuan.midunovel1.demo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lechuan.midunovel.demo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String userId = null;


    private static final String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.sTMBrButton).setOnClickListener(this);
        findViewById(R.id.TMItButton258092).setOnClickListener(this);
        findViewById(R.id.TMBrButton).setOnClickListener(this);
        findViewById(R.id.floatButton).setOnClickListener(this);
        findViewById(R.id.nsButton).setOnClickListener(this);
        findViewById(R.id.nsCPButton).setOnClickListener(this);
        userId = getIntent().getStringExtra("userId");
        if (Build.VERSION.SDK_INT >= 23) {
            if(!lacksPermissions(this,NEEDED_PERMISSIONS)){
                ActivityCompat.requestPermissions(this,NEEDED_PERMISSIONS,0);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.sTMBrButton:
                //横幅
                intent = new Intent(this, SbannerActivity.class);
                break;
            case R.id.TMItButton258092:
                //插屏
                intent = new Intent(this, InterstitialActivity.class);
                break;
            case R.id.TMBrButton:
                //Banner
                intent = new Intent(this, BannerActivity.class);
                break;
            case R.id.floatButton:
                //浮标
                intent = new Intent(this, DobberActivity.class);
                break;
            case R.id.nsButton:
                //自定义
                intent = new Intent(this, NonStandarActivity.class);
                break;
            case R.id.nsCPButton:
                //自定义
                intent = new Intent(this, NativeInterstitialActivity.class);
                break;
            default:
                return;
        }
        intent.putExtra("userId", userId);
        startActivity(intent);
    }


    /**
     * 判断权限集合
     * permissions 权限数组
     * return false-表示没有改权限  true-表示权限已开启
     */
    public  boolean lacksPermissions(Context mContexts, String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
