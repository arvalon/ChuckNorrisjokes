package ru.arvalon.chucknorrisjokes.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.arvalon.chucknorrisjokes.R;
import ru.arvalon.chucknorrisjokes.mvp.presenter.MainActivityPresenter;
import ru.arvalon.chucknorrisjokes.mvp.views.MainActivityView;


/**
 * Main Activity. Add in develop branch.
 */
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
    @OnClick(R.id.randomJokeButton)
    public void ShowRandomJoke() {
        startActivity(new Intent(this,JokeActivity.class));
    }

    @Override
    @OnClick(R.id.customJokeButton)
    public void ShowCustomJoke() {
        startActivity(new Intent(this,UserNameActivity.class));
    }
}
