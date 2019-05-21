package com.example.sdy51_e4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

public class NewNotificationActivityV2 extends AppCompatActivity {
    EditText title;
    DatePicker date;
    TimePicker time;
    Button submit;
    DatabaseHandler db;
    String tag;
    Button health, event, care, other;
    List<View> buttonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notification_v2);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        db = new DatabaseHandler(this);

        title = findViewById(R.id.titleEditText);
        date = findViewById(R.id.datePicker);
        time = findViewById(R.id.timePicker);
        time.setIs24HourView(true);
        submit = findViewById(R.id.submit);

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
        startActivity(new Intent(NewNotificationActivityV2.this, NotificationActivity.class));
    }

    public void submitNotification(View view) {
        db.addNotification(new Notification(title.getText().toString(), getCurrentDate(), getCurrentTime(), tag));
        startActivity(new Intent(NewNotificationActivityV2.this, NotificationTableActivity.class));
    }

    public String getCurrentDate() {
        return ((date.getMonth() + 1) + "/") +
                date.getDayOfMonth() + "/" +
                date.getYear();
    }

    public String getCurrentTime() {
        return ((time.getCurrentHour()) + ":") +
                time.getCurrentMinute();
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
}
