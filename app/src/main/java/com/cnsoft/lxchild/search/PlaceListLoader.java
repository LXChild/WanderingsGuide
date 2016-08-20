package com.cnsoft.lxchild.search;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.cnsoft.lxchild.main.R;
import com.cnsoft.lxchild.mapdata.MapData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LXChild on 2015/5/3.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PlaceListLoader extends AsyncTaskLoader<List<List<PlaceEntity>>> {

    private final String TAG = PlaceListLoader.class.getSimpleName();
    private final String[] placeName = new String[]{"出入口","停车位"};
    private Context cxt;
    private ProgressDialog progressDialog;

    private List<List<PlaceEntity>> places;
    private List<List<PlaceEntity>> loadedPlaces;

    private List<PlaceEntity> place_b1;
    private List<PlaceEntity> place_f1;
    private List<PlaceEntity> place_f2;

    private String filter;

    public PlaceListLoader(Context context, String filter) {
        super(context);
        this.cxt = context;
        this.filter = filter;
    }

    @Override
    protected void onStartLoading() {
        showProgressDialog();
        forceLoad();
        if (loadedPlaces != null) {
            deliverResult(loadedPlaces);
        }
        super.onStartLoading();
    }

    @Override
    public List<List<PlaceEntity>> loadInBackground() {
        if (places == null) {
            places = new ArrayList<>();
        }
        if (place_b1 == null) {
            place_b1 = new ArrayList<>();
        }
        if (place_f1 == null) {
            place_f1 = new ArrayList<>();
        }
        if (place_f2 == null) {
            place_f2 = new ArrayList<>();
        }

        for (int i = 0; i < MapData.map.length; i++) {

            for (int j = 0; j < MapData.map[i].length; j++) {

                for (int k = 0; k < MapData.map[i][j].length; k++) {

                    if (MapData.map[i][j][k].length < 2) {
                        continue;
                    }

                    addPlace(MapData.map[i][j][k][0], MapData.map[i][j][k][1], new int[]{i, j, k});
                }
            }
        }

        places.add(place_b1);
        places.add(place_f1);
        places.add(place_f2);
        return places;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCanceled(List<List<PlaceEntity>> data) {
        super.onCanceled(data);
        cancelLoad();
        progressDialog.dismiss();
    }

    @Override
    protected void onStopLoading() {
        Log.d(TAG, "onStopLoading");
        progressDialog.dismiss();
        super.onStopLoading();
    }

    @Override
    public void deliverResult(List<List<PlaceEntity>> data) {
        progressDialog.dismiss();
        if (isReset()) {
            if (data != null) {
                data.clear();
                data = null;
            }
        }
        loadedPlaces = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onReset() {
        showProgressDialog();
        onStopLoading();
        if (loadedPlaces != null) {
            loadedPlaces = null;
        }
        super.onReset();
    }

    private void addPlace(int type, int num, int[] pos) {
        if (filter != null && !filter.trim().equals("")) {
            if (((placeName[type - 2] + num).contains(filter)) || (filter.contains((placeName[type - 2] + num)))) {
                PlaceEntity place = initPlace(type, num, pos);
                addSortedPlace(pos, place);
            }
        } else {
            PlaceEntity place = initPlace(type, num, pos);
            addSortedPlace(pos, place);
        }
    }

    private void addSortedPlace(int[] pos, PlaceEntity place) {
        switch (pos[0]) {
            case 0:
                place_b1.add(place);
                break;
            case 1:
                place_f1.add(place);
                break;
            case 2:
                place_f2.add(place);
                break;
            default:
                break;
        }
    }

    private PlaceEntity initPlace(int type, int num, int[] pos) {
        PlaceEntity place = new PlaceEntity();
        switch (type) {
            case 2:
                place.setPlaceTypeCode(2);
                place.setPlaceIcon(R.drawable.chukou);
                place.setPlaceName(placeName[0]);
                break;
            case 3:
                place.setPlaceTypeCode(3);
                place.setPlaceIcon(R.drawable.tichewei);
                place.setPlaceName(placeName[1]);
                break;
            default:
                break;
        }
        place.setPlaceNum(num);
        place.setPlacePos(pos);
        return place;
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(cxt);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("请稍候");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
}
