package com.yaoh.jetpack.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * @author yaoh
 * @date Create in 2019-10-15 15:52
 * @description TODO
 */

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "uid",
        childColumns = "user_id",onDelete=ForeignKey.CASCADE))
public class Book {

    @PrimaryKey(autoGenerate = true)
    public int bookId;

    @ColumnInfo(name = "user_id")
    public int userId;

    @ColumnInfo(name = "book_name")
    public String bookName;


    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", userId=" + userId +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
