package com.example.jkbsg.pojos.home;

import com.example.jkbsg.pojos.home.Model;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ModelResult implements Serializable {
    @SerializedName("results")
    private List<Model> results = null;

    public List<Model> getResults() {
        return results;
    }

    public void setResults(List<Model> results) {
        this.results = results;
    }
}
