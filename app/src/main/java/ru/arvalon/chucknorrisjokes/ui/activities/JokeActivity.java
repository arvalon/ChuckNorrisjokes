package ru.arvalon.chucknorrisjokes.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    @BindView(R.id.jokeLoadProgressBar)ProgressBar jokeLoadProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        ButterKnife.bind(this);

        Log.d("happy","myJokePresenter.randomViewMode: "+myJokePresenter.randomViewMode);

        Intent intent=getIntent();
        Log.d("happy","JokeActivity onCreate");
        if(intent.hasExtra(joke)){
                Log.d("happy","intent.hasExtra(joke)");
                if (intent.getStringExtra(joke).length()!=0){
                    Log.d("happy","intent.getStringExtra(joke).length()!=0");
                    setJoke(intent.getStringExtra(joke));
                    myJokePresenter.isJokeSet=true;
                    myJokePresenter.randomViewMode=false;
                }
        }myJokePresenter.getRundomJoke();
    }

    @Override
    public void setJoke(String jokeText) {
        textView.setText(jokeText);
        Log.d("happy","JokeActivity setJoke");
        jokeLoadProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        textView.setText("ERROR");
        jokeLoadProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        jokeLoadProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void PostJoke() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("happy","onCreateOptionsMenu/myJokePresenter.randomViewMode: "+myJokePresenter.randomViewMode);
        getMenuInflater().inflate(R.menu.joke_actionbar_menu,menu);
        menu.setGroupVisible(R.id.group_visible1,myJokePresenter.randomViewMode);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh_action:
                Toast.makeText(this,"Refresh",Toast.LENGTH_SHORT).show();
                myJokePresenter.isJokeSet=false;
                myJokePresenter.getRundomJoke();
                break;
        }
        return true;
    }
}
