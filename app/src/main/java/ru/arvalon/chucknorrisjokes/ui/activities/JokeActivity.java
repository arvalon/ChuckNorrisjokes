package ru.arvalon.chucknorrisjokes.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.vk.sdk.api.model.VKApiDialog;
import com.vk.sdk.api.model.VKApiGetDialogResponse;
import com.vk.sdk.api.model.VKList;
import com.vk.sdk.api.model.VKWallPostResult;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.arvalon.chucknorrisjokes.R;
import ru.arvalon.chucknorrisjokes.mvp.presenter.JokePresenter;
import ru.arvalon.chucknorrisjokes.mvp.presenter.JokePresenterImpl;
import ru.arvalon.chucknorrisjokes.mvp.views.JokeView;

import static android.R.attr.id;

public class JokeActivity extends MvpAppCompatActivity implements JokeView {

    private static final String joke = "joke";

    private String[] scope=new String[]{VKScope.WALL};
    private int VkUserID;

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

        VKSdk.login(this,scope);

        //getVkUserInfo();

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
    public void PostJoke() {
        Toast.makeText(this,"Post to VK",Toast.LENGTH_SHORT).show();

        VKApi.users().get().executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                try{
                    JSONObject r=response.json.getJSONArray("response").getJSONObject(0);

                    Log.d("happy","УРА! Мои данные Вконтакте!");
                    Log.d("happy",r.toString());

                    VKParameters parameters = new VKParameters();
                    //my id 39223649
                    parameters.put(VKApiConst.OWNER_ID, r.getInt("id"));
                    parameters.put(VKApiConst.MESSAGE, textView.getText().toString());
                    VKRequest post = VKApi.wall().post(parameters);
                    Log.d("happy","Joke to post: "+textView.getText().toString());
                    post.setModelClass(VKWallPostResult.class);
                    post.executeWithListener(new VKRequest.VKRequestListener() {
                        @Override
                        public void onComplete(VKResponse response) {
                            Toast.makeText(getApplicationContext(),"post wall was added",Toast.LENGTH_LONG).show();
                            Log.d("happy","response: "+response.toString());
                            //
                        }
                        @Override
                        public void onError(VKError error) {
                            Toast.makeText(getApplicationContext(),"post wall error",Toast.LENGTH_LONG).show();

                        }
                    });

                }catch (Exception e){
                    Log.d("happy","VKApi.users().get() onComplete catch (Exception e)");
                    Log.d("happy",e.toString());
                }
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                super.attemptFailed(request, attemptNumber, totalAttempts);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }

            @Override
            public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                super.onProgress(progressType, bytesLoaded, bytesTotal);
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {

                Toast.makeText(getApplicationContext(),"Good",Toast.LENGTH_LONG).show();
                Log.d("happy","TOKEN: "+res.accessToken);
                Log.d("happy","TOKEN expiresIn: "+res.expiresIn);

            }
            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
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
