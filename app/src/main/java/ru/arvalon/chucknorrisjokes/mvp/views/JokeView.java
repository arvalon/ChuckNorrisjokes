package ru.arvalon.chucknorrisjokes.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by arvalon on 06.11.2016.
 */

public interface JokeView extends MvpView {
    void vkAuthorize();
    void setJoke(String jokeText);
    void showError();
    void showProgress();
    void PostButton();
    void ShowFinalDialog();
}
