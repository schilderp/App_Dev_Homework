package edu.lewisu.cs.peterschilder.dbtodolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by cindy on 2/14/16.
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

    public List getToDos(){
        ArrayList<ToDo> toDos = new ArrayList<>();
        return toDos;
    }

    public ToDo getToDo(UUID id){
        ToDo toDo = null;

        return toDo;
    }


    public void addToDo(ToDo toDo){

    }


}
