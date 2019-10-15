package com.yaoh.jetpack.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.yaoh.jetpack.room.dao.UserDao;
import com.yaoh.jetpack.room.entity.User;

/**
 * @author yaoh
 * @date Create in 2019-10-11 14:19
 * @description TODO
 */

@Database(entities = {User.class}, version = 1)
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDao getUserDao();

}
