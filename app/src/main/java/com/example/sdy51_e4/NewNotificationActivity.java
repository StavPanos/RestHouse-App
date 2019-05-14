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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class NewNotificationActivity extends AppCompatActivity {
    Button selectDateButton, selectTimeButton, submitButton;
    TextView date, time;
    EditText title;
    RadioGroup radioGroup;
    RadioButton category;
    int year;
    int month;
    int dayOfMonth;
    int currentHour;
    int currentMinute;
    String amPm;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    DatabaseHandler db;

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
        radioGroup = findViewById(R.id.categoryTags);
        submitButton = findViewById(R.id.submit);

        selectDateButtonSetListener();
        selectTimeButtonSetListener();
        submitButtonSetListener();

    }

    private void selectDateButtonSetListener() {
        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(NewNotificationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
    }

    private void selectTimeButtonSetListener() {
        selectTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(NewNotificationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        time.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });
    }

    private void submitButtonSetListener() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                category = findViewById(radioId);
                db.addNotification(new Notification(title.getText().toString(),date.getText().toString(),time.getText().toString(),category.getText().toString()));
                startActivity(new Intent(NewNotificationActivity.this, NotificationTableActivity.class));
            }
        });
    }

    public void cancel(View view) {
        startActivity(new Intent(NewNotificationActivity.this, NotificationActivity.class));
    }
}
