package ru.arvalon.chucknorrisjokes.mvp.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.arvalon.chucknorrisjokes.mvp.views.MainActivityView;


/**
 * Created by arvalon on 02.11.2016.
 */

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {
    public MainActivityPresenter() {
        Log.d("happy","MainActivityPresenter Constructor");
    }

    public void AllJokes(){
        Log.d("happy","MainActivityPresenter-AllJokes");
    }
}
