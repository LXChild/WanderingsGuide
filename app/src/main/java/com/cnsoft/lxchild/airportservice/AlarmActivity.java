package com.cnsoft.lxchild.airportservice;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.cnsoft.lxchild.main.R;

public class AlarmActivity extends Activity{
	MediaPlayer alarmMusic;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//加载指定音乐，并为之创建MediaPlayer对象
		alarmMusic = MediaPlayer.create(this,R.raw.mm);
		alarmMusic.setLooping(true);
		//播放闹钟
		alarmMusic.start();
		//创建一个对话框
		new AlertDialog.Builder(AlarmActivity.this).setTitle("取车提醒")
			.setMessage("停车时间到了")
			.setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//停止音乐
					alarmMusic.stop();
					AlarmActivity.this.finish();
				}
			}).show();
	}
}
