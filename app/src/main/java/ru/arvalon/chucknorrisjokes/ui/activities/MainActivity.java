package ru.arvalon.chucknorrisjokes.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arvalon.chucknorrisjokes.R;
import ru.arvalon.chucknorrisjokes.mvp.presenter.MainActivityPresenter;
import ru.arvalon.chucknorrisjokes.mvp.views.MainActivityView;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView{

    @InjectPresenter
    MainActivityPresenter mainActivityPresenter;

    @BindView(R.id.allJokesButton)Button allJokes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        allJokes.setOnClickListener(view->ShowAllJokes());

    }

    @Override
    public void ShowAllJokes() {
        startActivity(new Intent(this,AllJokesActivity.class));
        mainActivityPresenter.AllJokes();
    }

    @Override
    public void ShowRandomJoke() {

    }

    @Override
    public void ShowCustomJoke() {

    }

}
