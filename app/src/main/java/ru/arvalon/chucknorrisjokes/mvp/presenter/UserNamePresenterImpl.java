package ru.arvalon.chucknorrisjokes.mvp.presenter;

import android.content.Intent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeWrapper;
import ru.arvalon.chucknorrisjokes.mvp.views.UserNameView;
import ru.arvalon.chucknorrisjokes.rest.ChuckNorrisAPI;
import ru.arvalon.chucknorrisjokes.rest.ChuckNorrisRestAPI;
import ru.arvalon.chucknorrisjokes.ui.activities.JokeActivity;

/**
 * Created by arvalon on 07.11.2016.
 */

@InjectViewState
public class UserNamePresenterImpl extends MvpPresenter<UserNameView> implements UserNamePresenter {

    @Override
    public void getCustomJoke(String firsName, String lastName) {
        // TODO: 07.11.2016 retrofit is else view.showJoke (start new activity) or showConnection Erorr
        getViewState().showProgress();
        ChuckNorrisAPI api= ChuckNorrisRestAPI.getChuckNorrisRestAPI();
        Call<JokeWrapper>call=api.GetPersonajJoke(firsName,lastName);
        call.enqueue(new Callback<JokeWrapper>() {
            @Override
            public void onResponse(Call<JokeWrapper> call, Response<JokeWrapper> response) {
                getViewState().showJoke(response.body().getValue().getJoke());
            }

            @Override
            public void onFailure(Call<JokeWrapper> call, Throwable t) {
                getViewState().showConnectionError();
            }
        });
    }

    @Override
    public void validateUserName(String firsName, String lastName) {
        if(firsName.length()==0){
            getViewState().setFirstNameError();
        }else if(lastName.length()==0){
            getViewState().setLastnameError();
        }else{
            saveUserName(firsName,lastName);
            getCustomJoke(firsName,lastName);
        }
    }

    @Override
    public void saveUserName(String firsName, String lastName) {

    }

    @Override
    public void restoreUserName() {

    }
}
