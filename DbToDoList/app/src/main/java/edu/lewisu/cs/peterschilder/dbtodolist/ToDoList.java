package edu.lewisu.cs.peterschilder.dbtodolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by peter schilder on 2/14/16.
 */
public class ToDoList {
    private static ToDoList toDoList;
    private SQLiteDatabase database;


    public static ToDoList get(Context context){
        if(toDoList == null){
            toDoList = new ToDoList(context);
        }
        return toDoList;
    }

    private ToDoList(Context context){
        database = new DbHelper(context).getWritableDatabase();
    }

    public void updateToDo(ToDo toDo){
        ContentValues values = getContentValues(toDo);
        String uuid = toDo.getId().toString();
        String selection = ToDoTable.COL_UUID + "=?";
        String[] selectionArgs = {uuid};
        database.update(ToDoTable.TABLE_TODO, values, selection, selectionArgs);
    }


    public List getToDos(){
        ArrayList<ToDo> toDos = new ArrayList<>();
        Cursor c = database.query(ToDoTable.TABLE_TODO, null, null, null, null, null, null);
        ToDoCursorWrapper cursorWrapper = new ToDoCursorWrapper(c);
        ToDo toDo;

        try{
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                toDo = cursorWrapper.getToDo();
                toDos.add(toDo);
                cursorWrapper.moveToNext();
            }
        }finally {
            cursorWrapper.close();
        }

        return toDos;
    }

    public ToDo getToDo(UUID id){
        ToDo toDo = null;

        String selection = ToDoTable.COL_UUID + "=?";
        String[] selectionArgs = {id.toString()};

        Cursor c = database.query(ToDoTable.TABLE_TODO, null, selection,selectionArgs, null, null, null);
        ToDoCursorWrapper cursor = new ToDoCursorWrapper(c);

        try {
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            toDo = cursor.getToDo();
        }finally {
            cursor.close();
        }
        return toDo;
    }

    public void addToDo(ToDo toDo){
        ContentValues contentValues = getContentValues(toDo);
        database.insert(ToDoTable.TABLE_TODO, null, contentValues);
    }

    public void deleteToDo(ToDo toDo){
        String uuid = toDo.getId().toString();
        String selection = ToDoTable.COL_UUID + "=?";
        String[] selectionArgs = {uuid};
        database.delete(ToDoTable.TABLE_TODO, selection, selectionArgs);
    }

    private ContentValues getContentValues(ToDo toDo){
        ContentValues values = new ContentValues();

        values.put(ToDoTable.COL_UUID, toDo.getId().toString());
        values.put(ToDoTable.COL_TITLE, toDo.getTitle());

        int done = 0;
        if(toDo.isComplete()){
            done = 1;
        }
        values.put(ToDoTable.COL_COMPLETE, done);
        values.put(ToDoTable.COL_PRIORITY, toDo.getPriority());

        return values;
    }


}
