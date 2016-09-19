package com.neusoft.my12603;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏模式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SharedPreferences sp = getSharedPreferences("loginState", 0);
        final String name = sp.getString("name", "");
        final String pwd = sp.getString("pwd", "");
            setContentView(R.layout.activity_splash);
            Log.i("DDD", "" + Thread.currentThread().getId());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.i("ddd", "" + Thread.currentThread().getId());
                    if (TextUtils.isEmpty(name) && TextUtils.isEmpty(pwd)) {
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            },3000);
        }
    }

