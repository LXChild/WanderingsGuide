package com.cnsoft.lxchild.onlinemap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.cnsoft.lxchild.algorithm.Algorithm;

import java.util.HashMap;

/**
 * Created by LXChild on 2015/4/15.
 */
public class Path {
    public static Algorithm algorithm;
    private static Double[] pos;
    private String TAG = Path.class.getSimpleName();
    private Paint paint;
    private int span = 27;
    private Bitmap bmp_source;
    private Bitmap bmp_target;


    public Path(Context cxt) {

        bmp_source = BitmapFactory.decodeResource(cxt.getResources(), com.cnsoft.lxchild.main.R.drawable.source);
        bmp_target = BitmapFactory.decodeResource(cxt.getResources(), com.cnsoft.lxchild.main.R.drawable.target);

        paint = new Paint();
        algorithm = new Algorithm();
    }

    public static void setPos(Double[] pos) {
        Path.pos = pos;
    }

    public void drawPath(Canvas cvs) {
        if(Algorithm.pathFlag){
            HashMap<String,int[][]> hm=algorithm.hm;
            int[] temp= Algorithm.target;
            while(true){
                int[][] tempA=hm.get(temp[0]+":"+temp[1]);
                paint.setColor(Color.GREEN);
                paint.setStyle(Paint.Style.STROKE);//加粗
                paint.setStrokeWidth(8);//设置画笔粗度为2px
                cvs.drawLine(
                        tempA[0][0]*(span+1)+span/2+6,tempA[0][1]*(span+1)+span/2+6,
                        tempA[1][0]*(span+1)+span/2+6,tempA[1][1]*(span+1)+span/2+6,
                        paint
                );

                if(tempA[1][0]== Algorithm.source[0]&&tempA[1][1]== Algorithm.source[1]){//判断有否到出发点
                    break;
                }
                temp=tempA[1];
            }

        }

        //绘制出发点
        cvs.drawBitmap(bmp_source, 6 + algorithm.source[0] * (span + 1) - bmp_source.getWidth() / 2 + 3, 6 + algorithm.source[1] * (span + 1) - bmp_source.getHeight() + 3, paint);
        //绘制目标点
        cvs.drawBitmap(bmp_target, 6 + algorithm.target[0] * (span + 1) - bmp_target.getWidth() / 2, 6 + algorithm.target[1] * (span + 1) - bmp_target.getHeight(), paint);


    }


    public void drawTarget(Canvas cvs, int map_w, int map_h) {
//        Log.d(TAG, "Run DrawTarget:" + (game.target[0] - bmp_target.getWidth() / 2) + "," + (game.target[1] - bmp_target.getHeight()));
        //       cvs.drawBitmap(bmp_target, game.target[0] - bmp_target.getWidth() / 2, game.target[1] - bmp_target.getHeight(), null);
//        ScaleableImageView.isLongPressed = false;
//        ScaleableImageView.longPressPoint[0] = 0;
//        ScaleableImageView.longPressPoint[1] = 0;
    }
}
