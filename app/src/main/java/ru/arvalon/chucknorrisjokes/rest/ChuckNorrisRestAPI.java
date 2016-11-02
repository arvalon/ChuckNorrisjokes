package ru.arvalon.chucknorrisjokes.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arvalon on 02.11.2016.
 */

public class ChuckNorrisRestAPI {
    public static ChuckNorrisAPI getChuckNorrisRestAPI(){
        String url="http://api.icndb.com/jokes/";

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ChuckNorrisAPI.class);
    }
}
