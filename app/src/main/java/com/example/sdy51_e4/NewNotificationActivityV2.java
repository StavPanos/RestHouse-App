package com.example.sdy51_e4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class NewNotificationActivityV2 extends AppCompatActivity {
    EditText title;
    Spinner category;
    DatePicker date;
    TimePicker time;
    Button submit;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notification_v2);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        db = new DatabaseHandler(this);

        title = findViewById(R.id.titleEditText);
        category = findViewById(R.id.spinner);
        date = findViewById(R.id.datePicker);

        time = findViewById(R.id.timePicker);
        time.setIs24HourView(true);
        submit = findViewById(R.id.submit);

    }

    public void cancel(View view) {
        startActivity(new Intent(NewNotificationActivityV2.this, NotificationActivity.class));
    }

    public void submitNotification(View view) {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addNotification(new Notification(title.getText().toString(),getCurrentDate(),getCurrentTime(),""));
                startActivity(new Intent(NewNotificationActivityV2.this, NotificationTableActivity.class));
            }
        });
    }

    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();
        builder.append((date.getMonth() + 1)+"/");
        builder.append(date.getDayOfMonth()+"/");
        builder.append(date.getYear());
        return builder.toString();
    }

    public String getCurrentTime(){
        StringBuilder builder=new StringBuilder();
        builder.append((time.getCurrentHour())+":");
        builder.append(time.getCurrentMinute());
        return builder.toString();
    }
}
