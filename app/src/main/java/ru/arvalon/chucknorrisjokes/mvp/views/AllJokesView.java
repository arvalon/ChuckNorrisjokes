package ru.arvalon.chucknorrisjokes.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.arvalon.chucknorrisjokes.mvp.model.JokeList;

/**
 * Created by arvalon on 02.11.2016.
 */

public interface AllJokesView extends MvpView {
    void ShowProgress();
    void ShowError();
    void ShowJokes(JokeList jokeList);
    void RefreshJokes();
    void PickUpJoke();
}
