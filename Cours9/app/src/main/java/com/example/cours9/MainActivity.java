package com.example.cours9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btn_showAddToDoDialog;
    List<ToDo> todoList;
    TodoRecyclerAdapter todoRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleTodo);
        btn_showAddToDoDialog= findViewById(R.id.btn_showAddToDoDialog);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        todoList = new ArrayList<ToDo>();
        todoRecyclerAdapter = new TodoRecyclerAdapter(todoList);
        recyclerView.setAdapter(todoRecyclerAdapter);

        setListener();

    }
    private void setListener(){
        btn_showAddToDoDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAndShowAddTodoDialog();
            }
        });
    }
    private void createAndShowAddTodoDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_todo_dialog);

        Button dialogBtnAddToDo = dialog.findViewById(R.id.btn_showAddToDoDialog);

        dialogBtnAddToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextTitle = dialog.findViewById(R.id.editTextTitle);
                EditText editTextDescription = dialog.findViewById(R.id.editTextDescription);
                ToDo toDoToAdd = new ToDo(new Date(),
                        editTextTitle.getText().toString(),
                        editTextDescription.getText().toString()) ;
                todoList.add(toDoToAdd);
                todoRecyclerAdapter.notifyDataSetChanged();

                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
