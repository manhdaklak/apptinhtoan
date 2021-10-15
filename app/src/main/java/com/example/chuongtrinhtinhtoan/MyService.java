package com.example.chuongtrinhtinhtoan;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    public static final String CHANEL_ID = "com.example.chuongtrinhtinhtoan";
    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public int add(int a, int b)
    {
        return a + b;
    }
    public int tru(int a, int b)
    {
        return a - b;
    }
    public int nhan(int a, int b)
    {
        return a * b;
    }
    public int chia(int a, int b)
    {
        return a / b;
    }

    private void createChanelNotification() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANEL_ID,
                    "Chanel Service Example",
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.setSound(null,null);

            NotificationManager manager = getSystemService(NotificationManager.class);
            if(manager != null) {
                manager.createNotificationChannel(channel);
            }

        }
    }
    private void ShowNotification() {

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =PendingIntent.getActivity(this,0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification);
        Notification notification = new NotificationCompat.Builder(this, CHANEL_ID)
                .setSmallIcon(R.drawable.icon_1)
                .setContentIntent(pendingIntent)
                .setCustomBigContentView(remoteViews)
                .setSound(null)
                .build();
        startForeground(1, notification);
    }
    public class MyBinder extends Binder {
        public MyService getService(){
            return MyService.this;
        };
    }
}
