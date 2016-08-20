package com.cnsoft.lxchild.onlinemap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import com.cnsoft.lxchild.main.R;

/**
 * Created by LXChild on 2015/4/11.
 */
public class Movable {

    public double current_x;
    public double current_y;
    public double current_z;
    public double time;
    private String TAG = Movable.class.getSimpleName();
    private double start_x;
    private double start_y;
    /** 位置图标的Bitmap*/
    private Bitmap bmp_pos;
    /**位置图标的宽和高*/
    private int pos_w_half, pos_h;
    /** 地图的bitmap数组*/
    private Bitmap[] bmpMapAry = new Bitmap[3];

    public Movable(Context cxt) {
        initBitmap(cxt.getResources());
        initData();
    }

    private void initData() {
        this.start_x = this.current_x;
        this.start_y = this.current_y;

        this.time = System.nanoTime();
    }

    private void initBitmap(Resources resources) {
        try {
            bmp_pos = BitmapFactory.decodeResource(resources, R.drawable.position);
            pos_w_half = this.bmp_pos.getWidth() / 2;
            pos_h = this.bmp_pos.getHeight();
            bmpMapAry[0] = BitmapFactory.decodeResource(resources, R.drawable.map_1);//楼上二层的Bitmap
            bmpMapAry[1] = BitmapFactory.decodeResource(resources, R.drawable.map_1);//楼上一层的Bitmap
            bmpMapAry[2] = BitmapFactory.decodeResource(resources, R.drawable.map_1);//楼下一层的Bitmap
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }

    public void clearCanvas(Canvas cvs) {
        cvs.drawColor(Color.WHITE);
    }

    public void drawMap(Canvas cvs) {
        cvs.drawBitmap(bmpMapAry[(int) current_z], 0, 0, null);
    }

    public void drawSelf(Canvas cvs) {
        current_x *= bmpMapAry[(int) current_z].getWidth();
        if (bmpMapAry[(int) current_z].getHeight() < 500) {
            current_y = bmpMapAry[(int) current_z].getHeight() - (current_y + 0.6) * bmpMapAry[(int) current_z].getHeight();
            current_y /= 0.4;
        } else {
            current_y = bmpMapAry[(int) current_z].getHeight() - current_y * bmpMapAry[(int) current_z].getHeight();//计算出相对于地图的位置，并转换为以左下角为原点的坐标系内
        }
        cvs.drawBitmap(this.bmp_pos, ((int) current_x - pos_w_half), ((int) current_y - pos_h), null);
    }
}
