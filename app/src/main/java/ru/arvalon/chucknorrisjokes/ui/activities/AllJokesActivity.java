package ru.arvalon.chucknorrisjokes.ui.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arvalon.chucknorrisjokes.R;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeList;
import ru.arvalon.chucknorrisjokes.mvp.presenter.AllJokesPresenter;
import ru.arvalon.chucknorrisjokes.mvp.views.AllJokesView;

public class AllJokesActivity extends MvpAppCompatActivity implements AllJokesView {

    @InjectPresenter
    AllJokesPresenter allJokesPresenter;

    @BindView(R.id.jokesCount)TextView jokesCount;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_all_jokes);
        ButterKnife.bind(this);
        allJokesPresenter.getJokes();
    }

    @Override
    public void ShowProgress() {

    }

    @Override
    public void ShowError() {
        jokesCount.setText("ГРОБ ГРОБ КЛОДБИЩЕ ПИДОР");
    }

    @Override
    public void ShowJokes(JokeList jokeList) {
        jokesCount.setText(String.valueOf(jokeList.getValue().size()));
    }

    @Override
    public void PickUpJoke() {

    }
}
