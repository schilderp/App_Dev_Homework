package edu.lewisu.cs.peterschilder.dbtodolist;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by Peter Schilder on 4/10/2016.
 */
public class ToDoCursorWrapper extends CursorWrapper{
    public ToDoCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public ToDo getToDo(){
        String uuid = getString(getColumnIndexOrThrow(ToDoTable.COL_UUID));
        String title = getString(getColumnIndexOrThrow(ToDoTable.COL_TITLE));
        int priority = getInt(getColumnIndexOrThrow(ToDoTable.COL_PRIORITY));
        int done = getInt(getColumnIndexOrThrow(ToDoTable.COL_COMPLETE));

        ToDo toDo = new ToDo(uuid, title, priority, done);
        return toDo;
    }

}
