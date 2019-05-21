
package com.example.sdy51_e4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NotificationTableActivity extends AppCompatActivity {
    LinearLayout notificationTable;
    DatabaseHandler db;
    List<View> viewList;
    List<Notification> notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_table);

        db = new DatabaseHandler(this);

        viewList = new ArrayList<>();
        notificationTable = findViewById(R.id.notificationTable);

        refreshNotificationTable();
    }

    private void refreshNotificationTable() {
        Log.d("Reading: ", "Reading all notifications..");
        notifications = db.getAllNotifications();
        int count = 0;
        for (Notification ntf : notifications) {
            String log = "Id: " + ntf.getId() + " ,Title: " + ntf.getTitle() + " ,Date: " +
                    ntf.getDate() + ",Time:" + ntf.getTime() + ",Category" + ntf.getCategory();
            Log.d("Notification: ", log);

            LinearLayout newNotificationRow = new LinearLayout(this);
            //LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //newNotificationRow.setLayoutParams(param);
            notificationTable.addView(newNotificationRow);
            viewList.add(newNotificationRow);

            TextView title = (TextView) getLayoutInflater().inflate(R.layout.notification_text_view, null);
            TextView date = (TextView) getLayoutInflater().inflate(R.layout.notification_text_view, null);
            TextView time = (TextView) getLayoutInflater().inflate(R.layout.notification_text_view, null);
            TextView category = (TextView) getLayoutInflater().inflate(R.layout.notification_text_view, null);

            Button delete = (Button) getLayoutInflater().inflate(R.layout.delete_button, null);

            delete.setTag(count++);

            title.setText(ntf.getTitle());
            date.setText(ntf.getDate());
            time.setText(ntf.getTime());
            category.setText(ntf.getCategory());

            title.setWidth(370);
            date.setWidth(220);
            time.setWidth(190);
            category.setWidth(210);

            newNotificationRow.addView(category);
            newNotificationRow.addView(date);
            newNotificationRow.addView(time);
            newNotificationRow.addView(title);
            newNotificationRow.addView(delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (View vl : viewList) {
                        ((ViewGroup) vl.getParent()).removeView(vl);
                    }
                    db.deleteNotification(notifications.get((Integer) view.getTag()));
                    viewList.clear();
                    refreshNotificationTable();

                    Toast.makeText(getApplicationContext(), "Η υπενθύμιση διαγράφηκε", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void goBack(View view) {
        startActivity(new Intent(NotificationTableActivity.this, NotificationActivity.class));
    }

    public void exit(View view) {
        startActivity(new Intent(NotificationTableActivity.this, MainActivity.class));
    }
}