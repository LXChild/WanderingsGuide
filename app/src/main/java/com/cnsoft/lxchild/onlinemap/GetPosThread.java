package com.cnsoft.lxchild.onlinemap;

import android.content.Context;

import com.cnsoft.lxchild.algorithm.Algorithm;
import com.cnsoft.lxchild.search.SearchActivity;

/**
 * Created by LXChild on 2015/4/11.
 */
public class GetPosThread extends Thread {
    private String TAG = GetPosThread.class.getSimpleName();
    private Context cxt;

    private boolean flag = false;
    private int sleepSpan = 40;
    private double time_current;

    private OLFragment olFragment;
    private Movable mv;
    private DrawThread dt;


    public GetPosThread(Context cxt, OLFragment olFragment, Movable m, DrawThread dt) {
        this.cxt = cxt;
        this.olFragment = olFragment;
        this.mv = m;
        this.dt = dt;
    }

    @Override
    public void run() {
        while (flag) {
            if (olFragment.getLocationTask() != null) {
                synchronized ("s") {
                    time_current = System.nanoTime();
                    Double[] pos = olFragment.getLocationTask().getPos();
                    if (pos != null && pos.length > 0) {
                        //Log.d(TAG, pos.length + "");
                        mv.current_x = pos[0];
                        mv.current_y = pos[1];
                        mv.current_z = pos[2];
                        dt.setPos(pos);
                        Algorithm.setPos(pos);
                        Path.setPos(pos);
                        MapBitmap.setPos(pos);
                        SearchActivity.setPos(pos);

                        OLFragment.setPos(pos);
                        //Log.d(TAG, Arrays.toString(pos));
                    }
                }
            }
            try {
                Thread.sleep(sleepSpan);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
