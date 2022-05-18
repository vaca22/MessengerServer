package com.vaca.messengerserver;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.MessageFormat;
import java.util.Date;

public class MyService extends Service {

    protected static final String TAG = "MessengerTestService";

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d(TAG, "收到消息");
                    //获取客户端message中的Messenger，用于回调
                    final Messenger callback = msg.replyTo;
                    try {
                        Bundle bundle = new Bundle();
                        bundle.putString("lat", "40.087379");
                        bundle.putString("lon", "116.610951");
                        Message fuck=Message.obtain(null, 0);
                        fuck.setData(bundle);
                        // 回调
                        callback.send(fuck);
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(mHandler).getBinder();
    }

}