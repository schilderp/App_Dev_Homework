package edu.lewisu.cs.peterschilder.dbtodolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Peter Schilder on 4/3/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context){
       super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ToDoTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ToDoTable.onUpgrade(db,oldVersion, newVersion);
    }
}
