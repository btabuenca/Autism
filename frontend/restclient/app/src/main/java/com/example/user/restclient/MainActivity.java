package com.example.user.restclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.restclient.R;
import com.example.user.restclient.models.PlayerModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {


    EditText numeroJugadres;
    //private static final String URL_API = "http://arasaac.perentec.com/API/V1";
    private static final String URL_API = "https://soccer.sportmonks.com";

    private PictogramAPIRESTService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numeroJugadres = (EditText) findViewById(R.id.numeroJugadores);

    }


    public void verJugadores(View v){
        Intent intent = new Intent(this, PictogramListActivity.class);
        startActivity(intent);

    }


    public void obtenerDatos(View v){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(PictogramAPIRESTService.class);
        int cuantos = Integer.parseInt(numeroJugadres.getText().toString());
        consulta(cuantos);

    }

    public  void consulta(int cuantos){

        for (int i = 1;i<=cuantos;i++) {
            Call<PlayerModel> call_async = apiService.getPlayerById(Integer.toString(i));

            call_async.enqueue(new Callback<PlayerModel>() {
                @Override
                public void onResponse(Call<PlayerModel> call, Response<PlayerModel> response) {
                    PlayerModel player = response.body();
                    PictogramRepository pictoRepository = new PictogramRepository(getApplicationContext());
                    Pictogram picto = new Pictogram();
                    if (null != player) {
                        picto.setId(player.getData().getPlayerId());
                        picto.setCommon_name(player.getData().getCommonName());
                        picto.setImage_path(player.getData().getImagePath());
                        picto.setNationality(player.getData().getNationality());
                        picto.setdetails("");
                        pictoRepository.add(picto);
                    }
                    pictoRepository.close();

                }

                @Override
                public void onFailure(Call<PlayerModel> call, Throwable t) {


                }

            });
        }
        Toast.makeText(
                getApplicationContext(),
                "Pictogramas cargados con exito",
                Toast.LENGTH_LONG
        ).show();

    }
}
