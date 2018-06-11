/**
 * Author: Bernardo Tabuenca
 *
 */

package com.example.user.restclient.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("pictogram_id")
    @Expose
    private Integer pictogramId;


    @SerializedName("pictogram_name")
    @Expose
    private String pictogramName;


    @SerializedName("pictogram_description")
    @Expose
    private String pictogramDescription;


    @SerializedName("category_name")
    @Expose
    private String categoryName;


    @SerializedName("pictogram_img")
    @Expose
    private String pictogramImg;


    @SerializedName("pictogram_sound")
    @Expose
    private String pictogramSound;



    public Integer getPictogramId() {
        return pictogramId;
    }

    public void setPictogramId(Integer pictogramId) {
        this.pictogramId = pictogramId;
    }

    public String getPictogramName() {
        return pictogramName;
    }

    public void setPictogramName(String pictogramName) {
        this.pictogramName = pictogramName;
    }

    public String getPictogramDescription() {
        return pictogramDescription;
    }

    public void setPictogramDescription(String pictogramDescription) {
        this.pictogramDescription = pictogramDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPictogramImg() {
        return pictogramImg;
    }

    public void setPictogramImg(String pictogramImg) {
        this.pictogramImg = pictogramImg;
    }

    public String getPictogramSound() {
        return pictogramSound;
    }

    public void setPictogramSound(String pictogramSound) {
        this.pictogramSound = pictogramSound;
    }

}
