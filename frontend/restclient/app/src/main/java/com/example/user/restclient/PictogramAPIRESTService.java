/**
 * Author: Bernardo Tabuenca
 *
 */

package com.example.user.restclient;

import com.example.user.restclient.models.PictogramModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface PictogramAPIRESTService {

/*    @GET("/api/v2.0/players/{idPlayer}?api_token=HvxMdDUmLbvC6ipG946MHkJr2ODtvpxvcm81mtIU2JSlpxjn9tsmstwcTgmP")
    Call<PlayerModel> getPlayerById(@Path("idPlayer") String idPlayer);*/


    @GET("/API/V1/pictograms/{idPictogram}?x-access-token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEyMzQ1Njc4OSIsImlhdCI6MTUxOTgyNjUzNywiZXhwIjoxNTE5OTEyOTM3fQ.jl7GirlObEghJ9g9oBNaBIBLpxGS67EW-n1f_ftU19g")
    Call<PictogramModel> getPictogramById(@Path("idPictogram") String idPictogram);
}

