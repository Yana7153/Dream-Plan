package com.example.dreamplan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    private List<String> dateList;
    private CalendarFragment calendarFragment;

    public CalendarAdapter(List<String> dateList, CalendarFragment fragment) {
        this.dateList = dateList;
        this.calendarFragment = fragment;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar, parent, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        String date = dateList.get(position);
        holder.txtDate.setText(date);

        // Handle Date Click to Open Task Popup
        holder.itemView.setOnClickListener(v -> calendarFragment.showTaskPopup(date));
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public static class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
