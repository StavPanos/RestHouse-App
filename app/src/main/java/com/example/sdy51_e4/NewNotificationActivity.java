package com.example.sdy51_e4;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewNotificationActivity extends AppCompatActivity {
    Button selectDateButton, selectTimeButton, submitButton, health, event, care, other;
    TextView date, time;
    EditText title;
    int year, month, dayOfMonth, currentHour, currentMinute;
    String tag;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    DatabaseHandler db;
    List<View> buttonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notification);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        db = new DatabaseHandler(this);

        selectDateButton = findViewById(R.id.selectDateButton);
        date = findViewById(R.id.dateText);
        selectTimeButton = findViewById(R.id.selectTimeButton);
        time = findViewById(R.id.timeText);
        title = findViewById(R.id.titleEditText);
        submitButton = findViewById(R.id.submit);

        health = findViewById(R.id.healthTag);
        care = findViewById(R.id.careTag);
        event = findViewById(R.id.eventTag);
        other = findViewById(R.id.otherTag);

        buttonList = new ArrayList<>();

        buttonList.add(health);
        buttonList.add(care);
        buttonList.add(event);
        buttonList.add(other);
    }

    public void cancel(View view) {
        startActivity(new Intent(NewNotificationActivity.this, NotificationActivity.class));
    }

    public void selectTag(View view) {
        Button selectedButton = findViewById(view.getId());
        tag = selectedButton.getText().toString();
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        for (View temp : buttonList) {
            if (!temp.equals(view)) {
                temp.setBackgroundColor((getResources().getColor(R.color.orange)));
            }
        }
    }

    public void selectDate(View view) {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(NewNotificationActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String selectedDate = day + "/" + (month + 1) + "/" + year;
                        date.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public void selectTime(View view) {
        calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);
        calendar.set(Calendar.SECOND, 0);

        timePickerDialog = new TimePickerDialog(NewNotificationActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                String selectedTime = hourOfDay+":"+minutes;
                time.setText(selectedTime);
            }
        }, currentHour, currentMinute, true);

        timePickerDialog.show();
    }

    public void submitNotification(View view) {
        db.addNotification(new Notification(title.getText().toString(), date.getText().toString(), time.getText().toString(), tag));
        startActivity(new Intent(NewNotificationActivity.this, NotificationTableActivity.class));
    }
}
