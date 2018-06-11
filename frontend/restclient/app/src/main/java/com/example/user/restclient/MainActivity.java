/**
 * Author: Bernardo Tabuenca
 *
 */
package com.example.user.restclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.restclient.models.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {


    EditText etNumPictograms;
    private static final String URL_API = "http://arasaac.perentec.com";

    private PictogramAPIRESTService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNumPictograms = (EditText) findViewById(R.id.numPictograms);

    }


    public void showPictograms(View v){
        Intent intent = new Intent(this, PictogramListActivity.class);
        startActivity(intent);

    }


    public void getRestWSData(View v){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(PictogramAPIRESTService.class);


        int iNumItems = Integer.parseInt(etNumPictograms.getText().toString());


        /**
         * Obtains items from 1 to the id passed as parameter
         */
        for (int i = 1;i<=iNumItems;i++) {
            Call<PictogramModel> call_async = apiService.getPictogramById(Integer.toString(i));

            call_async.enqueue(new Callback<PictogramModel>() {
                @Override
                public void onResponse(Call<PictogramModel> call, Response<PictogramModel> response) {
                    PictogramModel pictoModel = response.body();
                    PictogramRepository pictoRepository = new PictogramRepository(getApplicationContext());
                    Pictogram picto = new Pictogram();
                    if (null != pictoModel) {

                        Data data = (Data)pictoModel.getData().get(0);
                        picto.setPictogram_id(data.getPictogramId());
                        picto.setPictogram_name(data.getPictogramName());
                        picto.setPictogram_description(data.getPictogramDescription());

                        pictoRepository.add(picto);
                    }
                    pictoRepository.close();

                }



                @Override
                public void onFailure(Call<PictogramModel> call, Throwable t) {

                    Toast.makeText(
                            getApplicationContext(),
                            "Somehting failed!",
                            Toast.LENGTH_LONG
                    ).show();


                }

            });
        }
        Toast.makeText(
                getApplicationContext(),
                "Pictogramas loaded succuessfully!",
                Toast.LENGTH_LONG
        ).show();

    }
}
