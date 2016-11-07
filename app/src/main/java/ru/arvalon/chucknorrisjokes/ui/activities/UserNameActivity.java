package ru.arvalon.chucknorrisjokes.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.arvalon.chucknorrisjokes.R;
import ru.arvalon.chucknorrisjokes.mvp.presenter.UserNamePresenterImpl;
import ru.arvalon.chucknorrisjokes.mvp.views.UserNameView;

public class UserNameActivity extends MvpAppCompatActivity implements UserNameView {

    @BindView(R.id.firstName)EditText firstName;
    @BindView(R.id.lastName)EditText lastName;
    @BindView(R.id.customJokeLoadProgressBar)ProgressBar loadCustomJokeProgressBar;
    @BindView(R.id.connectionErrorMessage)TextView connectionErorMessage;

    @InjectPresenter
    UserNamePresenterImpl userNamePresenter;

    private static String emptyFieldErrorString="Поле не может быть пустое";
    private static String JOKE="joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
        ButterKnife.bind(this);
    }

    @Override
    @OnClick(R.id.getCustomJokeButton)
    public void getJoke() {
        connectionErorMessage.setVisibility(View.GONE);
        userNamePresenter.validateUserName(firstName.getText().toString(),lastName.getText().toString());
    }

    @Override
    public void setFirstNameError() {
        firstName.setError(emptyFieldErrorString);
    }

    @Override
    public void setLastnameError() {
        lastName.setError(emptyFieldErrorString);
    }

    @Override
    public void showProgress() {
        loadCustomJokeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showConnectionError() {
        loadCustomJokeProgressBar.setVisibility(View.GONE);
        connectionErorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUserName(String firsName, String lastName) {

    }

    @Override
    public void showJoke(String jokeText) {
        Log.d("happy","UserNameActivity showJoke");
        loadCustomJokeProgressBar.setVisibility(View.GONE);
        Intent i=new Intent(this,JokeActivity.class);
        i.putExtra(JOKE,jokeText);
        startActivity(i);
    }
}
