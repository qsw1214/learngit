package com.neusoft.my12603.ticket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.neusoft.my12603.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by star on 2016/9/18.
 */
public class TicketStep1Activity extends Activity {
    ListView lvTicketResultStep1 = null;
    TextView tvTicketResultStep1Before = null;
    TextView tvTicketResultStep1After = null;
    TextView tvTicketResultStep1DateTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_result_step1);
        initView();
        initData();

    }


    private void initView() {
        lvTicketResultStep1 = (ListView) findViewById(R.id.lvTicketResultStep1);
        tvTicketResultStep1Before = (TextView) findViewById(R.id.tvTicket_before);
        tvTicketResultStep1After = (TextView) findViewById(R.id.tvTicket_after);
        tvTicketResultStep1DateTitle = (TextView) findViewById(R.id.tvTicketResultStep1DateTitle);
        tvTicketResultStep1Before.setOnClickListener(new HandlerTicketResultStep1());
        tvTicketResultStep1After.setOnClickListener(new HandlerTicketResultStep1());

    }

    private void initData() {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String, Object> row1 = new HashMap<String, Object>();
        row1.put("trainNo", "G108");
        row1.put("flg1", R.drawable.flg_shi);
        row1.put("flg2", R.drawable.flg_guo);
        row1.put("timeFrom", "07:00");
        row1.put("timeTo", "14:39(0日)");
        row1.put("seat1", "高级软卧:100");
        row1.put("seat2", "硬座:8");
        row1.put("seat3", "一等座:20");
        row1.put("seat4", "商务座:200");
        data.add(row1);

        Map<String, Object> row2 = new HashMap<String, Object>();
        row2.put("trainNo", "D1");
        row2.put("flg1", R.drawable.flg_shi);
        row2.put("flg2", R.drawable.flg_zhong);
        row2.put("timeFrom", "09:00");
        row2.put("timeTo", "12:39(0日)");
        row2.put("seat1", "高级软卧:100");
        row2.put("seat2", "硬座:8");
        row2.put("seat3", "一等座:20");
        row2.put("seat4", "商务座:200");
        data.add(row2);

        Map<String, Object> row3 = new HashMap<String, Object>();
        row3.put("trainNo", "K7777");
        row3.put("flg1", R.drawable.flg_guo);
        row3.put("flg2", R.drawable.flg_guo);
        row3.put("timeFrom", "15:00");
        row3.put("timeTo", "12:39(1日)");
        row3.put("seat1", "高级软卧:55");
        row3.put("seat2", "硬座:77");
        row3.put("seat3", "一等座:33");
        data.add(row3);
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item_ticket_result_step1,
                new String[]{"trainNo",
                        "flg1", "flg2", "timeFrom", "timeTo", "seat1", "seat2",
                        "seat3", "seat4"}, new int[]{
                R.id.tvTicketResultStep1TrainNo,
                R.id.imgTicketResultStep1Flg1,
                R.id.imgTicketResultStep1Flg2,
                R.id.tvTicketResultStep1TimeFrom,
                R.id.tvTicketResultStep1TimeTo,
                R.id.tvTicketResultStep1Seat1,
                R.id.tvTicketResultStep1Seat2,
                R.id.tvTicketResultStep1Seat3,
                R.id.tvTicketResultStep1Seat4,});
        lvTicketResultStep1.setAdapter(adapter);
        lvTicketResultStep1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(TicketStep1Activity.this, TicketStep2Activity.class);
                startActivity(intent);

            }
        });

    }

    private class HandlerTicketResultStep1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Calendar c = Calendar.getInstance();
            //获取选中日期
            String oldDateFrom = tvTicketResultStep1DateTitle.getText().toString();
            int oldYear = Integer
                    .parseInt(oldDateFrom.split(" ")[0].split("-")[0]);
            int oldMonthOfYear = Integer.parseInt(oldDateFrom.split(" ")[0]
                    .split("-")[1]) - 1;
            int oldDayOfMonth = Integer.parseInt(oldDateFrom.split(" ")[0]
                    .split("-")[2]);
            c.set(oldYear, oldMonthOfYear, oldDayOfMonth);
            switch (v.getId()) {
                case R.id.tvTicket_before:
                    c.add(Calendar.DAY_OF_MONTH, -1);
                    break;
                case R.id.tvTicket_after:
                    c.add(Calendar.DAY_OF_MONTH, +1);
                    break;
            }
            //更新选中日期
            String weekDay = DateUtils.formatDateTime(TicketStep1Activity.this, c.getTimeInMillis(), DateUtils.FORMAT_SHOW_WEEKDAY
                    | DateUtils.FORMAT_ABBREV_ALL);
            tvTicketResultStep1DateTitle.setText(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" +
                    c.get(Calendar.DAY_OF_MONTH) + " " + weekDay);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ticket_step1,menu);
        return true;
    }
}

