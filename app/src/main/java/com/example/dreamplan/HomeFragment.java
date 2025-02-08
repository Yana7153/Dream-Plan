package com.example.dreamplan;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private FloatingActionButton btnAddSection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize UI elements
        btnAddSection = view.findViewById(R.id.btnAddSection);

        // Handle button click
        btnAddSection.setOnClickListener(v -> showAddSectionDialog());

        return view;
    }

    private void showAddSectionDialog() {
        // Create the dialog using the custom layout
        Dialog sectionDialog = new Dialog(getContext());
        sectionDialog.setContentView(R.layout.task_input_dialog);  // Use the custom layout

        // Initialize the views
        EditText sectionName = sectionDialog.findViewById(R.id.et_section_name);
        DatePicker datePicker = sectionDialog.findViewById(R.id.date_picker);
        Spinner prioritySpinner = sectionDialog.findViewById(R.id.spinner_priority);
        CheckBox remindersCheckbox = sectionDialog.findViewById(R.id.cb_reminders);
        EditText notes = sectionDialog.findViewById(R.id.et_notes);
        Button saveSectionButton = sectionDialog.findViewById(R.id.btn_save_section);

        // Calendar Box Views
        LinearLayout llCalendarBox = sectionDialog.findViewById(R.id.llCalendarBox);
        LinearLayout llCalendarDetails = sectionDialog.findViewById(R.id.ll_calendar_details);
        TextView tvCalendarTitle = sectionDialog.findViewById(R.id.tv_calendar_title);

        // Set up the click listener for the Calendar box title
        tvCalendarTitle.setOnClickListener(v -> toggleCalendarDetails(llCalendarDetails));

        // Save section button click
        saveSectionButton.setOnClickListener(v -> {
            String name = sectionName.getText().toString().trim();
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();
            String priority = prioritySpinner.getSelectedItem().toString();
            boolean reminders = remindersCheckbox.isChecked();
            String notesText = notes.getText().toString().trim();

            // Validate inputs
            if (name.isEmpty()) {
                Toast.makeText(getContext(), "Section Name is required!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Optionally save the section data to a list, database, or other storage
            String dueDate = month + "/" + day + "/" + year; // Format the due date (mm/dd/yyyy)

            // For now, just show a Toast to confirm the data
            Toast.makeText(getContext(), "Section Saved: " + name + "\nDue Date: " + dueDate + "\nPriority: " + priority, Toast.LENGTH_SHORT).show();

            // Close the dialog
            sectionDialog.dismiss();
        });

        // Show the dialog
        sectionDialog.show();
    }


    private void toggleCalendarDetails(LinearLayout llCalendarDetails) {
        if (llCalendarDetails.getVisibility() == View.VISIBLE) {
            llCalendarDetails.setVisibility(View.GONE);
        } else {
            llCalendarDetails.setVisibility(View.VISIBLE);
        }
    }
}
