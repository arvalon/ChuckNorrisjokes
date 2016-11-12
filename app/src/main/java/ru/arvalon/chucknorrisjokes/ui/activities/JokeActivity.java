package ru.arvalon.chucknorrisjokes.ui.activities;

import android.content.Intent;
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
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKWallPostResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.arvalon.chucknorrisjokes.R;
import ru.arvalon.chucknorrisjokes.mvp.presenter.JokePresenterImpl;
import ru.arvalon.chucknorrisjokes.mvp.views.JokeView;


public class JokeActivity extends MvpAppCompatActivity implements JokeView {

    private static final String joke = "joke";
    private static final String VK_ACCESS_TOKEN = "VK_ACCESS_TOKEN";

    private String[] scope=new String[]{VKScope.WALL};

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
    public void vkAuthorize() {
        VKSdk.login(this,scope);

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
    @OnClick(R.id.postToVkButton)
    public void PostButton() {
        if (VKAccessToken.tokenFromSharedPreferences(this,VK_ACCESS_TOKEN)==null){
            Log.d("happy","Token from shared == null");
            vkAuthorize();
            //PostJoke(VKAccessToken.tokenFromSharedPreferences(this,VK_ACCESS_TOKEN));
            // TODO: 12.11.2016 call PostJoke with token
        }else if(VKAccessToken.tokenFromSharedPreferences(this,VK_ACCESS_TOKEN)!=null){
            Log.d("happy","Token from shared != null");
            PostJoke(VKAccessToken.tokenFromSharedPreferences(this,VK_ACCESS_TOKEN));
        }

    }

    private void PostJoke(VKAccessToken token) {
        VKParameters parameters = new VKParameters();
        parameters.put(VKApiConst.OWNER_ID, token.userId);
        parameters.put(VKApiConst.MESSAGE, textView.getText().toString());
        VKRequest post = VKApi.wall().post(parameters);
        Log.d("happy","Joke to post: "+textView.getText().toString());
        post.setModelClass(VKWallPostResult.class);
        post.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                Toast.makeText(getApplicationContext(),"Шутка добавлена",Toast.LENGTH_LONG).show();
                Log.d("happy","response: "+response.toString());
            }
            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(),"Ошибка авторизации",Toast.LENGTH_LONG).show();
                vkAuthorize();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Log.d("happy","Получили токен onActivityResult TOKEN: "+res.accessToken+", TOKEN expiresIn: "+res.expiresIn);
                res.saveTokenToSharedPreferences(getApplicationContext(),VK_ACCESS_TOKEN);
                PostJoke(res);
            }
            @Override
            public void onError(VKError error) {

                Log.d("happy","onActivityResult error не смогли получить токен");
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
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
