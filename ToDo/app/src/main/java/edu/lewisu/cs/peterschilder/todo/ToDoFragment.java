package edu.lewisu.cs.peterschilder.todo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoFragment extends Fragment {

    private EditText titleField;
    private Button dateButton;
    private Spinner prioritySpinner;
    private CheckBox completeCheckbox;

    private ToDo toDo;

    private static final String TAG="To do updates";

    public ToDoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //toDo = new ToDo();
        UUID toDoId = (UUID) getActivity().getIntent().getSerializableExtra("id");
        ToDoList toDoList = ToDoList.get();
        toDo = toDoList.getToDo(toDoId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_to_do, container, false);

        titleField = (EditText)v.findViewById(R.id.title_field);
        titleField.addTextChangedListener(new TitleListener());

        dateButton = (Button)v.findViewById(R.id.due_date_button);
        prioritySpinner = (Spinner)v.findViewById(R.id.priority_spinner);
        prioritySpinner.setOnItemSelectedListener(new PriorityListener());

        completeCheckbox = (CheckBox)v.findViewById(R.id.complete_checkbox);
        completeCheckbox.setOnClickListener(new CompleteCheckListener());

        //set view components to display detail
        titleField.setText(toDo.getTitle());
        dateButton.setText(toDo.getDueDate().toString());
        prioritySpinner.setSelection(toDo.getPriority());
        completeCheckbox.setChecked(toDo.isComplete());

        return v;
    }

    private class TitleListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            toDo.setTitle(s.toString());
            Log.d(TAG, "Up Dated title to:" + toDo.getTitle());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class PriorityListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            toDo.setPriority(position);
            Log.d(TAG, "Updated priority to:" + toDo.getPriority());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class CompleteCheckListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(completeCheckbox.isChecked()){
                toDo.setComplete(true);
            }else {
                toDo.setComplete(false);
            }
            Log.d(TAG, "Updated complete check to:" + toDo.isComplete());
        }
    }

}
