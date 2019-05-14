package com.example.sdy51_e4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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

        viewList = new ArrayList<View>();
        notificationTable = findViewById(R.id.notificationTable);

        refreshNotificationTable();
    }

    private void refreshNotificationTable(){
        Log.d("Reading: ", "Reading all notifications..");
        notifications = db.getAllNotifications();
        int count = 0;
        for (Notification ntf : notifications) {
            String log = "Id: " + ntf.getId() + " ,Title: " + ntf.getTitle() + " ,Date: " +
                    ntf.getDate()+ ",Time:" + ntf.getTime() + ",Category" + ntf.getCategory();
            Log.d("Notification: ", log);

            LinearLayout newNotificationRow = new LinearLayout(this);
            notificationTable.addView(newNotificationRow);
            viewList.add(newNotificationRow);

            TextView title = (TextView) getLayoutInflater().inflate(R.layout.notification_text_view, null);
            TextView date = (TextView) getLayoutInflater().inflate(R.layout.notification_text_view, null);
            TextView time = (TextView) getLayoutInflater().inflate(R.layout.notification_text_view, null);
            TextView category = (TextView) getLayoutInflater().inflate(R.layout.notification_text_view, null);

            Button delete = (Button) getLayoutInflater().inflate(R.layout.delete_button, null);
            Button edit = (Button) getLayoutInflater().inflate(R.layout.edit_button, null);

            delete.setTag(count);
            edit.setTag(count++);

            title.setWidth(700);
            date.setWidth(450);
            time.setWidth(400);
            category.setWidth(400);

            title.setText(ntf.getTitle());
            date.setText(ntf.getDate());
            time.setText(ntf.getTime());
            category.setText(ntf.getCategory());

            newNotificationRow.addView(category);
            newNotificationRow.addView(date);
            newNotificationRow.addView(time);
            newNotificationRow.addView(title);
            newNotificationRow.addView(delete);
            newNotificationRow.addView(edit);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (View vl : viewList) {
                        ((ViewGroup) vl.getParent()).removeView(vl);
                    }
                    db.deleteNotification(notifications.get((Integer) view.getTag()));
                    viewList.clear();
                    refreshNotificationTable();

                    Toast.makeText(getApplicationContext(),"Η υπενθύμιση διαγράφηκε",Toast.LENGTH_SHORT).show();
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //PopupWindow popw = new PopupWindow(new LinearLayout(con));

                }
            });
        }
    }

}
