package ru.mirea.bandurin.looper;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyLooper extends Thread {
    public Handler mHandler;
    private Handler mainHandler;

    public MyLooper(Handler mainThreadHandler) {
        mainHandler = mainThreadHandler;
    }

    public void run() {
        Log.d("MyLooper", "run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            public void handleMessage(Message msg) {
                String job = msg.getData().getString("JOB");
                int age = msg.getData().getInt("AGE");
                try{
                    TimeUnit.SECONDS.sleep(age);
                }
                catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("message", String.format("My job is %s and my age is %d years old ", job, age));
                message.setData(bundle);
                mainHandler.sendMessage(message);
            }
        };
        Looper.loop();
    }
}