package com.cnsoft.lxchild.onlinemap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;

import com.cnsoft.lxchild.algorithm.Algorithm;
import com.cnsoft.lxchild.main.R;

/**
 * Created by LXChild on 2015/4/11.
 */
public class DrawThread extends Thread {
    private String TAG = DrawThread.class.getSimpleName();
    private Context cxt;
    private boolean flag = false;//线程执行标志位

    private Double[] pos;

    private int sleepSpan = 30;
    private long start = System.nanoTime();//记录起始时间，改变辆用于计算帧速率
    private int count = 0;//记录帧数，改变辆用于计算帧速率
    private MapBitmap mapBitmap;
    private Bitmap bmp_src;
    private Bitmap bmp_buffer;
    private Canvas cvs=null;

    private double[] map_pre = new double[3];

    private Handler handler;

    private int[] res_maps = new int[]{R.drawable.map_1, R.drawable.map_1, R.drawable.map_1};

    public DrawThread(MapBitmap mapBitmap, Handler handler, Context cxt) {
        this.mapBitmap = mapBitmap;
        for (int i = 0; i < map_pre.length; i++) {
            map_pre[i] = 5;
        }
        this.handler = handler;
        this.cxt = cxt;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setPos(Double[] pos) {
        this.pos = pos;
    }

    private void useDoubleBuffer() {
        Canvas cvs = new Canvas();
        Paint paint = new Paint();
        try {
            recycleBitmap(bmp_src);
            recycleBitmap(bmp_buffer);
            /**加载资源*/
            //bmp_src = BitmapFactory.decodeResource(cxt.getResources(), res_maps[pos[2].intValue()]);
            bmp_src=BitmapFactory.decodeResource(cxt.getResources(),R.drawable.map_1);
            /**创建图片大小的缓冲区*/
            bmp_buffer = Bitmap.createBitmap(bmp_src.getWidth(), bmp_src.getHeight(), Bitmap.Config.ARGB_8888);
            /**设置将bmp_src绘制在bmp_buffer上*/
            cvs.setBitmap(bmp_buffer);
            /**将bmp_src绘制在bmp_buffer上*/
            cvs.drawBitmap(bmp_src, 0, 0, paint);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        Canvas cvs = null;
        while (flag) {
           if (pos != null) {
                try {
//                if (mapBitmap.pos[2] != map_pre[2]) {
//                    try {
//                    //    bmp = BitmapFactory.decodeResource(cxt.getResources(), res_maps[((int) mapBitmap.pos[2])]).copy(Bitmap.Config.ARGB_8888, true);
//                        if (bmp == null) {
//                            // 如果实例化失败 返回默认的Bitmap对象
//                            bmp = BitmapFactory.decodeResource(cxt.getResources(), res_maps[0]).copy(Bitmap.Config.ARGB_8888, true);
//                        }
//                    } catch (OutOfMemoryError e) {
//                        e.printStackTrace();
//                    }
//                    map_pre[2] = mapBitmap.pos[2];
//                }
                    if (pos[2] != map_pre[2]) {
                        useDoubleBuffer();
                        cvs = new Canvas(bmp_buffer);
                        map_pre[2] = pos[2];
                    }

                        if (cvs != null) {
                            mapBitmap.doDraw(cvs);
                            Message msg = handler.obtainMessage();
                            msg.obj = bmp_buffer;
                            handler.sendMessage(msg);
                        }
                        map_pre[0] = pos[0];
                        map_pre[1] = pos[1];


                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
                calculateFPS();
            }
            try {
                Thread.sleep(sleepSpan);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void recycleBitmap(Bitmap bmp) {
        // 先判断是否已经回收
        if (bmp != null && !bmp.isRecycled()) {
            // 回收并且置为null
            bmp.recycle();
        }
        System.gc();
    }

    private void calculateFPS() {
        count++;
        if (count == 20) {
            count = 0;
            long tempStamp = System.nanoTime();//获取对当前时间
            long span = tempStamp - start;//获取当前时间间隔
            start = tempStamp;
            double fps = Math.round(100000000000.0 / span * 20) / 100;
            mapBitmap.fps = "FPS:" + fps;
        }
    }
}
