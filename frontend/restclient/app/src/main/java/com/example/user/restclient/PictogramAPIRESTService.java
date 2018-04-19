package com.example.user.restclient;

import com.example.user.restclient.models.PlayerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by User on 28/10/2017.
 */

public interface PictogramAPIRESTService {

    @GET("/api/v2.0/players/{idPlayer}?api_token=HvxMdDUmLbvC6ipG946MHkJr2ODtvpxvcm81mtIU2JSlpxjn9tsmstwcTgmP")
    Call<PlayerModel> getPlayerById(@Path("idPlayer") String idPlayer);
}

