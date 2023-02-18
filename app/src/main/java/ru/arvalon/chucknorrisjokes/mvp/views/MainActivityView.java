package ru.arvalon.chucknorrisjokes.mvp.views;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

/**
 * Created by arvalon on 02.11.2016.
 */
public interface MainActivityView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)void ShowAllJokes();
    @StateStrategyType(AddToEndSingleStrategy.class)void ShowRandomJoke();
    @StateStrategyType(AddToEndSingleStrategy.class)void ShowCustomJoke();
}
