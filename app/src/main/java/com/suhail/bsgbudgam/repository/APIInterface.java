package com.suhail.bsgbudgam.repository;

import com.suhail.bsgbudgam.pojos.albums.AlbumResults;
import com.suhail.bsgbudgam.pojos.facebook.FacebookFeedResult;
import com.suhail.bsgbudgam.pojos.newsfeed.NewsResults;

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

    @Headers({"Content-Type: application/json"})
    @GET("scoutsFacebookFeed.php")
    Call<FacebookFeedResult> getFacebookFeed();
}