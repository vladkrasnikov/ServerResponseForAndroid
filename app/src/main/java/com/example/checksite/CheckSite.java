package com.example.checksite;

import static com.example.checksite.MainActivity.SqLiteDatabase;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

public class CheckSite extends AsyncTask<String, String, String> {


    final String My_Log = "myLog";
    String url_adress = MainActivity.url_adress;


    public CheckSite(String Url)
    {
        url_adress = Url;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }





    @Override
    protected String doInBackground(String... params) {
        Log.d(My_Log, url_adress);
        try {
            URL url = new URL(url_adress);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.connect();
            if(HttpURLConnection.HTTP_OK == urlConnection.getResponseCode())
            {
                Log.d(My_Log,Integer.toString(urlConnection.getResponseCode()));
                SqLiteDatabase = MainActivity.dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.KEY_URL, url_adress);
                contentValues.put(DBHelper.KEY_RES,"Correct Response");
                MainActivity.stringArrayAdapter.add("Correct Response");
                SqLiteDatabase.insert(DBHelper.TABLE_CONTACTS,null,contentValues);
            }else {
                Log.d(My_Log,Integer.toString(urlConnection.getResponseCode()));
                SqLiteDatabase = MainActivity.dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.KEY_URL, url_adress);
                MainActivity.stringArrayAdapter.add("Fail");
                contentValues.put(DBHelper.KEY_RES,"Fail");
                SqLiteDatabase.insert(DBHelper.TABLE_CONTACTS,null,contentValues);
            }
        } catch (Exception e) {
            Log.d(My_Log, "Exception 1");
            System.out.println(e.getMessage());
        }
        return "";
    }
}
