package com.example.sdy51_e4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "test1Notifications";
    private static final String TABLE_NOTIFICATIONS = "notifications";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_CATEGORY = "category";

    DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_NOTIFICATIONS = "CREATE TABLE " + TABLE_NOTIFICATIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_DATE + " TEXT,"
                + KEY_TIME + " TEXT," + KEY_CATEGORY + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE_NOTIFICATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS);

        // Create tables again
        onCreate(db);
    }

    void addNotification(Notification notification) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, notification.getTitle());
        values.put(KEY_DATE, notification.getDate());
        values.put(KEY_TIME, notification.getTime());
        values.put(KEY_CATEGORY, notification.getCategory());
        //inserting row
        db.insert(TABLE_NOTIFICATIONS, null, values);
        //closing database connection
        db.close(); // Closing database connection
    }

    List<Notification> getAllNotifications() {
        List<Notification> contactList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Notification notification = new Notification();
                notification.setId(Integer.parseInt(cursor.getString(0)));
                notification.setTitle(cursor.getString(1));
                notification.setDate(cursor.getString(2));
                notification.setTime(cursor.getString(3));
                notification.setCategory(cursor.getString(4));

                contactList.add(notification);
            } while (cursor.moveToNext());
        }

        return contactList;
    }

    void deleteNotification(Notification notification) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTIFICATIONS, KEY_ID + " = ?",
                new String[] { String.valueOf(notification.getId()) });
        db.close();
    }

    /*public int getNotificationsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NOTIFICATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }*/
}
