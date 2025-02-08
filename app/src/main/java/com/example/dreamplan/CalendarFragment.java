package com.example.dreamplan;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment {

    private RecyclerView recyclerViewCalendar;
    private TextView txtMotivation, txtTaskSchedule, txtPopupTasks;
    private View taskPopup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        // Initialize Views
        txtMotivation = view.findViewById(R.id.txtMotivation);
        txtTaskSchedule = view.findViewById(R.id.txtTaskSchedule);
        recyclerViewCalendar = view.findViewById(R.id.recyclerViewCalendar);
        taskPopup = view.findViewById(R.id.taskPopup);
        txtPopupTasks = view.findViewById(R.id.txtPopupTasks);

        // Set Motivational Text (Can be dynamic/random)
        txtMotivation.setText("Stay consistent, your future self will thank you!");

        // Set Up Calendar RecyclerView
        recyclerViewCalendar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        CalendarAdapter calendarAdapter = new CalendarAdapter(generateDates(), this);
        recyclerViewCalendar.setAdapter(calendarAdapter);

        return view;
    }

    // Generate Dates for the Horizontal Calendar
    private List<String> generateDates() {
        List<String> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 14; i++) {  // Show 14 days (2 weeks)
            dates.add(calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dates;
    }

    // Show Task Popup on Date Click
    public void showTaskPopup(String date) {
        txtPopupTasks.setText("Tasks for " + date + ":\n- Example Task 1\n- Example Task 2");
        taskPopup.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), "Showing tasks for " + date, Toast.LENGTH_SHORT).show();
    }
}
