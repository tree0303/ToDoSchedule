package com.example.todoschedule.fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.todoschedule.DeleteTaskListener;
import com.example.todoschedule.R;
import com.example.todoschedule.database.Task;

import java.util.ArrayList;
import java.util.List;

public class ThisDayItemRecyclerViewAdapter extends RecyclerView.Adapter<ThisDayItemRecyclerViewAdapter.ViewHolder> {


    private List<Task> taskList;
    private DeleteTaskListener deleteTaskListener;

    public ThisDayItemRecyclerViewAdapter(){
        this.taskList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_this_day_task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getItemtask_name().setText((CharSequence) taskList.get(position).getTaskName());
        holder.getItemdatetime().setText((CharSequence) taskList.get(position).getDatetime());
        holder.getItemfinbutton().setTag(position);
        holder.getItemfinbutton().setOnClickListener(view -> {
            if (deleteTaskListener!=null) deleteTaskListener.onClickDeleteTask(position);
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView itemtask_name;
        private final TextView itemdatetime;
        private final Button itemfinbutton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemtask_name = itemView.findViewById(R.id.item_task_name_text);
            itemdatetime = itemView.findViewById(R.id.item_datetime_text);
            itemfinbutton = itemView.findViewById(R.id.item_deleat_task_button);
        }

        public TextView getItemtask_name() {
            return itemtask_name;
        }

        public TextView getItemdatetime() {
            return itemdatetime;
        }

        public Button getItemfinbutton() {
            return itemfinbutton;
        }

    }
    public void setDeleteTaskListener(DeleteTaskListener deleteTaskListener) {
        this.deleteTaskListener = deleteTaskListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        //TODO:今日中の範囲のTaskListにして並び替え
        notifyDataSetChanged();
    }
}