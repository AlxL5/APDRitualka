package com.alxl5.apdritualka.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.alxl5.apdritualka.models.User;

import java.util.ArrayList;
import java.util.List;

public class DBUser {

    DBHelper dbHelper;
    Context context;
    Cursor cursor;
    SQLiteDatabase db;
    List<User> userList;

    public String tableName = "user";

    public DBUser(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public int getCount() {
        db = dbHelper.getReadableDatabase();
        cursor = db.query(tableName, null, null, null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public User getUser() {
        this.truncateUser();
        cursor = db.query(tableName, null, null, null, null, null, null);
        userList = new ArrayList<>();
        cursor.moveToFirst();
        int name = cursor.getColumnIndex("name");
        int surname = cursor.getColumnIndex("surname");
        int phone = cursor.getColumnIndex("phone");
        int email = cursor.getColumnIndex("email");
        User u = new User(cursor.getString(name), cursor.getString(surname), cursor.getString(phone), cursor.getString(email));
        userList.add(u);
        cursor.close();
        for (User user : userList) {
            return user;
        }
        return null;
    }

    public long insertUser(User user) {
        long id;
        if (getCount() < 1) {
            ContentValues values = new ContentValues();
            values.put("name", user.getName());
            values.put("surname", user.getSurname());
            values.put("phone", user.getPhone());
            values.put("email", user.getEmail());
            id = db.insert("user", null, values);
        } else id = 0;
        return id;
    }

    public void truncateUser() {
        String sql = "DELETE FROM " + tableName + "; REINDEX " + tableName + "; VACUUM";
        db.execSQL(sql);
    }
}
