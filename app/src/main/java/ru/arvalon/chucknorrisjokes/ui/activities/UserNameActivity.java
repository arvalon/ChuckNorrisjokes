package ru.arvalon.chucknorrisjokes.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import ru.arvalon.chucknorrisjokes.vk.App;

/**
 * Активность редактирования имени/фамилии пользователя
 * fix edit
 * master edit
 */
public class UserNameActivity extends MvpAppCompatActivity implements UserNameView {

    @BindView(R.id.firstName)EditText firstName;
    @BindView(R.id.lastName)EditText lastName;
    @BindView(R.id.customJokeLoadProgressBar)ProgressBar loadCustomJokeProgressBar;
    @BindView(R.id.connectionErrorMessage)TextView connectionErorMessage;

    @InjectPresenter
    UserNamePresenterImpl userNamePresenter;

    private static final String emptyFieldErrorString="Поле не может быть пустое";
    private static final String JOKE="joke";

    private static final String sharedFirstName="sharedFirstName";
    private static final String sharedLastName="sharedLastName";

    SharedPreferences sPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
        ButterKnife.bind(this);
        sPrefs=getPreferences(getApplicationContext().MODE_PRIVATE);
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
    public void setUserName() {
        if(sPrefs.contains(sharedFirstName)&&sPrefs.contains(sharedLastName)){
            Log.d(App.TAG,"Get from SharedPreferences firsnname: "
                    +sPrefs.getString(sharedFirstName,"")
                    +", lastname: "
                    +sPrefs.getString(sharedLastName,""));
            this.firstName.setText(sPrefs.getString(sharedFirstName,""));
            this.lastName.setText(sPrefs.getString(sharedLastName,""));
        }
    }

    @Override
    public void saveUserName(String firstName, String lastName) {

        SharedPreferences.Editor editor=sPrefs.edit();
        editor.putString(sharedFirstName,firstName);
        editor.putString(sharedLastName,lastName);
        editor.apply();
    }

    @Override
    public void showJoke(String jokeText) {
        Log.d(App.TAG,"UserNameActivity showJoke");
        loadCustomJokeProgressBar.setVisibility(View.GONE);
        Intent i=new Intent(this,JokeActivity.class);
        i.putExtra(JOKE,jokeText);
        startActivity(i);
    }
}
