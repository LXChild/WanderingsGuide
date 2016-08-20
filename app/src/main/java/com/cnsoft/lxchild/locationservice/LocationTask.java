package com.cnsoft.lxchild.locationservice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cnsoft.lxchild.settings.AlterLocInterfaceActivity;
import com.cnsoft.lxchild.util.MyWifiManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

/**
 * Created by LXChild on 2015/3/23.
 */
public class LocationTask extends AsyncTask<String, Double, String> {

    private String TAG = LocationTask.class.getSimpleName();
    private Context cxt;
    private boolean flag;

    private Double[] pos;

    private MyWifiManager wifiManager;

    public LocationTask(Context cxt) {
        this.cxt = cxt;
    }

    @Override
    protected void onPreExecute() {
        // 任务启动，可以在这里显示一个对话框，这里简单处理
        super.onPreExecute();

        wifiManager = new MyWifiManager(cxt);
        /**如果Wifi没有打开，则显示打开wifi对话框*/
        if (!wifiManager.isWifiOpened()) {
            flag = false;
            this.cancel(true);
            showWifiDialog();
        } else {
            // Toast.makeText(cxt, "Location Mission Start.....", Toast.LENGTH_SHORT).show();//提示任务结束
        }
    }

    @Override
    protected String doInBackground(String... params) {

        String result;
        try {
            while (flag) {
                result = runHttpGet(params[0]);
                if (result != null) {
                    Double[] pos = parseJSON(result);
                    publishProgress(pos);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    publishProgress();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //     return "LocationTask has Stoped.";
        return null;
    }

    @Override
    protected void onProgressUpdate(Double... values) {
        if (values != null && values.length > 0) {
            pos = values;
        } else {
            flag = false;
            this.cancel(true);
            showAltSetDialog();
        }

        super.onProgressUpdate(values);
    }

    public Double[] getPos() {
        return pos;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // Toast.makeText(cxt, s, Toast.LENGTH_SHORT).show();//提示任务结束

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        //  Toast.makeText(cxt, "Location Mission Canceled!", Toast.LENGTH_SHORT).show();//提示任务取消
    }

    private String runHttpGet(String loc_url) {
        //发送请求
        try {
            HttpGet httpGet = new HttpGet(loc_url);
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                //返回读到的数据
                return EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析获取到的位置数据
     */
    private Double[] parseJSON(String json_data) {
        if (!json_data.trim().equals("")) {
            Double[] pos = new Double[3];
            try {
                JSONArray jsonArray = new JSONArray(json_data);

                for (int i = 0; i < jsonArray.length(); i++) {
                    pos[i] = (Double) jsonArray.get(i);
                }

            } catch (Exception e) {
                Toast.makeText(cxt, e.toString(), Toast.LENGTH_SHORT).show();
            }
            return pos;
        }
        return null;
    }

    /**
     * 显示未连接Wifi对话框
     */
    private void showWifiDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(cxt);
        builder.setTitle("Tip");
        builder.setMessage("未打开Wifi,是否打开连接？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                wifiManager.toggleWiFi(cxt, true);
                if (!wifiManager.isWifiConnected()) {
                    wifiManager.chooseWifi();
                }
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    /**
     * 显示Wifi连接异常对话框
     */
    private void showAltSetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(cxt);
        builder.setTitle("Tip");
        builder.setMessage("网络连接异常，是否进行设置？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setClass(cxt, AlterLocInterfaceActivity.class);
                cxt.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }
}
