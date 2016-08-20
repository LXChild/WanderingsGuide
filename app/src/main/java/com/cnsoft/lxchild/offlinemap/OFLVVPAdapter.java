package com.cnsoft.lxchild.offlinemap;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cnsoft.lxchild.main.R;
import com.cnsoft.lxchild.widget.ScaleableImageView;
import com.cnsoft.lxchild.widget.VerticalPagerAdapter;

/**
 * Created by LXChild on 2015/4/11.
 */
public class OFLVVPAdapter extends VerticalPagerAdapter{
    private int[] maps = new int[]{R.drawable.map_1};
    private ImageView[] iv_maps = new ImageView[maps.length];

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ScaleableImageView iv_scale = new ScaleableImageView(container.getContext());
        iv_scale.setImageResource(maps[position]);
        container.addView(iv_scale);
        iv_maps[position] = iv_scale;
        return iv_scale;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(iv_maps[position]);

    }

    @Override
    public int getCount() {
        return iv_maps.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
