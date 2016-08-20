package com.cnsoft.lxchild.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cnsoft.lxchild.main.R;

public class AlterLocInterfaceActivity extends ActionBarActivity implements TextWatcher {

    private TextView tv_intfc;
    private EditText et_ip, et_port, et_loc;

    private SharedPreferences sp;
    private String TAG = "AlterLocInterfaceActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_settings_altlocifs);
        tv_intfc = (TextView) findViewById(R.id.settings_tv_intfc);
        et_ip = (EditText) findViewById(R.id.settings_et_altip);
        et_ip.addTextChangedListener(this);
        et_port = (EditText) findViewById(R.id.settings_et_altport);
        et_port.addTextChangedListener(this);
        et_loc = (EditText) findViewById(R.id.settings_et_altloc);
        et_loc.addTextChangedListener(this);
        Button btn_confirm = (Button) findViewById(R.id.settings_btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                finish();
            }
        });
        Button btn_cancel = (Button) findViewById(R.id.settings_btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button btn_changewifi = (Button) findViewById(R.id.settings_btn_changewifi);
        btn_changewifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseWifi();
            }
        });
    }

    private void initData() {
        String PREFS_NAME = "LocationInterface";
        sp = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        et_ip.setText(sp.getString("IP", "192.168.1.1"));
        et_port.setText(String.valueOf(sp.getInt("Port", 80)));
        et_loc.setText(String.valueOf(sp.getString("Loc", "loc")));
        showConnectInfo();
    }

    private void saveData() {
        SharedPreferences.Editor sp_editor = sp.edit();
        sp_editor.putString("IP", et_ip.getText().toString().trim());
        sp_editor.putInt("Port", Integer.valueOf(et_port.getText().toString().trim()));
        sp_editor.putString("Loc", et_loc.getText().toString().trim());
        sp_editor.apply();
    }

    private void showConnectInfo() {
        tv_intfc.setText("当前连接信息\n\n" + "Wifi名称： " + getWifiId() + "\n接口：" + "http://" + et_ip.getText().toString().trim() + ":" + et_port.getText().toString().trim() + "/" + et_loc.getText().toString().trim());
    }

    private String getWifiId() {
        WifiManager wifiMgr = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiMgr.getConnectionInfo();
        return info != null ? info.getSSID() : null;
    }

    private void chooseWifi() {
        Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
        startActivity(intent);
    }

//    @Override
//    protected void onPause() {
//        Log.d(TAG, "onPause");
//        saveData();
//        super.onPause();
//    }
//
    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        showConnectInfo();
        super.onResume();
    }
//
//    @Override
//    protected void onRestart() {
//        Log.d(TAG, "onRestart");
//        showConnectInfo();
//        super.onRestart();
//    }
//
//    @Override
//    protected void onStop() {
//        Log.d(TAG, "onStop");
//        super.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        Log.d(TAG, "onDestroy");
//        super.onDestroy();
//    }

    @Override 
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_alter_loc_interface, menu);
        showActionBar();
        return true;
    }

    public void showActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        showConnectInfo();
    }
}
