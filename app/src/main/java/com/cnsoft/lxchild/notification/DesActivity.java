package com.cnsoft.lxchild.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

/**
 * Created by LXChild on 2015/5/2.
 */
public class DesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(MyNotification.NOTIFICATION_ID);
        nm.cancelAll();
        this.finish();
    }
}
