package ru.arvalon.chucknorrisjokes.mvp.presenter;

import android.util.Log;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.arvalon.chucknorrisjokes.mvp.model.Count;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeList;
import ru.arvalon.chucknorrisjokes.mvp.views.AllJokesView;
import ru.arvalon.chucknorrisjokes.rest.ChuckNorrisAPI;
import ru.arvalon.chucknorrisjokes.rest.ChuckNorrisRestAPI;
import ru.arvalon.chucknorrisjokes.vk.App;

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

        //getViewState().ShowProgress();

        ChuckNorrisAPI api= ChuckNorrisRestAPI.getChuckNorrisRestAPI();
        Call<Count> call = api.GetJokesCount();
        call.enqueue(new Callback<Count>() {
            @Override
            public void onResponse(Call<Count> call, Response<Count> response) {
                /*count=response.body().getValue(); // тут NPE
                ChuckNorrisAPI api2=ChuckNorrisRestAPI.getChuckNorrisRestAPI();
                Call<JokeList> call2=api2.GetAllJokes(count);
                call2.enqueue(new Callback<JokeList>() {
                    @Override
                    public void onResponse(Call<JokeList> call, Response<JokeList> response) {

                        for(int i=0;i<response.body().getValue().size();i++){
                            response.body().getValue().get(i).setJoke(
                                    response.body().getValue().get(i).getJoke().replaceAll("&quot;","\""));
                        }

                        getViewState().ShowJokes(response.body());
                        Log.d(App.TAG,"onResponse end");
                    }

                    @Override
                    public void onFailure(Call<JokeList> call, Throwable t) {
                        getViewState().ShowError();
                    }
                });*/
            }

            @Override
            public void onFailure(Call<Count> call, Throwable t) {
                    //getViewState().ShowError();
            }
        });
    }
    public void showJokes(){

    }
}
