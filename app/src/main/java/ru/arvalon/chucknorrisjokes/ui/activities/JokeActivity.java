package ru.arvalon.chucknorrisjokes.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arvalon.chucknorrisjokes.R;
import ru.arvalon.chucknorrisjokes.mvp.presenter.JokePresenter;
import ru.arvalon.chucknorrisjokes.mvp.presenter.JokePresenterImpl;
import ru.arvalon.chucknorrisjokes.mvp.views.JokeView;

public class JokeActivity extends MvpAppCompatActivity implements JokeView {

    private static final String joke = "joke";

    @InjectPresenter
    JokePresenterImpl myJokePresenter;

    @BindView(R.id.jokeText)TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        Log.d("happy","JokeActivity onCreate");
        if(intent.hasExtra(joke)){
                Log.d("happy","intent.hasExtra(joke)");
                if (intent.getStringExtra(joke).length()!=0){
                    Log.d("happy","intent.getStringExtra(joke).length()!=0");
                    setJoke(intent.getStringExtra(joke));
                    myJokePresenter.isJokeSet=true;
                }
        }myJokePresenter.getRundomJoke();
    }

    @Override
    public void setJoke(String jokeText) {
        textView.setText(jokeText);
        Log.d("happy","JokeActivity setJoke");
    }

    @Override
    public void showError() {
        textView.setText("ERROR");
    }

    @Override
    public void showProgress() {
        textView.setText("wait...");
    }

    @Override
    public void PostJoke() {

    }
}
