package com.suhail.bsgbudgam.pojos.albums;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AlbumData implements Serializable {

    @SerializedName("dataId")
    private String dataId;

    @SerializedName("dataUrl")
    private String dataUrl;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
}
