package edu.lewisu.cs.peterschilder.dbtodolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.UUID;

public class DetailActivity extends AppCompatActivity {
    private EditText titleField;
    private Spinner prioritySpinner;
    private CheckBox completeCheckBox;
    private ToDo toDo;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        UUID id = (UUID)getIntent().getSerializableExtra("id");

        titleField = (EditText)findViewById(R.id.title_field);
        titleField.addTextChangedListener(new TitleListener());

        prioritySpinner = (Spinner)findViewById(R.id.spinner);
        prioritySpinner.setOnItemSelectedListener(new PrioritySelect());

        completeCheckBox = (CheckBox)findViewById(R.id.complete_checkbox);
        completeCheckBox.setOnClickListener(new CompleteChangeListener());

        addButton = (Button)findViewById(R.id.add_button);

        if(id != null){
            toDo = ToDoList.get(getApplicationContext()).getToDo(id);
            if (toDo != null){
                titleField.setText(toDo.getTitle());
                prioritySpinner.setSelection(toDo.getPriority());
                completeCheckBox.setChecked(toDo.isComplete());
                addButton.setText(R.string.update);
                addButton.setOnClickListener(new OnUpdateButtonClick());
            }

        }else{
            toDo = new ToDo();
            addButton.setOnClickListener(new OnAddButtonClick());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.delete:
                
                Intent intent = new Intent(getApplicationContext(),ToDoList.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private class TitleListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            toDo.setTitle(s.toString());

        }

        @Override
        public void afterTextChanged(Editable s) {
            //Log.d(TAG, "updated title to " + toDo.getTitle());

        }
    }

    private class CompleteChangeListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(completeCheckBox.isChecked()){
                toDo.setComplete(true);
            }else{
                toDo.setComplete(false);
            }
            //Log.d(TAG, "updated complete to " + toDo.isComplete());
        }
    }

    private class PrioritySelect implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            toDo.setPriority(position);
            //Log.d(TAG, "updated priority to " + toDo.getPriority());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnAddButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            ToDoList toDoList = ToDoList.get(getApplicationContext());
            toDoList.addToDo(toDo);
            finish();
        }
    }

    private class OnUpdateButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            ToDoList toDoList = ToDoList.get(getApplicationContext());
            toDoList.updateToDo(toDo);
            finish();
        }
    }

}
