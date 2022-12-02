package com.example.todoschedule.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoschedule.R;

public class ThisMonth extends RecyclerView.Adapter<ThisMonth.ViewHolder> implements AssortmentOfDateTime {

    @NonNull
    @Override
    public ThisMonth.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thismonth,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThisMonth.ViewHolder holder, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView thismonth;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thismonth = itemView.findViewById(R.id.thismonth_text);
        }
        public TextView getThismonth() {
            return thismonth;
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}