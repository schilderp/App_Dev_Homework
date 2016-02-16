package edu.lewisu.cs.peterschilder.todo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoListFragment extends Fragment {
    List<ToDo> toDos;


    public ToDoListFragment() {
        toDos = new ArrayList<>();
        toDos.add(new ToDo("Grade", 3, false));
        toDos.add(new ToDo("Do Homework",2,false));
        toDos.add(new ToDo("Submit Homework",3,false));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false);
    }

}
