package ru.arvalon.chucknorrisjokes.ui.activities;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.arvalon.chucknorrisjokes.R;
import ru.arvalon.chucknorrisjokes.mvp.model.Count;
import ru.arvalon.chucknorrisjokes.mvp.model.Joke;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeList;
import ru.arvalon.chucknorrisjokes.mvp.presenter.AllJokesPresenter;
import ru.arvalon.chucknorrisjokes.mvp.views.AllJokesView;
import ru.arvalon.chucknorrisjokes.rest.ChuckNorrisAPI;
import ru.arvalon.chucknorrisjokes.rest.ChuckNorrisRestAPI;

public class AllJokesActivity extends MvpAppCompatActivity implements AllJokesView {

    @InjectPresenter
    AllJokesPresenter allJokesPresenter;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_all_jokes);

        ChuckNorrisAPI api= ChuckNorrisRestAPI.getChuckNorrisRestAPI();
        Call<Count> call=api.GetJokesCount();
        call.enqueue(new Callback<Count>() {
            @Override
            public void onResponse(Call<Count> call, Response<Count> response) {
                count=response.body().getValue();

                Log.d("happy","Count: "+response.body().getValue());

                ChuckNorrisAPI api2= ChuckNorrisRestAPI.getChuckNorrisRestAPI();

                Call<JokeList> call2=api2.GetAllJokes(count);
                call2.enqueue(new Callback<JokeList>() {
                    @Override
                    public void onResponse(Call<JokeList> call, Response<JokeList> response) {
                        Log.d("happy","Joke count in List: "+response.body().getValue().size());
                        for(Joke joke: response.body().getValue()){
                            //Log.d("happy",joke.getJoke());
                        }
                    }

                    @Override
                    public void onFailure(Call<JokeList> call, Throwable t) {
                        Log.d("happy",t.toString());
                    }
                });
            }

            @Override
            public void onFailure(Call<Count> call, Throwable t) {
                Log.d("happy",t.toString());
            }
        });
    }

    @Override
    public void setJokeCount(Count count) {

    }

    @Override
    public void setJokesList(List<Joke> jokes) {

    }

    @Override
    public void ShowProgress() {

    }

    @Override
    public void ShowError() {

    }

    @Override
    public void PickUpJoke() {

    }
}
