package com.example.jkbsg.pojos;

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
