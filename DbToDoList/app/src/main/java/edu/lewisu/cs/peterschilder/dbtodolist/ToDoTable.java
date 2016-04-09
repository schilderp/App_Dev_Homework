package edu.lewisu.cs.peterschilder.dbtodolist;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Peter Schilder on 4/3/2016.
 */
public class ToDoTable {
    public static final String TABLE_TODO = "todos";
    public static final String COL_ID = "_id";
    public static final String COL_UUID = "uuid";
    public static final String COL_TITLE = "title";
    public static final String COL_PRIORITY = "priority";
    public static final String COL_COMPLETE = "complete";


    private  static final String DATABASE_CREATE =
            "CREATE table " + TABLE_TODO + "(" +
                    COL_ID + " integer primary key autoincrement, " +
                    COL_UUID + " text not null, " +
                    COL_TITLE + " text not null, " +
                    COL_PRIORITY + " integer default 0, " +
                    COL_COMPLETE + " integer default 0 " + ");";

    public static void onCreate(SQLiteDatabase database){
        Log.d(ToDoTable.class.getName(), DATABASE_CREATE);
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(ToDoTable.class.getName(), "Upgrading database from version " +
                oldVersion + " to " + newVersion + "which will destroy all data");
        db.execSQL("DROP TABLE IF EXISTS " + ToDoTable.TABLE_TODO);
        onCreate(db);
    }
}
