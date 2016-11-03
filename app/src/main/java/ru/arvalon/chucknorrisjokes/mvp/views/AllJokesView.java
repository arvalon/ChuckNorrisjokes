package ru.arvalon.chucknorrisjokes.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.arvalon.chucknorrisjokes.mvp.model.Count;
import ru.arvalon.chucknorrisjokes.mvp.model.Joke;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeList;

/**
 * Created by arvalon on 02.11.2016.
 */

public interface AllJokesView extends MvpView {
    void ShowProgress();
    void ShowError();
    void ShowJokes(JokeList jokeList);
    void PickUpJoke();
}
