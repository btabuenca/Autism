package com.example.user.appinstance;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 28/10/2017.
 */

public class Pictogram {

    private String image_path;
    private String nationality;
    private String common_name;
    private int id;
    private String details;
    public Pictogram() {

    }

    public Pictogram(int id , String details, String image_path , String nationality , String common_name ){
        id = id;
        details = details;
        image_path = image_path;
        nationality = nationality;
        common_name = common_name;
    }


    public int getId(){ return this.id;}
    public void setId(int id){
        this.id = id;
    }
    public String getdetails(){ return this.details;}
    public void setdetails(String details){
        this.details= details;
    }
    public String getCommon_name() {
        return this.common_name;
    }
    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public String getNationality() {
        return this.nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getImage_path() {
        return this.image_path;
    }
    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

}
