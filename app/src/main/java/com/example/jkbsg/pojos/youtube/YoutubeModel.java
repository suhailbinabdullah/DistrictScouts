package com.example.jkbsg.pojos.youtube;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class YoutubeModel implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("youtubeAlbumName")
    private String youtubeAlbumName;

    @SerializedName("videoId")
    private String videoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYoutubeAlbumName() {
        return youtubeAlbumName;
    }

    public void setYoutubeAlbumName(String youtubeAlbumName) {
        this.youtubeAlbumName = youtubeAlbumName;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
