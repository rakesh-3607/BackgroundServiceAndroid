package com.example.rakeshgondaliya.backgroundsevicesyncapp.domain;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.rakeshgondaliya.backgroundsevicesyncapp.model.CorePoint;
import com.example.rakeshgondaliya.backgroundsevicesyncapp.model.PriorityQueue;

import java.util.Random;

/**
 * Created by rakeshgondaliya on 18/12/15.
 */

public class ProducerService extends Service{

    private Intent intent;
    public static final String BROADCAST_ACTION = "com.example.rakeshgondaliya.backgroundsevicesyncapp.androidinterface.MainActivity";

    private static final int MAX_QUEUE_SIZE = 100000;
    private static final int PRODUCER_TIMER = 1000;
    private static final int CONSUMER_TIMER = 1200;

    public static PriorityQueue priorityQueue = new PriorityQueue(MAX_QUEUE_SIZE);
    private Handler producerHandler = new Handler();
    private Handler consumerHandler = new Handler();


    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println(" onCreate Called : ");
        intent = new Intent(BROADCAST_ACTION);

        producerHandler.removeCallbacks(producePointRunnable);
        producerHandler.postDelayed(producePointRunnable,PRODUCER_TIMER);

        consumerHandler.removeCallbacks(consumePointRunnable);
        consumerHandler.postDelayed(consumePointRunnable,CONSUMER_TIMER);

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        System.out.println(" onStart Called : ");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println(" onStartCommand Called : ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println(" onDestroy Called : ");
        producerHandler.removeCallbacks(producePointRunnable);
        consumerHandler.removeCallbacks(consumePointRunnable);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public PriorityQueue getPriorityQueue() {
        return priorityQueue;
    }

    private Runnable producePointRunnable = new Runnable() {
        public void run() {
            insertIntoQueue();
            producerHandler.postDelayed(this, PRODUCER_TIMER); // 1 seconds
        }
    };

    private Runnable consumePointRunnable = new Runnable() {
        public void run() {

            insertIntoQueue();
            removeFromQueue();
            consumerHandler.postDelayed(this, CONSUMER_TIMER); // 1 seconds
        }
    };

    private void insertIntoQueue() {

        if(!getPriorityQueue().isFull()){
            CorePoint corePoint = getPriorityQueue().insert(generateRandomString(), generateRandomNumber());
            System.out.println(" Point : " + corePoint.getPointName() + " Priority " + corePoint.getPriority());
        }

    }

    private void removeFromQueue() {
        if(!getPriorityQueue().isEmpty()){
            CorePoint corePoint = getPriorityQueue().remove();
            intent.putExtra("point", corePoint.getPointName());
            intent.putExtra("size", getPriorityQueue().size());
            sendBroadcast(intent);
        }
    }
    /**
     *
     * @return
     */
    private String generateRandomString(){

        StringBuilder sb = new StringBuilder();
        Random r = new Random();
         final int POINT_SIZE = 4;
         final char [] subset = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        char buf[] = new char[POINT_SIZE];
        for (int i=0;i<buf.length;i++) {
            int index = r.nextInt(subset.length);
            buf[i] = subset[index];
        }

        return new String(buf).toUpperCase();
    }

    /**
     *
     * @return
     */
    private Integer generateRandomNumber(){
        Random rand = new Random();
        int max = 10;
        int min = 1;
        return rand.nextInt((max - min) + 1) + min;
    }

}
