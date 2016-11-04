package ru.arvalon.chucknorrisjokes.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Collection;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arvalon.chucknorrisjokes.R;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeList;
import ru.arvalon.chucknorrisjokes.mvp.presenter.AllJokesPresenter;
import ru.arvalon.chucknorrisjokes.mvp.views.AllJokesView;
import ru.arvalon.chucknorrisjokes.ui.adapters.JokesAdapter;

public class AllJokesActivity extends MvpAppCompatActivity implements AllJokesView {

    @InjectPresenter
    AllJokesPresenter allJokesPresenter;

    @BindView(R.id.jokesCount)TextView jokesCount;
    @BindView(R.id.jokesRecyclerView)RecyclerView recyclerView;

    private JokesAdapter jokesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_all_jokes);
        ButterKnife.bind(this);
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

        Collections.sort(jokeList.getValue(),(joke1,joke2)->joke1.getId()-joke2.getId());

        jokesAdapter=new JokesAdapter(jokeList);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration jokeListDecorator = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(jokeListDecorator);

        recyclerView.setAdapter(jokesAdapter);
    }

    @Override
    public void PickUpJoke() {

    }

}
