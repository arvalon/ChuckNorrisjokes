package ru.arvalon.chucknorrisjokes.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.arvalon.chucknorrisjokes.mvp.model.Count;
import ru.arvalon.chucknorrisjokes.mvp.model.Joke;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeList;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeWrapper;

/**
 * Created by arvalon on 02.11.2016.
 */

public interface ChuckNorrisAPI {

    @GET("count")
    Call<Count> GetJokesCount();

    @GET("random")
    Call<JokeWrapper>GetSingleJoke();

    @GET("random/{count}")
    Call<JokeList>GetAllJokes(@Path("count")int count);

    @GET("jokes/{number}")
    Call<Joke>GetJokeByNumber(@Path("number")int number);

    @GET("categories")
    Call<List<String>>GetJokeCategories();

}
