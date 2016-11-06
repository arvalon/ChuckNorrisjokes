package ru.arvalon.chucknorrisjokes.mvp.presenter;

/**
 * Created by arvalon on 06.11.2016.
 */

public interface JokePresenter {
    void postJoke(String jokeText);
    void getRundomJoke();
    void setJoke(String jokeText);
}
