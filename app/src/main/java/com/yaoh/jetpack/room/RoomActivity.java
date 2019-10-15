package com.yaoh.jetpack.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yaoh.jetpack.R;
import com.yaoh.jetpack.room.dao.UserDao;
import com.yaoh.jetpack.room.database.UserDataBase;
import com.yaoh.jetpack.room.entity.User;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_create_db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_create_db = findViewById(R.id.bt_create_db);
        bt_create_db.setOnClickListener(this);

        UserDataBase db = Room.databaseBuilder(getApplicationContext(), UserDataBase.class,
                "database.db").build();
        userDao = db.getUserDao();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_create_db) {
            insert();
        }
    }

    private void insert() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User();
                user.name = "Huawei";
                userDao.insert(user);
            }
        }).start();
    }


}
