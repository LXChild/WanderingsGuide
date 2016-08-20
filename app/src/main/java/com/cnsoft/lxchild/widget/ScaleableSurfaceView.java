package com.cnsoft.lxchild.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.cnsoft.lxchild.main.R;

/**
 * Created by LXChild on 2015/5/3.
 */
public class ScaleableSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private final String TAG = ScaleableSurfaceView.class.getSimpleName();

    public String fps = "FPS:N/A";//用于显示帧速率的字符串，初始时为：“FPS：N/A”

    private Bitmap[] bmpMapAry = new Bitmap[3];
    private Bitmap bmpPos;

    private Paint paint;

    public ScaleableSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        initPaint();
        initBitmap(getResources());
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setTextSize(18);
        paint.setAntiAlias(true);
    }

    private void initBitmap(Resources resources){
        bmpMapAry[0] = BitmapFactory.decodeResource(resources, R.drawable.map_b1l);//楼下一层的Bitmap
        bmpMapAry[1] = BitmapFactory.decodeResource(resources, R.drawable.map_f1l);//楼上一层的Bitmap
        bmpMapAry[2] = BitmapFactory.decodeResource(resources, R.drawable.map_f2l);//楼上二层的Bitmap

        bmpPos = BitmapFactory.decodeResource(resources, R.drawable.position);//位置图标的Bitmap
    }

    private void initMovable() {
    }

    public void doDraw(Canvas cvs) {
        cvs.drawText(fps, 30, 30, paint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }
}
