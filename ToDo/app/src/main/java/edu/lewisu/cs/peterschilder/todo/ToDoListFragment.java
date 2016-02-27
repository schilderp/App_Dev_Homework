package edu.lewisu.cs.peterschilder.todo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoListFragment extends Fragment {
    private List<ToDo> toDos;
    private ToDoList toDoList;
    private ToDoAdapter adapter;
    private Button addButton;

    public ToDoListFragment() {
        toDoList = ToDoList.get();
        toDos = toDoList.getToDos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);
        adapter = new ToDoAdapter(toDos);
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoList.addRandomToDo();

                int end = toDos.size()-1;

                adapter.notifyItemInserted(end);
                recyclerView.scrollToPosition(end);
            }
        });

        return view;
    }


    private class ToDoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView titleTextView;
        private TextView dateTextView;
        private CheckBox completeCheckbox;
        private ToDo toDo;

        public ToDoHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            titleTextView = (TextView)itemView.findViewById(R.id.title_text_view);
            dateTextView = (TextView)itemView.findViewById(R.id.date_text_view);
            completeCheckbox = (CheckBox)itemView.findViewById(R.id.task_complete_checkbox);
            completeCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toDo.setComplete(completeCheckbox.isChecked());
                    String toastString;
                    if(toDo.isComplete()){
                        toastString = "ToDo marked complete";
                    }else {
                        toastString = "ToDo marked incomplete";
                    }
                    Toast.makeText(getActivity(), toastString, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bindToDo(ToDo toDo){
            this.toDo = toDo;
            titleTextView.setText(toDo.getTitle());
            dateTextView.setText(toDo.getDueDate().toString());
            completeCheckbox.setChecked(toDo.isComplete());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),ToDoActivity.class);
            UUID id = toDo.getId();
            //Toast.makeText(getActivity(), id.toString(),Toast.LENGTH_SHORT).show();
            intent.putExtra("id",id);
            startActivity(intent);
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
