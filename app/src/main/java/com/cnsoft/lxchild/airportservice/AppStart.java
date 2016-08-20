package com.cnsoft.lxchild.airportservice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;

public class AppStart extends Activity{
	public static final String PACKAGE_NAME = "com.example.alarmtest";
	public static final String VERSION_KEY = "versionCode";
	SharedPreferences preferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);


		//判断是否是首次安装
		/** 判断应用首次运行 **/
		
		preferences = getSharedPreferences("count",MODE_WORLD_READABLE);

        int count = preferences.getInt("start_count", 0);
        if(count == 0){
        	Editor editor = preferences.edit();
			//存入数据
            editor.putInt("start_count", ++count);
			//提交修改
            editor.commit();
            
			
			
			AppStart.this.finish();
			
		}else{
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			LinearLayout linearLayout = new LinearLayout(this);
			linearLayout.setLayoutParams(params);
			linearLayout.setOrientation(LinearLayout.VERTICAL);
			//linearLayout.setBackgroundResource(R.drawable.main_bg_default_img_2);
			
			new Handler().postDelayed(new Runnable(){
				@Override
				public void run(){
					Intent intent = new Intent (AppStart.this, ServiceFragment.class);
					startActivity(intent);			
					AppStart.this.finish();
				}
			}, 0);
			setContentView(linearLayout);
		}		
	}
}
