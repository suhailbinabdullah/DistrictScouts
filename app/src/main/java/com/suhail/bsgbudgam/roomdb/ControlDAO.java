package com.suhail.bsgbudgam.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.suhail.bsgbudgam.roomdb.tables.NewsFeed;

import java.util.List;

@Dao
public interface ControlDAO {

    @Query("Select * from NewsFeed")
    List<NewsFeed> getAllNewsFeedPosts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewsfeedData(List<NewsFeed> posts);

    @Query("Delete from NewsFeed")
    void deleteAllOfflinePosts();

}

