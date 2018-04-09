package ru.arvalon.chucknorrisjokes.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.arvalon.chucknorrisjokes.mvp.views.MainActivityView;
import ru.arvalon.chucknorrisjokes.vk.App;


/**
 * Created by arvalon on 02.11.2016.
 */

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {
    public MainActivityPresenter() {
        Log.d(App.TAG,"MainActivityPresenter Constructor");
    }

    public void AllJokes(){
        Log.d(App.TAG,"MainActivityPresenter-AllJokes");
    }
}
