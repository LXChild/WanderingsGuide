package com.cnsoft.lxchild.util;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * Created by LXChild on 2015/3/29.
 */
public class GetStatusBarHeight {

    private int statusBarHeight;

    //获取状态栏高度
    public GetStatusBarHeight(Context cxt) {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = cxt.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getStatusBarHeight() {
        return statusBarHeight;
    }
}
