package com.example.jkbsg.pojos.newsfeed;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NewsResults implements Serializable {
    @SerializedName("results")
    private List<NewsModel> results = null;

    public List<NewsModel> getResults() {
        return results;
    }

    public void setResults(List<NewsModel> results) {
        this.results = results;
    }
}
