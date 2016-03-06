package com.sumanta.askme.entities;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by sumanta on 5/3/16.
 */
public class ItemEntity implements Serializable {
    @SerializedName("label")
    private String label;
    @SerializedName("image")
    private String imageUrl;
    @SerializedName("web-url")
    private String webUrl;

    public String getLabel() {
        return label;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
