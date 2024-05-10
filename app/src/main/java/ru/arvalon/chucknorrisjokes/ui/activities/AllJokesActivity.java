package ru.arvalon.chucknorrisjokes.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.arvalon.chucknorrisjokes.R;
import ru.arvalon.chucknorrisjokes.mvp.model.Joke;
import ru.arvalon.chucknorrisjokes.mvp.model.JokeList;
import ru.arvalon.chucknorrisjokes.mvp.presenter.AllJokesPresenter;
import ru.arvalon.chucknorrisjokes.mvp.views.AllJokesView;
import ru.arvalon.chucknorrisjokes.ui.adapters.JokesAdapter;
import ru.arvalon.chucknorrisjokes.ui.listeners.RecyclerTouchListener;
import ru.arvalon.chucknorrisjokes.vk.App;

public class AllJokesActivity extends MvpAppCompatActivity implements AllJokesView {

    @InjectPresenter
    AllJokesPresenter allJokesPresenter;

    //TextView jokesCount;
    RecyclerView recyclerView;
    ProgressBar jokeLoadProgressBar;

    private JokesAdapter jokesAdapter;
    private boolean isListenerCreated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_all_jokes);

        //jokesCount = findViewById(R.id.jokesCount);
        recyclerView = findViewById(R.id.jokesRecyclerView);
        jokeLoadProgressBar = findViewById(R.id.jokeLoadProgressBar);

        String[] rowList = getResources().getStringArray(R.array.alljokes);

        List<Joke> rowJokeList = new ArrayList<>();

        for (int i=0; i < rowList.length; i++){
            rowJokeList.add(new Joke(i,rowList[i]));
        }

        JokeList jokeList = new JokeList(null, rowJokeList);

        jokesAdapter=new JokesAdapter(jokeList);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration jokeListDecorator = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(jokeListDecorator);

        recyclerView.setAdapter(jokesAdapter);
    }

    @Override
    public void ShowProgress() {
        jokeLoadProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ShowError() {
        jokeLoadProgressBar.setVisibility(View.GONE);
        //jokesCount.setText("REST ERROR");
    }


    @Override
    public void ShowJokes(JokeList jokeList) {
        jokeLoadProgressBar.setVisibility(View.GONE);

        //jokesCount.setText(String.valueOf(jokeList.getValue().size()));

        /*Collections.sort(jokeList.getValue(),(joke1,joke2)->joke1.getId()-joke2.getId());

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
                            Log.d(App.TAG,"new RecyclerTouchListener.ClickListener() onClick");
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

            Log.d(App.TAG,"Listener is created");
            isListenerCreated=true;
        }*/
    }

    public void RefreshJokes() {

        allJokesPresenter.getJokes();
        Log.d(App.TAG,"@OnClick(R.id.refreshJokesButton)");
    }

    @Override
    public void PickUpJoke() {

    }

}
