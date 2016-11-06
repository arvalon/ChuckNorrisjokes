package ru.arvalon.chucknorrisjokes.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.arvalon.chucknorrisjokes.R;
import ru.arvalon.chucknorrisjokes.mvp.model.Joke;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeList;
import ru.arvalon.chucknorrisjokes.mvp.presenter.AllJokesPresenter;
import ru.arvalon.chucknorrisjokes.mvp.views.AllJokesView;
import ru.arvalon.chucknorrisjokes.ui.adapters.JokesAdapter;
import ru.arvalon.chucknorrisjokes.ui.listeners.RecyclerTouchListener;

public class AllJokesActivity extends MvpAppCompatActivity implements AllJokesView {

    @InjectPresenter
    AllJokesPresenter allJokesPresenter;

    @BindView(R.id.jokesCount)TextView jokesCount;
    @BindView(R.id.jokesRecyclerView)RecyclerView recyclerView;
    @BindView(R.id.jokeLoadProgressBar)ProgressBar jokeLoadProgressBar;

    private JokesAdapter jokesAdapter;
    private boolean isListenerCreated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_all_jokes);
        ButterKnife.bind(this);
    }

    @Override
    public void ShowProgress() {
        jokeLoadProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ShowError() {
        jokeLoadProgressBar.setVisibility(View.GONE);
        jokesCount.setText("ГРОБ ГРОБ КЛОДБИЩЕ ПИДОР");
    }


    @Override
    public void ShowJokes(JokeList jokeList) {
        jokeLoadProgressBar.setVisibility(View.GONE);
        jokesCount.setText(String.valueOf(jokeList.getValue().size()));

        Collections.sort(jokeList.getValue(),(joke1,joke2)->joke1.getId()-joke2.getId());

        jokesAdapter=new JokesAdapter(jokeList);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration jokeListDecorator = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(jokeListDecorator);

        recyclerView.setAdapter(jokesAdapter);

        if(!isListenerCreated){
            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(
                    getApplicationContext(),
                    recyclerView,
                    new RecyclerTouchListener.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            Log.d("happy","new RecyclerTouchListener.ClickListener() onClick");
                            Joke joke=jokeList.getValue().get(position);
                            Intent intent=new Intent(getApplicationContext(),JokeActivity.class);
                            intent.putExtra("joke",joke.getJoke());
                            startActivity(intent);
                        }

                        @Override
                        public void onLongClick(View view, int position) {
                            Toast.makeText(getApplicationContext(),
                                    "Ты что экран продавить хочешь?",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
            ));

            Log.d("happy","Listener is created");
            isListenerCreated=true;
        }
    }

    @OnClick(R.id.refreshJokesButton)
    public void RefreshJokes() {

        allJokesPresenter.getJokes();
        Log.d("happy","@OnClick(R.id.refreshJokesButton)");
    }

    @Override
    public void PickUpJoke() {

    }

}
