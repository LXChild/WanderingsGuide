//package com.cnsoft.lxchild.search;
//
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.os.Build;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.cnsoft.lxchild.main.R;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by LXChild on 2015/5/4.
// */
//public class PlacesAdapter extends ArrayAdapter<PlaceEntity> {
//
//    private LayoutInflater inflater;
//
//    public PlacesAdapter(Context context) {
//        super(context, android.R.layout.simple_list_item_2);
//		this.inflater = LayoutInflater.from(context);
//    }
//
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public void setData(List<PlaceEntity> data) {
//        clear();
//        if (data != null) {
//            addAll(data);
//        }
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder mHolder;
//		if(convertView == null) {
//			convertView = inflater.inflate(R.layout.adapter_item,
//					parent, false);
//
//			mHolder = new ViewHolder();
//
//			mHolder.iv_place = (ImageView) convertView
//					.findViewById(R.id.iv_tag);
//			mHolder.tv_place_name = (TextView) convertView
//					.findViewById(R.id.tv_name);
//			mHolder.tv_place_pos = (TextView) convertView
//					.findViewById(R.id.tv_pos);
//
//			convertView.setTag(mHolder);
//		} else {
//			mHolder = (ViewHolder) convertView.getTag();
//		}
//        PlaceEntity place = getItem(position);
//        mHolder.iv_place.setImageResource(place.getPlaceIcon());
//		mHolder.tv_place_name.setText(place.getPlaceName());
//		mHolder.tv_place_pos.setText(Arrays.toString(place.getPlacePos()));
//		return convertView;
//	}
//
//	public final class ViewHolder {
//		public ImageView iv_place;
//		public TextView tv_place_name;
//		public TextView tv_place_pos;
//	}
//}
