package com.cnsoft.lxchild.onlinemap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;

import com.cnsoft.lxchild.algorithm.Algorithm;


/**
 * Created by LXChild on 2015/4/11.
 */
public class MapBitmap {

    public static boolean canDrawPath = false;
    public static Algorithm algorithm;
    private static Double[] pos;
    public String fps = "FPS:N/A";//用于显示帧速率的字符串，初始时为：“FPS：N/A”
    public DrawThread dt;
    private String TAG = MapBitmap.class.getSimpleName();
    private OLFragment olFragment;
    private Path path;
    private Movable m;
    private Paint paint;
    private GetPosThread gpt;

    public MapBitmap(Context cxt, OLFragment olFragment, Handler handler) {
        this.olFragment = olFragment;

        initPath(cxt);
        initMovable(cxt);
        initPaint();

        initThread(cxt, handler);
        algorithm = new Algorithm();
    }

    public static void setPos(Double[] pos) {
        MapBitmap.pos = pos;
    }

    private void initThread(Context cxt, Handler handler) {

        if (dt == null) {
            dt = new DrawThread(this, handler, cxt);
        }
        if (gpt == null) {
            gpt = new GetPosThread(cxt, olFragment, m, dt);
        }
        if (!gpt.isAlive()) {
            gpt.setFlag(true);
            gpt.start();
        }
        if (!dt.isAlive()) {
            dt.setFlag(true);
            dt.start();
        }
    }


    private void initPath(Context cxt) {
        path = new Path(cxt);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setTextSize(18);
        paint.setAntiAlias(true);
    }

    private void initMovable(Context cxt) {
        m = new Movable(cxt);
    }

    public void doDraw(Canvas cvs) {
        m.clearCanvas(cvs);
        m.drawMap(cvs);
        m.drawSelf(cvs);

        if (canDrawPath) {
            path.drawPath(cvs);
        }

        cvs.drawText(fps, 30, 30, paint);
    }

    public void release() {
        if (gpt != null) {
            gpt.setFlag(false);
            gpt = null;
        }
        if (dt != null) {
            dt.setFlag(false);
            dt = null;
        }
    }
}
