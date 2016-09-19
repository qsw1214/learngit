package com.neusoft.my12603;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 明星 on 2016/9/9.
 */
public class LoginActivity extends Activity {

    private EditText mEt_name;
    private EditText mEt_pwd;
    private Button mBtn_login;
    private CheckBox mCb;
    private TextView mTv_pwd;
    private SharedPreferences sp;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initView() {
        mEt_name = (EditText) findViewById(R.id.et_name);
        mEt_pwd = (EditText) findViewById(R.id.et_pwd);
        mBtn_login = (Button) findViewById(R.id.btn_login);
        mCb = (CheckBox) findViewById(R.id.cb);
        mTv_pwd = (TextView) findViewById(R.id.tv_pwd);

    }
    private void initData() {
        sp = getSharedPreferences("loginState",MODE_PRIVATE);
        mEditor = sp.edit();

        //给TextVievw设置链接
        mTv_pwd.setText(Html.fromHtml("<a href=\"http://www.baidu.com\">忘记密码？</a>"));
        mTv_pwd.setMovementMethod(LinkMovementMethod.getInstance());
        mBtn_login.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(mEt_name.getText().toString())&&
                            !TextUtils.isEmpty(mEt_pwd.getText().toString())) {
                        if(mCb.isChecked()){
                            mEditor.putString("name",mEt_name.getText().toString());
                            mEditor.putString("pwd",mEt_pwd.getText().toString());
                            mEditor.commit();
                        }else {
                            SharedPreferences spr=getSharedPreferences("loginState",MODE_PRIVATE);
                            SharedPreferences.Editor editor=spr.edit();
                            editor.remove("name");
                            editor.remove("pwd");
                            editor.commit();
                        }
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(),"请输入内容",Toast.LENGTH_SHORT).show();
                    }
                }
            });




        }

    }

