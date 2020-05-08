package com.example.cours9;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.TodoViewHolder> {
    private List<ToDo> todoDataSet;

    public TodoRecyclerAdapter(List<ToDo> todoDataSet) {
        this.todoDataSet = todoDataSet;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.todo_cart, parent, false);
    TodoViewHolder todoViewHolder = new TodoViewHolder(v);
    return todoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        ToDo todoToDisplay = todoDataSet.get(position);
        holder.textViewTitle.setText(todoToDisplay.getTitle());
        holder.textViewDescription.setText(todoToDisplay.getDescription());
        holder.textViewDate.setText(todoToDisplay.getDateAdded().toString());
    }

    @Override
    public int getItemCount() {
        return todoDataSet.size();
    }

    public class TodoViewHolder  extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewDate;
        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textView_card_title);
            textViewDescription = itemView.findViewById(R.id.textView_cardDescription);
            textViewDate = itemView.findViewById(R.id.textView_cardDate);

        }
    }
}
