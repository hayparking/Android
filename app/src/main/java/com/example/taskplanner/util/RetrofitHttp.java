package com.example.taskplanner.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHttp {
    private Retrofit retrofit;
    private final String BASE_URL ="http://10.0.2.2:8080/";

    /**
     * Conexcion a la api para consumir servicios
     */
    public RetrofitHttp() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Creamos una variable con la cual podremos hacer llamados a retrofir desde los activity sin tener que poner
     * en todos el codigo de instanciacion (arriba)
     * @return
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

}
