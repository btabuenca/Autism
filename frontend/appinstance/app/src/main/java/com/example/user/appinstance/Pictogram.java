/**
 * Author: Bernardo Tabuenca
 *
 */

package com.example.user.appinstance;

import android.os.Parcel;
import android.os.Parcelable;


public class Pictogram {


    private int id;
    private String details;
    private String pictogram_name;
    private String pictogram_description;
    private String category_name;
    private String pictogram_img;
    private String pictogram_sound;

    public Pictogram() {

    }


    public Pictogram(int id, String details, String pictogram_name, String pictogram_description, String category_name, String pictogram_img, String pictogram_sound) {
        this.id = id;
        this.details = details;
        this.pictogram_name = pictogram_name;
        this.pictogram_description = pictogram_description;
        this.category_name = category_name;
        this.pictogram_img = pictogram_img;
        this.pictogram_sound = pictogram_sound;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPictogram_name() {
        return pictogram_name;
    }

    public void setPictogram_name(String pictogram_name) {
        this.pictogram_name = pictogram_name;
    }

    public String getPictogram_description() {
        return pictogram_description;
    }

    public void setPictogram_description(String pictogram_description) {
        this.pictogram_description = pictogram_description;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getPictogram_img() {
        return pictogram_img;
    }

    public void setPictogram_img(String pictogram_img) {
        this.pictogram_img = pictogram_img;
    }

    public String getPictogram_sound() {
        return pictogram_sound;
    }

    public void setPictogram_sound(String pictogram_sound) {
        this.pictogram_sound = pictogram_sound;
    }


}
