package com.cnsoft.lxchild.onlinemap;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cnsoft.lxchild.algorithm.Algorithm;
import com.cnsoft.lxchild.locationservice.LocationTask;
import com.cnsoft.lxchild.main.R;
import com.cnsoft.lxchild.shake.AccelerometerListener;
import com.cnsoft.lxchild.shake.AccelerometerManager;
import com.cnsoft.lxchild.widget.ScaleableImageView;

/**
 * Created by LXChild on 2015/4/8.
 */
public class OLFragment extends Fragment implements AccelerometerListener {
    private String TAG = OLFragment.class.getSimpleName();
    private Context cxt;

    private LocationTask locationTask;
    private ScaleableImageView iv_scale;
    private MapBitmap mapBitmap;
    private static Double[] pos;

    public static void setPos(Double[] pos) {
        OLFragment.pos = pos;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (iv_scale != null) {
                iv_scale.setImageBitmap((Bitmap) msg.obj);
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        this.cxt = activity;
        runLocation();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mapBitmap = new MapBitmap(container.getContext(), this, handler);

        View rootView = inflater.inflate(R.layout.fragment_olmap, container, false);
        iv_scale = (ScaleableImageView) rootView.findViewById(R.id.iv_scale);
        return rootView;
    }

    public LocationTask getLocationTask() {
        return locationTask;
    }

    @Override
    public void onResume() {
        runLocation();
        super.onResume();

        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isSupported(getActivity())) {

            //Start Accelerometer Listening
            AccelerometerManager.startListening(this);
        }
    }

    /**
     * 获取位置信息
     */
    private void runLocation() {
        if (locationTask == null || locationTask.isCancelled()) {
            String PREFS_NAME = "LocationInterface";
            SharedPreferences sp = cxt.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            String loc_url = "http://" + sp.getString("IP", "192.168.1.1") + ":" + sp.getInt("Port", 80) + "/" + sp.getString("Loc", "loc");
            locationTask = new LocationTask(cxt);
            locationTask.setFlag(true);
            locationTask.execute(loc_url);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (locationTask != null && !locationTask.isCancelled()) {
            locationTask.setFlag(false);
            locationTask.cancel(true);
        }
    }

    @Override
    public void onDetach() {
        if (locationTask != null && !locationTask.isCancelled()) {
            locationTask.setFlag(false);
            locationTask.cancel(true);
        }
        if (mapBitmap != null) {
            mapBitmap.release();
        }
        super.onDetach();
    }

    public void onAccelerationChanged(float x, float y, float z) {
        // TODO Auto-generated method stub
        if((x>5||x<-5)&&(y>8||y<-8)) {

            Algorithm.source[0]=22;
            Algorithm.source[1]=41;

            if(Algorithm.target[0]==0){
                Toast.makeText(getActivity(),"您并没有在该停车场存放车辆",Toast.LENGTH_LONG).show();
            }else {


                Path.algorithm.runAlgorithm();
            }
        }
    }
    public void onShake(float force) {
        // TODO Auto-generated method stub

        //Toast.makeText(getBaseContext(), "Motion detected", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onStop() {
        super.onStop();

        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isListening()) {

            //Start Accelerometer Listening
            AccelerometerManager.stopListening();

            // Toast.makeText(getBaseContext(), "onStop Accelerometer Stoped", Toast.LENGTH_SHORT).show();
        }
    }
}
