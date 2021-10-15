package com.example.chuongtrinhtinhtoan;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {
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

    public class MyBinder extends Binder {
        public MyService getService(){
            return MyService.this;
        };
    }
}
