
package com.example.user.restclient.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerModel {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String toString(){
        return data.toString();
    }

}