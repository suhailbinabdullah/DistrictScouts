package com.example.jkbsg.pojos.youtube;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class YoutubeResults implements Serializable {
    @SerializedName("results")
    private List<YoutubeModel> youtubeModelResults = null;

    public List<YoutubeModel> getYoutubeModelResults() {
        return youtubeModelResults;
    }

    public void setYoutubeModelResults(List<YoutubeModel> youtubeModelResults) {
        this.youtubeModelResults = youtubeModelResults;
    }
}
