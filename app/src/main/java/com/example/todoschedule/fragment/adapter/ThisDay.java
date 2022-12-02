package com.example.todoschedule.fragment.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

public class ThisDay extends RecyclerView.Adapter<ThisDay.ViewHolder> implements AssortmentOfDateTime {

    @NonNull
    @Override
    public ThisDay.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thisday,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThisDay.ViewHolder holder, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView thisday;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thisday = itemView.findViewById(R.id.thisday_text);
        }
        public TextView getThisday() {
            return thisday;
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}