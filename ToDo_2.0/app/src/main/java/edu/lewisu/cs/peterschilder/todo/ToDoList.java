package edu.lewisu.cs.peterschilder.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Peter Schilder on 2/25/2016.
 */
public class ToDoList {
    private static ToDoList toDoList;
    private List<ToDo> toDos;


    // creates an instance of ToDoList
    public static ToDoList get(){
        if(toDoList == null){
            toDoList = new ToDoList();

        }
        return toDoList;
    }

    public ToDoList() {
        toDos = new ArrayList<>();
        toDos.add(new ToDo("Grade", 1, false));
        toDos.add(new ToDo("Do Homework",2,false));
        toDos.add(new ToDo("Submit Homework",0,false));
    }

    public List<ToDo> getToDos() {
        return toDos;
    }

    public ToDo getToDo(UUID id){
        ToDo toDo = null;
        for(ToDo td:toDos){
            if (td.getId().equals(id)){
                toDo=td;
            }
        }
        return toDo;
    }

    //generating random numbers to create a todo.
    public void addRandomToDo(){
        Random random = new Random();
        int taskNo = random.nextInt(20);
        boolean done;
        if (taskNo % 2 ==0){
            done = true;
        }else {
            done = false;
        }
        int priority = random.nextInt(3);

        toDos.add(new ToDo("Task# "+taskNo, priority, done));
    }

    public void addToDo (ToDo toDo){
        toDos.add(toDo);
    }
}
