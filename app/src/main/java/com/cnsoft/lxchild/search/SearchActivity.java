package com.cnsoft.lxchild.search;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.cnsoft.lxchild.algorithm.Algorithm;
import com.cnsoft.lxchild.main.R;
import com.cnsoft.lxchild.onlinemap.Path;

import java.util.List;

@SuppressLint("NewApi")
public class SearchActivity extends ActionBarActivity implements SearchView.OnQueryTextListener, LoaderManager.LoaderCallbacks<List<List<PlaceEntity>>>, ExpandableListView.OnChildClickListener {

    private static Double[] pos;
    final Handler handler = new Handler();
    private String TAG = SearchActivity.class.getSimpleName();
    private ExpandableListView lv_search;
    /**
     * 提供数据源的ExpandPlaceAdapter对象
     */
    private ExpandPlaceAdapter mAdapter;
    /**
     * 保存当前过滤字符串（从SearchView控件中获取）的变量
     */
    private String mCurFilter;

    public static void setPos(Double[] pos) {
        SearchActivity.pos = pos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_search);
        lv_search = (ExpandableListView) findViewById(R.id.search_lv);
    }

    private void initData() {
        getLoaderManager().initLoader(0, null, this);
    }


    @SuppressLint("NewApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        showActionBar();
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    public void showActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mCurFilter = !TextUtils.isEmpty(newText) ? newText : null;
        getLoaderManager().restartLoader(0, null, this);
        return true;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Algorithm.target[0] = ((PlaceEntity) (mAdapter.getChild(groupPosition, childPosition))).getPlacePos()[2];
        Algorithm.target[1] = ((PlaceEntity) (mAdapter.getChild(groupPosition, childPosition))).getPlacePos()[1];
        Algorithm.target[2] = ((PlaceEntity) (mAdapter.getChild(groupPosition, childPosition))).getPlacePos()[0];

        Algorithm.source[0] = 22;
        Algorithm.source[1] = 41;

        Path.algorithm.runAlgorithm();

        finish();
        return false;
    }

    @Override
    public Loader<List<List<PlaceEntity>>> onCreateLoader(int id, Bundle args) {
        return new PlaceListLoader(this, mCurFilter);
    }

    @Override
    public void onLoadFinished(Loader<List<List<PlaceEntity>>> loader, List<List<PlaceEntity>> data) {
        mAdapter = new ExpandPlaceAdapter(this);
        mAdapter.setData(data);
        lv_search.setAdapter(mAdapter);
        lv_search.setDescendantFocusability(ExpandableListView.FOCUS_AFTER_DESCENDANTS);
        lv_search.setOnChildClickListener(this);
    }

    @Override
    public void onLoaderReset(Loader<List<List<PlaceEntity>>> loader) {
        mAdapter.setData(null);
    }
}
