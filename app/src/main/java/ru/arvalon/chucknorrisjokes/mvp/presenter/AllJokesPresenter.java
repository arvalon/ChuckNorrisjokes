package ru.arvalon.chucknorrisjokes.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.arvalon.chucknorrisjokes.mvp.model.Count;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeList;
import ru.arvalon.chucknorrisjokes.mvp.views.AllJokesView;
import ru.arvalon.chucknorrisjokes.rest.ChuckNorrisAPI;
import ru.arvalon.chucknorrisjokes.rest.ChuckNorrisRestAPI;

/**
 * Created by arvalon on 02.11.2016.
 */

@InjectViewState
public class AllJokesPresenter extends MvpPresenter<AllJokesView> {

    private int count;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getJokes();
    }

    public void getJokes(){

        getViewState().ShowProgress();

        ChuckNorrisAPI api= ChuckNorrisRestAPI.getChuckNorrisRestAPI();
        Call<Count> call = api.GetJokesCount();
        call.enqueue(new Callback<Count>() {
            @Override
            public void onResponse(Call<Count> call, Response<Count> response) {
                count=response.body().getValue();
                ChuckNorrisAPI api2=ChuckNorrisRestAPI.getChuckNorrisRestAPI();
                Call<JokeList> call2=api2.GetAllJokes(count);
                call2.enqueue(new Callback<JokeList>() {
                    @Override
                    public void onResponse(Call<JokeList> call, Response<JokeList> response) {
                        getViewState().ShowJokes(response.body());
                        Log.d("happy","Load data");
                    }

                    @Override
                    public void onFailure(Call<JokeList> call, Throwable t) {
                        getViewState().ShowError();
                    }
                });
            }

            @Override
            public void onFailure(Call<Count> call, Throwable t) {
                    getViewState().ShowError();
            }
        });
    }
    public void showJokes(){

    }
}
