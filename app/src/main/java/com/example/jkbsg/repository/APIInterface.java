package com.example.jkbsg.repository;

import com.example.jkbsg.pojos.albums.AlbumResults;
import com.example.jkbsg.pojos.newsfeed.NewsResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIInterface {
    @Headers({"Content-Type: application/json"})
    @GET("budgam_scouts_albums.php?")
    Call<AlbumResults> getVideoAlbumsAndData(@Query("mediaType") String mediaType);

    @Headers({"Content-Type: application/json"})
    @GET("budgam_scouts_albums.php?")
    Call<AlbumResults> getPhotoAlbumsAndData(@Query("mediaType") String mediaType);

    @Headers({"Content-Type: application/json"})
    @GET("newsfeed.php?")
    Call<NewsResults> getNewsFeedData();
}