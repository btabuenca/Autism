/**
 * Author: Bernardo Tabuenca
 *
 */

package com.example.user.restclient.models;

import com.example.user.restclient.models.Data;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PictogramModel {

    @SerializedName("Error")
    @Expose
    private Boolean error;


    @SerializedName("Code")
    @Expose
    private String code;


    @SerializedName("Message")
    @Expose
    private String message;


    @SerializedName("Data")
    @Expose
    private List<Data> data = null;



    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

}
