package com.neusoft.my12603;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.neusoft.my12603.stationlist.StationListActivity;
import com.neusoft.my12603.ticket.TicketStep1Activity;

import java.util.Calendar;

/**
 * Created by 明星 on 2016/9/9.
 */
public class TicketFragment extends android.support.v4.app.Fragment{

    private TextView tvTicketStationFrom;
    private TextView tvTicketStationTo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       return inflater.inflate(R.layout.ticker_fargmet,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }
    private void initView() {
        tvTicketStationFrom = (TextView) getActivity().findViewById(R.id.tvTicketStationFrom);
        tvTicketStationTo = (TextView) getActivity().findViewById(R.id.tvTicketStationTo);
        ImageView imgTicketExchange= (ImageView) getActivity().findViewById(R.id.imgTicketExchange);
        final TextView tvTicketDateFrom= (TextView) getActivity().findViewById(R.id.tvTicketDateFrom);
        Button btnTicketQuery= (Button) getActivity().findViewById(R.id.btnTicketQuery);
        tvTicketStationFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),StationListActivity.class);
                startActivityForResult(intent,100);
            }
        });
        tvTicketStationTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),StationListActivity.class);
                startActivityForResult(intent,101);
            }
        });
        imgTicketExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String stationFrom= tvTicketStationFrom.getText().toString();
                final String stationTo= tvTicketStationTo.getText().toString();
                final TranslateAnimation animationFrom=new TranslateAnimation(0,150,0,0);
                animationFrom.setInterpolator(new AccelerateInterpolator());
                animationFrom.setDuration(300);
                animationFrom.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        tvTicketStationTo.setText(stationFrom);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                });
                TranslateAnimation animationTo=new TranslateAnimation(0,-150,0,0);
                animationTo.setInterpolator(new AccelerateInterpolator());
                animationTo.setDuration(300);
                animationTo.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        tvTicketStationFrom.setText(stationTo);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }


                });
                //TextView移动
                tvTicketStationFrom.startAnimation(animationFrom);
                tvTicketStationTo.startAnimation(animationTo);

            }
        });

        tvTicketDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldDataFrom=tvTicketDateFrom.getText().toString();
                int oldYear=Integer.parseInt(oldDataFrom.split(" ")[0].split("-")[0]);
                int oldMonthOfYear=Integer.parseInt(oldDataFrom.split(" ")[0].split("-")[1])-1;
                int oldDayOfMouth=Integer.parseInt(oldDataFrom.split(" ")[0].split("-")[2]);
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    //重写该函数
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar c2=Calendar.getInstance();
                        c2.set(year,monthOfYear,dayOfMonth);
                        String weekDay= DateUtils.formatDateTime(getActivity(),c2.getTimeInMillis(),
                                DateUtils.FORMAT_SHOW_WEEKDAY|DateUtils.FORMAT_ABBREV_ALL);
                        tvTicketDateFrom.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth+" "+weekDay);

                    }
                },oldYear,oldMonthOfYear,oldDayOfMouth).show();
            }
        });
        btnTicketQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),TicketStep1Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String name=data.getStringExtra("name");
        if (!TextUtils.isEmpty(name)){
            switch (requestCode){
                case 100:
                    tvTicketStationFrom.setText(name);
                    break;
                case 101:
                    tvTicketStationTo.setText(name);
                    break;
            }
        }
    }
}
