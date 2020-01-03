package com.yaoh.jetpack.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.yaoh.jetpack.room.entity.Book;
import com.yaoh.jetpack.room.entity.User;

import java.util.List;

/**
 * @author yaoh
 * @date Create in 2019-10-11 14:16
 * @description TODO
 */

@Dao
public interface BookDao {

    @Query("SELECT * FROM Book")
    List<Book> getAllBook();

    @Insert
    void insert(Book book);

    @Delete()
    void delete(Book book);

}
