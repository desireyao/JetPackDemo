package com.yaoh.jetpack.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author yaoh
 * @date Create in 2019-10-11 14:15
 * @description TODO
 */

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;


}
