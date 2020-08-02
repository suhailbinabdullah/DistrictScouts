package com.suhail.bsgbudgam.pojos.facebook;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FacebookFeedResult implements Serializable {
    @SerializedName("data")
    private List<Datum> data = null;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
