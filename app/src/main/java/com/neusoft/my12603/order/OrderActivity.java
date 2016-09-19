package com.neusoft.my12603.order;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.neusoft.my12603.R;

/**
 * Created by 明星 on 2016/9/12.
 */
public class OrderActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        initView();
    }

    private void initView() {
       Button btn_wm= (Button) findViewById(R.id.btn_wm);
        final ImageView image=new ImageView(OrderActivity.this);
        image.setImageDrawable(getResources().getDrawable(R.drawable.qr));
       btn_wm.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               new AlertDialog.Builder(OrderActivity.this)
                       .setTitle("查看我二维码")
                       .setView(image)
                       .setPositiveButton("确定",null)
                       .show();
           }
       });


    }
}
