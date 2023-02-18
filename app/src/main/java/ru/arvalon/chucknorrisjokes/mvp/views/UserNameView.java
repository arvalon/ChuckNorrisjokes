package ru.arvalon.chucknorrisjokes.mvp.views;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

/**
 * Created by arvalon on 07.11.2016.
 */

@StateStrategyType(SkipStrategy.class)
public interface UserNameView extends MvpView {
    void getJoke();
    void setFirstNameError();
    void setLastnameError();
    void showProgress();
    void showConnectionError();
    void setUserName();
    void saveUserName(String firstName, String lastName);
    void showJoke(String jokeText);
}
