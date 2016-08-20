package com.cnsoft.lxchild.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.cnsoft.lxchild.main.R;

public class SettingsFragment extends Fragment implements TextWatcher {

    private String TAG = SettingsFragment.class.getSimpleName();
//    private ListView lv_options;
private TextView tv_intfc;
    private EditText et_ip, et_port, et_loc;

    private SharedPreferences sp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_settings_altlocifs, container, false);
//        lv_options = (ListView) rootView.findViewById(R.id.settingsfrg_lv_option);
//        lv_options.setAdapter(new SettingsFrg_lv_adapter(container.getContext()));
//        lv_options.setOnItemClickListener(this);
        // Inflate the layout for this fragment

        tv_intfc = (TextView) rootView.findViewById(R.id.settings_tv_intfc);
        et_ip = (EditText) rootView.findViewById(R.id.settings_et_altip);
        et_ip.addTextChangedListener(this);
        et_port = (EditText) rootView.findViewById(R.id.settings_et_altport);
        et_port.addTextChangedListener(this);
        et_loc = (EditText) rootView.findViewById(R.id.settings_et_altloc);
        et_loc.addTextChangedListener(this);
        Button btn_confirm = (Button) rootView.findViewById(R.id.settings_btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
               // finish();
            }
        });
        Button btn_cancel = (Button) rootView.findViewById(R.id.settings_btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // finish();
            }
        });
        Button btn_changewifi = (Button) rootView.findViewById(R.id.settings_btn_changewifi);
        btn_changewifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseWifi();
            }
        });

        String PREFS_NAME = "LocationInterface";
        sp = this.getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        et_ip.setText(sp.getString("IP", "192.168.1.1"));
        et_port.setText(String.valueOf(sp.getInt("Port", 80)));
        et_loc.setText(String.valueOf(sp.getString("Loc", "loc")));
        showConnectInfo();
        return rootView;
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        switch (position) {
//            case 0:
//                Intent intent = new Intent();
//                intent.setClass(this.getActivity(), AlterLocInterfaceActivity.class);
//                startActivity(intent);
//                break;
//            case 1:
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            case 5:
//                break;
//            default:
//                break;
//        }
//    }

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
        WifiManager wifiMgr = (WifiManager) this.getActivity().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiMgr.getConnectionInfo();
        return info != null ? info.getSSID() : null;
    }

    private void chooseWifi() {
        Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        showConnectInfo();
        super.onResume();
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
