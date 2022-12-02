package com.example.todoschedule.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoschedule.R;

public class ThisWeek extends RecyclerView.Adapter<ThisWeek.ViewHolder> implements AssortmentOfDateTime {

    @NonNull
    @Override
    public ThisWeek.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thisweek,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThisWeek.ViewHolder holder, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView thisweek;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thisweek = itemView.findViewById(R.id.thisweek_text);
        }
        public TextView getThisweek() {
            return thisweek;
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}