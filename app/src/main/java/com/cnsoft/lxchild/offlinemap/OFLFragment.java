package com.cnsoft.lxchild.offlinemap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cnsoft.lxchild.algorithm.Algorithm;
import com.cnsoft.lxchild.main.R;
import com.cnsoft.lxchild.onlinemap.*;
import com.cnsoft.lxchild.onlinemap.MapBitmap;
import com.cnsoft.lxchild.shake.AccelerometerListener;
import com.cnsoft.lxchild.shake.AccelerometerManager;
import com.cnsoft.lxchild.widget.ScaleableImageView;
import com.cnsoft.lxchild.widget.VerticalViewPager;

/**
 * Created by LXChild on 2015/4/11.
 */
public class OFLFragment extends Fragment implements AccelerometerListener {



    private Context cxt;


    public void onAttach(Activity activity) {

        super.onAttach(activity);
        this.cxt = activity;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        VerticalViewPager vp_maps = (VerticalViewPager) rootView.findViewById(R.id.vp_maps);
        vp_maps.setAdapter(new OFLVVPAdapter());
        return rootView;
    }
    public void onResume() {

        super.onResume();

        super.onResume();
        //Toast.makeText(getBaseContext(), "onResume Accelerometer Started", Toast.LENGTH_SHORT).show();

        //Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isSupported(getActivity())) {

            //Start Accelerometer Listening
            AccelerometerManager.startListening(this);
        }
    }
    public void onAccelerationChanged(float x, float y, float z) {
        // TODO Auto-generated method stub
        if((x>5||x<-5)&&(y>8||y<-8)) {
            Toast.makeText(getActivity(), "反向寻车", Toast.LENGTH_SHORT).show();
            Algorithm.target[0] =16;
            Algorithm.target[1] =36;

            Algorithm.source[0] =22;
            Algorithm.source[1] =41;



           Path.algorithm.runAlgorithm();
        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
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
