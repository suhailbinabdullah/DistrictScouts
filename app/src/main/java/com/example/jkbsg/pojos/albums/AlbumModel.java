package com.example.jkbsg.pojos.albums;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AlbumModel implements Serializable {

    @SerializedName("albumId")
    private String albumId;

    @SerializedName("albumName")
    private String albumName;

    @SerializedName("albumImageUrl")
    private String albumImageUrl;

    @SerializedName("albumDescription")
    private String albumDescription;

    @SerializedName("albumData")
    private List<AlbumData> albumData = null;

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumImageUrl() {
        return albumImageUrl;
    }

    public void setAlbumImageUrl(String albumImageUrl) {
        this.albumImageUrl = albumImageUrl;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }

    public List<AlbumData> getAlbumData() {
        return albumData;
    }

    public void setAlbumData(List<AlbumData> albumData) {
        this.albumData = albumData;
    }
}
