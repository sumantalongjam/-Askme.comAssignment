package com.sumanta.askme.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sumanta on 5/3/16.
 */
public class DataEntity {
    @SerializedName("label")
    private String label;
    @SerializedName("image")
    private String imageUrl;
    @SerializedName("template")
    private String template;
    @SerializedName("items")
    private ArrayList<ItemEntity> itemList;

    public String getLabel() {
        return label;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTemplate() {
        return template;
    }

    public ArrayList<ItemEntity> getItemList() {
        return itemList;
    }
}
