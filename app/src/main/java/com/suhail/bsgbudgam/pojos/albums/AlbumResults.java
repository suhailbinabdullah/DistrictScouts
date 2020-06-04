package com.suhail.bsgbudgam.pojos.albums;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AlbumResults implements Serializable {
    @SerializedName("results")
    private List<AlbumModel> results = null;

    public List<AlbumModel> getResults() {
        return results;
    }

    public void setResults(List<AlbumModel> results) {
        this.results = results;
    }
}
