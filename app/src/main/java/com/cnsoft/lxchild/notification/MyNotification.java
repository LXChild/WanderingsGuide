package com.cnsoft.lxchild.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.cnsoft.lxchild.main.R;

/**
 * Created by LXChild on 2015/5/2.
 */
public class MyNotification {

    public static final int NOTIFICATION_ID = 1;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void showNotification(Context cxt, String title, String text) {
        NotificationManager nm = (NotificationManager) cxt.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.cancelAll();
        //��ʾnotification�������ȴ���һ��Builder���󣬲�������Ӧ����Ϣ
        Notification.Builder builder = new Notification.Builder(cxt);
        builder.setSmallIcon(R.mipmap.icon);
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setVibrate(new long[]{0, 500});
        builder.setContentInfo("ssss");
        builder.setLights(0xff00ff00, 300, 100);
        builder.setTicker(text);

        Intent intent = new Intent(cxt, DesActivity.class);
        PendingIntent p_intent = PendingIntent.getActivity(cxt, 0, intent, 0);
        builder.setContentIntent(p_intent);

        nm.notify(NOTIFICATION_ID, builder.build());
    }
}
