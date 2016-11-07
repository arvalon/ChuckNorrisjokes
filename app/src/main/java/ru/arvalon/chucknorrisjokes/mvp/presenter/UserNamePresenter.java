package ru.arvalon.chucknorrisjokes.mvp.presenter;

/**
 * Created by arvalon on 07.11.2016.
 */

public interface UserNamePresenter {
    void getCustomJoke(String firsName, String lastName);
    void validateUserName(String firsName, String lastName);
    void saveUserName(String firsName, String lastName);
    void restoreUserName();
}
