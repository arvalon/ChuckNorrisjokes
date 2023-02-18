package ru.arvalon.chucknorrisjokes.mvp.views;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeList;

/**
 * Created by arvalon on 02.11.2016.
 */

public interface AllJokesView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class) void ShowProgress();

    @StateStrategyType(AddToEndSingleStrategy.class) void ShowError();

    @StateStrategyType(AddToEndSingleStrategy.class) void ShowJokes(JokeList jokeList);

    @StateStrategyType(AddToEndSingleStrategy.class) void RefreshJokes();

    @StateStrategyType(AddToEndSingleStrategy.class) void PickUpJoke();
}
