package com.cnsoft.lxchild.airportservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import java.lang.String;


import com.cnsoft.lxchild.main.R;

import java.util.Calendar;

/**
 * Created by LXChild on 2015/4/13.
 */
public class ServiceFragment extends Fragment {
    Button button;
    AlarmManager alarmManager;
    private String TAG = ServiceFragment.class.getSimpleName();
//    private MapView map;
//    private Algorithm algorithm;
//    private int[] source;
//    private int[] target;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        map = new MapView(container.getContext());
//        algorithm = new Algorithm();
//
//        source = new int[] {2, 12};
//        target = new int[] {5, 5};
//
//        algorithm.mapView = this.map;
//        map.algorithm = this.algorithm;
//        map.getSourceAndTarget(source, target);
//        algorithm.runAlgorithm();

//        View rootView = inflater.inflate(R.layout.fragment_airportservice, container, false);
        //       return map;
        View view=inflater.inflate(R.layout.fragment_airportservice,container, false);
        button=(Button)view.findViewById(R.id.button1);
        alarmManager = (AlarmManager) getActivity().getSystemService(Service.ALARM_SERVICE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                //创建一个TimePickerDialog实例，并显示
                new TimePickerDialog(getActivity(), 0,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //指定启动AlarmActivity组件
                                Intent intent = new Intent(getActivity(),AlarmActivity.class);

                                //intent.setClass(getActivity(),AlarmActivity.class);
                                //创建PendingIntent对象
								/*
								 * PendingIntent与Intent的区别是PendingIntent处理即将发生的事情
								 * 比如：在通知栏Notification中跳转页面，不是立即跳转
								 * 通常通过  getActivity、getBroadcast、getService得到PendingIntent的实例
								 *
								 */
                                PendingIntent pi = PendingIntent.getActivity(getActivity(), 0, intent, 0);
                                Calendar c = Calendar.getInstance();
                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);

                                alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
                                Toast.makeText(getActivity(), "取车提醒设置成功", Toast.LENGTH_SHORT).show();
                            }
                        }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime.get(Calendar.MINUTE), false).show();
            }
        });





        return view;
    }
}
