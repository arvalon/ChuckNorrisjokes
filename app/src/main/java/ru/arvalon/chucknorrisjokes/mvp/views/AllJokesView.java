package ru.arvalon.chucknorrisjokes.mvp.views;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.arvalon.chucknorrisjokes.mvp.model.Count;
import ru.arvalon.chucknorrisjokes.mvp.model.Joke;

/**
 * Created by arvalon on 02.11.2016.
 */

public interface AllJokesView extends MvpView {
    void setJokeCount(Count count);
    void setJokesList(List<Joke> jokes);
    void ShowProgress();
    void ShowError();
    void PickUpJoke();
}
