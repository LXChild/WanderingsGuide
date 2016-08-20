package com.cnsoft.lxchild.main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cnsoft.lxchild.about.AboutFragment;
import com.cnsoft.lxchild.airportservice.ServiceFragment;
import com.cnsoft.lxchild.offlinemap.OFLFragment;
import com.cnsoft.lxchild.onlinemap.OLFragment;
import com.cnsoft.lxchild.search.SearchActivity;
import com.cnsoft.lxchild.settings.SettingsFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by LXChild on 2015/3/19.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static Bundle bundle;
    private static String TAG = MainActivity.class.getSimpleName();
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_main);

        //创建NavigationDrawer
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
    }

    private void initData() {
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                OLFragment olmf = new OLFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, olmf).commit();
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                OFLFragment oflmf = new OFLFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, oflmf).commit();
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                ServiceFragment svcF = new ServiceFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, svcF).commit();
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                SettingsFragment sf = new SettingsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, sf).commit();
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                AboutFragment af = new AboutFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, af).commit();
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                finish();
                System.exit(0);
                break;
            default:
                break;
        }
    }

    /**
     * 按键监控事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            //如果按键为返回键则调用双击退出程序函数
            case KeyEvent.KEYCODE_BACK:
                exitBy2Click();
                break;
            case KeyEvent.KEYCODE_MENU:
                break;
            default:
                break;
        }
        return false;
    }

    /**
     * 双击退出程序
     */
    private void exitBy2Click() {

        if (!isExit) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            Timer tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);//关闭进程
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_map, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search_item:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
//            case R.id.action_option_item:
//                return true;
            default:
                return false;
        }
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.navigation_drawer_item, container, false);
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    //    private static class MyHandler extends Handler {
//        WeakReference<MapActivity> mActivity;
//
//        MyHandler(MapActivity activity) {
//            mActivity = new WeakReference<MapActivity>(activity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            MapActivity theActivity = mActivity.get();
//            if (theActivity != null) {
//                // theActivity.hm_pos = (HashMap<String, Integer>) msg.obj;
//            }
//        }
//    }
}
