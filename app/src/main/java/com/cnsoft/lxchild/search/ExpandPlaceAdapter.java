package com.cnsoft.lxchild.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnsoft.lxchild.main.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by LXChild on 2015/5/5.
 */
public class ExpandPlaceAdapter extends BaseExpandableListAdapter {
    private String TAG = ExpandPlaceAdapter.class.getSimpleName();

    private LayoutInflater inflater;
    private String[] groupNames;
    private List<List<PlaceEntity>> data;

    public ExpandPlaceAdapter(Context cxt) {
        this.inflater = LayoutInflater.from(cxt);
        this.groupNames = new String[]{"停车场","",""};
    }

    public void setData(List<List<PlaceEntity>> data) {
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_group, parent, false);
            holder = new GroupViewHolder();
            holder.mGroupName = (TextView) convertView.findViewById(R.id.group_name);
            holder.mGroupCount = (TextView) convertView.findViewById(R.id.group_count);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.mGroupName.setText(groupNames[groupPosition]);
        holder.mGroupCount.setText("[" + data.get(groupPosition).size() + "]");

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_item, parent, false);
            holder = new ChildViewHolder();
            holder.mIcon = (ImageView) convertView.findViewById(R.id.iv_tag);
            holder.mChildName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.mDetail = (TextView) convertView.findViewById(R.id.tv_pos);
            convertView.setTag(holder);

        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        holder.mIcon.setImageResource(((PlaceEntity) getChild(groupPosition, childPosition)).getPlaceIcon());
        holder.mChildName.setText(((PlaceEntity) getChild(groupPosition, childPosition)).getPlaceName() + ((PlaceEntity) getChild(groupPosition, childPosition)).getPlaceNum());
        holder.mDetail.setText(Arrays.toString(((PlaceEntity) getChild(groupPosition, childPosition)).getPlacePos()));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class GroupViewHolder {
        TextView mGroupName;
        TextView mGroupCount;
    }

    private class ChildViewHolder {
        ImageView mIcon;
        TextView mChildName;
        TextView mDetail;
    }
}
