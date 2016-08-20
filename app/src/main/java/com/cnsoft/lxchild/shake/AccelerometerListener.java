package com.cnsoft.lxchild.shake;

/**
 * Created by Ray on 2015/5/19.
 */
public interface AccelerometerListener {
    public void onAccelerationChanged(float x, float y, float z);

    public void onShake(float force);

}
