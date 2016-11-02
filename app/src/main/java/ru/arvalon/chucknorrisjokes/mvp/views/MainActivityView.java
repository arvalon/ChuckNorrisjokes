package ru.arvalon.chucknorrisjokes.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by arvalon on 02.11.2016.
 */

public interface MainActivityView extends MvpView {
    void ShowAllJokes();
    void ShowRandomJoke();
    void ShowCustomJoke();
}
