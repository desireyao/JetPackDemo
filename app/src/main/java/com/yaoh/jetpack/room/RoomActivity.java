package com.yaoh.jetpack.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yaoh.jetpack.R;
import com.yaoh.jetpack.room.dao.BookDao;
import com.yaoh.jetpack.room.dao.UserDao;
import com.yaoh.jetpack.room.database.MyRoomDataBase;
import com.yaoh.jetpack.room.entity.Book;
import com.yaoh.jetpack.room.entity.User;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RoomActivity";

    private MyRoomDataBase mRoomDataBase;

    private Button bt_insert;
    private Button bt_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        bt_insert = findViewById(R.id.bt_insert);
        bt_insert.setOnClickListener(this);

        bt_delete = findViewById(R.id.bt_delete);
        bt_delete.setOnClickListener(this);

        mRoomDataBase = Room.databaseBuilder(getApplicationContext(), MyRoomDataBase.class,
                "database.db").build();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_insert) {
            insert();
        } else if (id == R.id.bt_delete) {
            delete();
        }
    }

    private void insert() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserDao userDao = mRoomDataBase.getUserDao();
                User user = new User();
                user.name = "Huawei";
                userDao.insert(user);

                BookDao bookDao = mRoomDataBase.getBookDao();
                Book book = new Book();
                book.bookName = "Android从入门到放弃";
                book.userId = 1;
                bookDao.insert(book);
            }
        }).start();
    }

    private void delete() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                BookDao bookDao = mRoomDataBase.getBookDao();
//                List<Book> books = bookDao.getAllBook();
//                Log.e(TAG, "onQuery all books ---> " + books);
//                bookDao.delete(books.get(0));

                UserDao userDao = mRoomDataBase.getUserDao();
                User user = userDao.getAll().get(0);
                Log.e(TAG, "onQuery user ---> " + user);
                userDao.delete(user);
            }
        }).start();
    }


}
