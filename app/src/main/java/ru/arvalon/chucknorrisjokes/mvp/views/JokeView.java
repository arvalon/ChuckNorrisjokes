package ru.arvalon.chucknorrisjokes.mvp.views;

import com.arellomobile.mvp.MvpView;

/**
 * Created by arvalon on 06.11.2016.
 */

public interface JokeView extends MvpView {
    void setJoke(String jokeText);
    void showError();
    void showProgress();
    void PostJoke();
}
