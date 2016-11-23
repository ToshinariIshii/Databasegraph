package com.example.b1014001.databasegraph;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatabaseActivity extends Activity {
    public static int milk;
    public static int hindo;
    public static String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();
        final EditText milkText = (EditText) findViewById(R.id.editMilk);
        final EditText hindoText = (EditText) findViewById(R.id.editHindo);
        Button entryButton = (Button) findViewById(R.id.insert);
        // 現在日時の取得
        Date now = new Date(System.currentTimeMillis());
        // 日時のフォーマットオブジェクト作成
        DateFormat formatter = new SimpleDateFormat("MM/dd HH:mm.ss");
        // フォーマット
        date = formatter.format(now);
        entryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milk = Integer.valueOf(milkText.getText().toString());
                hindo = Integer.valueOf(hindoText.getText().toString());
                ContentValues insertValues = new ContentValues();
                insertValues.put("date", date);
                insertValues.put("milk", milk);
                insertValues.put("hindo", hindo);

                long id = db.insert("person", date, insertValues);
            }
        });
        Button deleteAllButton = (Button) findViewById(R.id.deleteAll);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                milk = Integer.valueOf(milkText.getText().toString());
                hindo = Integer.valueOf(hindoText.getText().toString());
                db.delete("person", null, null);
            }
        });
        Button detaBaseButton = (Button) findViewById(R.id.dataBase);
        detaBaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbIntent = new Intent(DatabaseActivity.this,
                        BarChartActivity.class);
                startActivity(dbIntent);
            }
        });
    }
}
