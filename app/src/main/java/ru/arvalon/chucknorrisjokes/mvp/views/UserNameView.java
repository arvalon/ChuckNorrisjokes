package ru.arvalon.chucknorrisjokes.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

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
    void setUserName(String firsName, String lastName);
    void showJoke(String jokeText);
}
