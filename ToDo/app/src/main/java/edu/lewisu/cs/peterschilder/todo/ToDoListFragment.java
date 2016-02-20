package edu.lewisu.cs.peterschilder.todo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoListFragment extends Fragment {
    private List<ToDo> toDos;
    private ToDoAdapter adapter;

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
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);
        adapter = new ToDoAdapter(toDos);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }


    private class ToDoHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private TextView dateTextView;
        private CheckBox completeCheckbox;
        private ToDo todo;

        public ToDoHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView)itemView.findViewById(R.id.title_text_view);
            dateTextView = (TextView)itemView.findViewById(R.id.date_text_view);
            completeCheckbox = (CheckBox)itemView.findViewById(R.id.task_complete_checkbox);
        }

        public void bindToDo(ToDo toDo){
            this.todo = toDo;
            titleTextView.setText(toDo.getTitle());
            dateTextView.setText(toDo.getDueDate().toString());
            completeCheckbox.setChecked(toDo.isComplete());
        }
    }

    private class ToDoAdapter extends RecyclerView.Adapter<ToDoHolder>{
        private List<ToDo> ToDoList;
        private ToDoHolder holder;

        public ToDoAdapter(List<ToDo> toDoList) {
            ToDoList = toDoList;
        }

        @Override
        public ToDoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_todo,parent, false);
            holder = new ToDoHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ToDoHolder holder, int position) {
            ToDo toDo = ToDoList.get(position);
            holder.bindToDo(toDo);
        }

        @Override
        public int getItemCount() {
            return ToDoList.size();
        }
    }

}
