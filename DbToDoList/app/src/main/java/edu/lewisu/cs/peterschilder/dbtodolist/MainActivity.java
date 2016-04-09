package edu.lewisu.cs.peterschilder.dbtodolist;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mainTextView = (TextView)findViewById(R.id.maain_textview);

        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        mainTextView.setText("success");
    }
}
