package com.example.rakeshgondaliya.backgroundsevicesyncapp.androidinterface;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rakeshgondaliya.backgroundsevicesyncapp.R;
import com.example.rakeshgondaliya.backgroundsevicesyncapp.domain.ProducerService;


public class MainActivity extends AppCompatActivity {

    Button addButton,removeButton;
    TextView lastPointTextview,sizetext;
    Intent broadcastIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        addButton = (Button) findViewById(R.id.buttonAdd);
        removeButton = (Button) findViewById(R.id.buttonRemove);
        lastPointTextview = (TextView) findViewById(R.id.lastPointTextview);
        sizetext = (TextView) findViewById(R.id.sizetext);

        broadcastIntent  = new Intent(MainActivity.this, ProducerService.class);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.this.startService(broadcastIntent);
                registerReceiver(broadcastReceiver, new IntentFilter(ProducerService.BROADCAST_ACTION));


            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                unregisterReceiver(broadcastReceiver);
                MainActivity.this.stopService(broadcastIntent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            updateUI(intent);
        }
    };

    private void updateUI(Intent intent) {

        String pointName = intent.getStringExtra("point");
        int size = intent.getIntExtra("size",0);

        lastPointTextview.setText(pointName);
        sizetext.setText(size +"");
    }
}
