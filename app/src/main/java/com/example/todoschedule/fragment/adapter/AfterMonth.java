package com.example.todoschedule.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoschedule.R;

public class AfterMonth extends RecyclerView.Adapter<AfterMonth.ViewHolder> implements AssortmentOfDateTime {

    @NonNull
    @Override
    public AfterMonth.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aftermonth,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AfterMonth.ViewHolder holder, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView aftermonth;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            aftermonth = itemView.findViewById(R.id.aftermonth_text);
        }
        public TextView getaftermonth() {
            return aftermonth;
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}