package com.example.sdy51_e4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class NotificationActivity extends AppCompatActivity {
    ToggleButton version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        version = findViewById(R.id.version);
    }

    public void newNotification(View view) {
        if (version.getText().equals("v1")) {
            startActivity(new Intent(NotificationActivity.this, NewNotificationActivity.class));
        }
        else{
            startActivity(new Intent(NotificationActivity.this, NewNotificationActivityV2.class));
        }
    }

    public void showNotifications(View view) {
        startActivity(new Intent(NotificationActivity.this, NotificationTableActivity.class));
    }
}
