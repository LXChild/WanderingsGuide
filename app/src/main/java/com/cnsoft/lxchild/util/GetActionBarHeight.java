package com.cnsoft.lxchild.util;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by LXChild on 2015/4/7.
 */
public class GetActionBarHeight {

    private float actionBarHeight;
    public GetActionBarHeight(Context cxt) {
        TypedArray actionbarSizeTypedArray = cxt.obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });

        actionBarHeight = actionbarSizeTypedArray.getDimension(0, 0);
    }
    public float getActionBarHeight() {
        return actionBarHeight;
    }
}
