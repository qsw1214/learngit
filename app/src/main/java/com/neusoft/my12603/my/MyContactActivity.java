package com.neusoft.my12603.my;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.neusoft.my12603.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by 明星 on 2016/9/10.
 */
public class MyContactActivity extends Activity{

    private List<Map<String, Object>> mListcontact;
    private ListView lv_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycontact);
        ActionBar bar=getActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        initView();
        initData();

    }

    private void initData() {

        mListcontact = new ArrayList<Map<String,Object>>();
        //row 1
        Map<String,Object> row1=new HashMap<String,Object>();
        row1.put("name","刘明星(成人)");
        row1.put("Idcard","身份证:130481199503036938");
        row1.put("tel","电话:15732051874");
        mListcontact.add(row1);
        //row2
        Map<String,Object> row2=new HashMap<String,Object>();
        row2.put("name","张扬(学生)");
        row2.put("Idcard","身份证:222222222222222222");
        row2.put("tel","电话:15230535289");
        mListcontact.add(row2);
        Map<String,Object> row3=new HashMap<String,Object>();
        //row3
        row3.put("name","刘明阳(学生)");
        row3.put("Idcard","身份证:111111111111111111");
        row3.put("tel","电话:15733629712");
        mListcontact.add(row3);
        MyAdapter myAdapter=new MyAdapter();
        lv_contact.setAdapter(myAdapter);

    }

    private void initView() {
        lv_contact = (ListView) findViewById(R.id.lv_contact);
        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(),MyContactEditActivity.class);
                intent.putExtra("row", (Serializable) mListcontact.get(position));
                startActivity(intent);
                }

        });
    }

    private class MyAdapter  extends BaseAdapter{
        @Override
        public int getCount() {
            return mListcontact.size();
        }

        @Override
        public Object getItem(int position) {
            return mListcontact.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(getApplicationContext(),R.layout.contact_item,null);
            TextView tv_name= (TextView) view.findViewById(R.id.tv_name);
            TextView tv_cid= (TextView) view.findViewById(R.id.tv_cid);
            TextView tv_phone= (TextView) view.findViewById(R.id.tv_phone);
            tv_name.setText((CharSequence) mListcontact.get(position).get("name"));
            tv_cid.setText((CharSequence) mListcontact.get(position).get("Idcard"));
            tv_phone.setText((CharSequence) mListcontact.get(position).get("tel"));
            return view;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //访问服务器，跟新数据
        Toast.makeText(MyContactActivity.this,"刷新",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_contact,menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        //菜单事件处理
       switch (item.getItemId()){
           case android.R.id.home:
               finish();
               break;
           case R.id.mn_contact_add:
               //跳转到添加新用户
               Intent intent=new Intent();
               intent.setClass(getApplicationContext(),MyContactNewActivity.class);
               startActivity(intent);
               break;
       }
        return super.onMenuItemSelected(featureId, item);
    }
}
