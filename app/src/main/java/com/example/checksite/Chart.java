package com.example.checksite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Chart extends Activity {
    float values[]={0,0};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS,null, null, null,null,null,null);
        values[0]=0;
        values[1]=0;
        if(cursor.moveToFirst())
        {
            int resIndex = cursor.getColumnIndex(DBHelper.KEY_RES);
            do{
                if(cursor.getString(resIndex).equals("Correct Response"))values[0]++;
                else values[1]++;
            }while(cursor.moveToNext());
        }else Log.d("qqqqq", "Finish");
        TextView textView_1 = (TextView)findViewById(R.id.textView);
        Log.d("Count",String.valueOf(values[0]));
        textView_1.setText((int) values[0]);
        TextView textView_2 = (TextView)findViewById(R.id.textView2);
        Log.d("Count",String.valueOf(values[1]));
        textView_2.setText((int) values[1]);
        LinearLayout linear=(LinearLayout) findViewById(R.id.drow);
        values=calculateData(values);
        linear.addView(new MyGraphview(this,values));
    }


    public void onClickBack(View v)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }




    private float[] calculateData(float[] data) {
        float total=0;
        for(int i=0;i<data.length;i++)
        {
            total+=data[i];
        }
        for(int i=0;i<data.length;i++)
        {
            data[i]=360*(data[i]/total);
            Log.d("value", String.valueOf(data[i]));
        }
        return data;

    }
    public class MyGraphview extends View
    {
        private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        private float[] value_degree;
        private int[] COLORS={Color.BLUE, Color.GREEN};

        RectF rectf = new RectF(30, 30, 1000, 1000);
        int temp=0;
        public MyGraphview(Context context, float[] values) {

            super(context);
            Log.d("out", String.valueOf(values.length));
            value_degree=new float[values.length];
            for(int i=0;i<values.length;i++)
            {
                value_degree[i]=values[i];
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for (int i = 0; i < value_degree.length; i++) {
                if (i == 0) {
                    paint.setColor(COLORS[i]);
                    canvas.drawArc(rectf, 0, value_degree[i], true, paint);
                    Log.d("out1","1");
                }
                else
                {
                    temp += (int) value_degree[i - 1];
                    paint.setColor(COLORS[i]);
                    canvas.drawArc(rectf, temp, value_degree[i], true, paint);
                    Log.d("out2","2");
                }
            }

        }

    }

}