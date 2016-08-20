package com.cnsoft.lxchild.about;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cnsoft.lxchild.main.R;

/**
 * Created by LXChild on 2015/4/8.
 */
public class AboutFragment extends Fragment{

    private String TAG = AboutFragment.class.getSimpleName();
    private Context cxt;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        return rootView;
    }
}
