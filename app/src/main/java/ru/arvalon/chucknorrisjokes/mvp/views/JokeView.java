package ru.arvalon.chucknorrisjokes.mvp.views;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

/**
 * Created by arvalon on 06.11.2016.
 */

public interface JokeView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class) void vkAuthorize();

    @StateStrategyType(AddToEndSingleStrategy.class) void setJoke(String jokeText);

    @StateStrategyType(AddToEndSingleStrategy.class) void showError();
    @StateStrategyType(AddToEndSingleStrategy.class) void showProgress();
    @StateStrategyType(AddToEndSingleStrategy.class)void PostButton();
    @StateStrategyType(AddToEndSingleStrategy.class) void ShowFinalDialog();
}
