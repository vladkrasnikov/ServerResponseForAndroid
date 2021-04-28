package com.example.checksite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    public static TextView textView;
    public static EditText editText;
    public static DBHelper dbHelper;
    final String LOG_TAG = "myLogs";
    public static String url_adress;
    boolean FlagTimer;
    TimerTask timerTask;
    Timer timer = new Timer(false);

    public static ListView listView;
    public static List<String> stringList;
    public static ArrayAdapter<String> stringArrayAdapter;
    public static SQLiteDatabase SqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.URL);
        dbHelper = new DBHelper(this);

        stringList = new ArrayList<String>();
        listView = (ListView)findViewById(R.id.LIST);
        stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);
        stringArrayAdapter.setNotifyOnChange(true);
        listView.setAdapter(stringArrayAdapter);
        FlagTimer = false;

        final Handler handler = new Handler();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(FlagTimer == true)
                        {
                            StartAction();
                            Log.d(LOG_TAG,"Action Started");
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 5000,5000);
    }



    public void StartAction()
    {
        Log.d(LOG_TAG, url_adress);
        startService(new Intent(this, MyService.class).putExtra("Url", url_adress));
    }

    public void onClickStart(View v)
    {
        url_adress = editText.getText().toString();
        FlagTimer = true;
    }

    public  void onClickStop(View v)
    {
        FlagTimer =false;
        stopService(new Intent(this, MyService.class).putExtra("Url", url_adress));
    }

    public void onClickChart(View v) throws InterruptedException {
        FlagTimer = false;
        stopService(new Intent(this, MyService.class).putExtra("Url", url_adress));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, Chart.class);
        startActivity(intent);
    }



}