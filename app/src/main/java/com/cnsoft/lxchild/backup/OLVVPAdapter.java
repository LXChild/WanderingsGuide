//package com.cnsoft.lxchild.backup;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.os.Parcelable;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.cnsoft.lxchild.main.R;
//import com.cnsoft.lxchild.onlinemap.OLMapFragment;
//import com.cnsoft.lxchild.widget.ScaleableImageView;
//import com.cnsoft.lxchild.widget.VerticalPagerAdapter;
//
///**
// * Created by LXChild on 2015/4/5.
// */
//public class OLVVPAdapter extends VerticalPagerAdapter {
//
//    private Context cxt;
//
//    private String TAG = OLVVPAdapter.class.getSimpleName();
//
//    private int[] maps = new int[]{R.drawable.map_f2l, R.drawable.map_f1l, R.drawable.map_b1l};
//    private ImageView[] iv_maps = new ImageView[maps.length];
//    private Bitmap bmp_map;
//    private int map_h, map_w;
//    private double pos_x, pos_y, pos_z;
//    private double pre_z;
//
//    private Paint paint;
//    private Canvas canvas;
//
//    public OLVVPAdapter(Context cxt) {
//        this.cxt = cxt;
//
//        paint = new Paint();
//        paint.setColor(Color.GREEN);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setAntiAlias(true);
//        paint.setTextSize(20);
//        paint.setStrokeWidth(1);
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//
//        ScaleableImageView iv_scale = new ScaleableImageView(container.getContext());
//        Bitmap bmp = getViewResource(container.getContext(), position);
//        iv_scale.setImageBitmap(bmp);
//        container.addView(iv_scale);
//        iv_maps[position] = iv_scale;
//        return iv_scale;
//    }
//
//    private Bitmap getViewResource(Context cxt, int position) {
//        if (OLMapFragment.hm_pos.get("x") != null) {
//            pos_x = map_w * OLMapFragment.hm_pos.get("x");
//            pos_y = map_h * OLMapFragment.hm_pos.get("y");
//            pos_z = OLMapFragment.hm_pos.get("z");
//        }
//        if (pos_z != pre_z) {
////            if (bmp_map != null && !bmp_map.isRecycled()) {
////                bmp_map.recycle();
////            }
//            try {
//                bmp_map = BitmapFactory.decodeResource(cxt.getResources(), maps[position]).copy(Bitmap.Config.ARGB_8888, true);
//            } catch (OutOfMemoryError error) {
//                error.printStackTrace();
//            }
//            map_w = bmp_map.getWidth();
//            map_h = bmp_map.getHeight();
//            canvas = new Canvas(bmp_map);
//            pre_z = pos_z;
//        }
//      //  }
//        if (position == pos_z) {
//            if (canvas != null) {
//                canvas.drawColor(Color.WHITE);
//                canvas.drawBitmap(bmp_map, 0, 0, paint);
//                canvas.drawText("L", (int) pos_x, convertCoord((float) pos_y), paint);
//            }
//        }
//        return bmp_map;
//    }
//
//    /**
//     * 坐标系转换
//     */
//    private int convertCoord(float pos_y_bottom) {
//        return (int) (map_h - pos_y_bottom);
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(iv_maps[position]);
//    }
//
//    @Override
//    public int getCount() {
//        return iv_maps.length;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    @Override
//    public void startUpdate(ViewGroup container) {
//        super.startUpdate(container);
//    }
//
//    @Override
//    public void finishUpdate(ViewGroup container) {
//        super.finishUpdate(container);
//    }
//
//    @Override
//    public Parcelable saveState() {
//        return super.saveState();
//    }
//
//    @Override
//    public int getItemPosition(Object object) {
//        return POSITION_NONE;
//    }
//}
