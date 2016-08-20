package com.cnsoft.lxchild.settings;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cnsoft.lxchild.main.R;

/**
 * Created by LXChild on 2015/3/24.
 */
public class SettingsFrg_lv_adapter extends BaseAdapter{

    private LayoutInflater inflater;
    private String[] item;
    public SettingsFrg_lv_adapter(Context cxt) {
        // TODO Auto-generated constructor stub
        this.inflater = LayoutInflater.from(cxt);
        Resources res = cxt.getResources();
        item = res.getStringArray(R.array.settingsact_lv_item_text);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return item.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.settings_lv_items, parent, false);
        }
        TextView tv_item = (TextView) convertView.findViewById(R.id.settingsact_lv_tv_item);
        tv_item.setText(item[position]);

        return convertView;
    }
}
