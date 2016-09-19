package com.neusoft.my12603.my;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.neusoft.my12603.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 明星 on 2016/9/10.
 */
public class MyContactEditActivity extends Activity{

    private ListView mLv_edit;
    private Button mBtn_save;
    private List<Map<String,Object>> data=null;
    private SimpleAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_edit);
        initView();
        initData();
        initControl();
    }

    private void initView() {
        mLv_edit = (ListView) findViewById(R.id.lv_edit);
        mBtn_save = (Button) findViewById(R.id.btn_save);
        mBtn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
            }
        });



    }
    private void initControl() {
        //适配器
        adapter = new SimpleAdapter(getApplicationContext(),data,R.layout.edit_item,new String[]{"key1","key2","key3"},
                new int[]{R.id.tv_Ename,R.id.tv_edit,R.id.iv_go});
                mLv_edit.setAdapter(adapter);
                mLv_edit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                         switch (position){
                             case 0:
                                 final EditText edtName=new EditText(MyContactEditActivity.this);
                                 edtName.setText((String) data.get(position).get("key2"));
                                 edtName.selectAll(); //默认选中
                                 new AlertDialog.Builder(MyContactEditActivity.this)
                                         .setIcon(android.R.drawable.ic_dialog_info)
                                         .setTitle("请输入姓名")
                                         .setView(edtName)
                                         .setPositiveButton("确定",
                                                 new DialogInterface.OnClickListener() {
                                                     @Override
                                                     public void onClick(DialogInterface dialog, int which) {
                                                         String name=edtName.getText().toString();
                                                         if (TextUtils.isEmpty(name)){
                                                             edtName.setError("请输入姓名");
                                                             edtName.requestFocus();
                                                         }else {
                                                             //设置对话框自动关闭
                                                             dialog.dismiss();
                                                             data.get(position).put("key2",edtName.getText().toString());
                                                             //更新listview
                                                             adapter.notifyDataSetChanged();
                                                         }
                                                     }
                                                 })
                                         .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                     @Override
                                                     public void onClick(DialogInterface dialog, int which) {
                                                         dialog.dismiss();
                                                     }
                                                 }
                                         ).show();
                                 break;
                             case 3:
                                 final String[] types=new String[]{"成人","学生","儿童","其他"};
                                 String key2= (String) data.get(position).get("key2");
                                 int idx=0;
                                 for(int i=0;i<types.length;i++){
                                     if (types[i].equals(key2)){
                                         idx=i;
                                         break;
                                     }
                                 }
                                 new AlertDialog.Builder(MyContactEditActivity.this)
                                         .setIcon(android.R.drawable.ic_dialog_alert)
                                         .setTitle("请选择乘客的类型")
                                         .setSingleChoiceItems(types, idx, new DialogInterface.OnClickListener() {
                                             @Override
                                             public void onClick(DialogInterface dialog, int which) {
                                                 data.get(position).put("key2",types[which]);
                                                 adapter.notifyDataSetChanged();
                                                 dialog.dismiss();
                                             }
                                         }).setNegativeButton("取消",null).show();
                                 break;
                             case 4:
                                 final EditText et_Phone=new EditText(MyContactEditActivity.this);
                                 et_Phone.setText((String)data.get(position).get("key2"));
                                 et_Phone.selectAll();
                                 new AlertDialog.Builder(MyContactEditActivity.this)
                                         .setIcon(android.R.drawable.ic_dialog_alert)
                                         .setTitle("请输入电话")
                                         .setView(et_Phone)
                                         .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                             @Override
                                             public void onClick(DialogInterface dialog, int which) {
                                                 String phone=et_Phone.getText().toString();
                                                 if (TextUtils.isEmpty(phone)){
                                                     et_Phone.setError("请输入电话");
                                                     et_Phone.requestFocus();
                                                 }else {
                                                     data.get(position).put("key2", phone);
                                                     adapter.notifyDataSetChanged();
                                                 }
                                             }
                                         })
                                         .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                             @Override
                                             public void onClick(DialogInterface dialog, int which) {
                                                 dialog.dismiss();
                                             }
                                         }).show();
                                 break;
                         }
                    }
                });
       }

    private void initData() {
       //接受数据
        Intent intent=getIntent();
        Map<String,Object> contact= (Map<String, Object>) intent.getSerializableExtra ("row");
        //数据部分
        data=new ArrayList<Map<String,Object>>();
        //row1： 姓名
        Map<String,Object>  row1=new HashMap<String,Object>();
        String name= (String) contact.get("name");
        row1.put("key1","姓名");
        row1.put("key2",name.split("\\(")[0]); //split 根据特殊字符分割字符串，并将其转化为字符串数组
        row1.put("key3",R.drawable.forward_25);
        data.add(row1);

        //row2:证件类型
        Map<String,Object> row2=new HashMap<String, Object>();
        String idcard= (String) contact.get("Idcard");
        row2.put("key1","证件类型");
        row2.put("key2",idcard.split(":")[0]);
        row2.put("key3",null);
        data.add(row2);

        //row3:证件号码
        Map<String,Object> row3=new HashMap<String, Object>();
        row3.put("key1","证件号码");
        row3.put("key2",idcard.split(":")[1]);
        row3.put("key3",null);
        data.add(row3);

        //row4:乘客类型
        Map<String,Object> row4=new HashMap<String,Object>();
        row4.put("key1","乘客类型");
        row4.put("key2", name.split("\\(")[1].split("\\)")[0]);
        row4.put("key3",R.drawable.forward_25);
        data.add(row4);

       //row5:电话
        Map<String,Object> row5=new HashMap<String, Object>();
        String tel= (String) contact.get("tel");
        Log.i("Log",tel);
        row5.put("key1","电话");
        row5.put("key2",tel.split(":")[1]);
        row5.put("key3",R.drawable.forward_25);
        data.add(row5);
        }




    }














