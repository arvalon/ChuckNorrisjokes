package ru.arvalon.chucknorrisjokes.vk;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by arvalon on 10.11.2016.
 * This line added in flash copy
 * And this line added in origin place
 */

public class App extends Application {

    //public static final String VK_ACCESS_TOKEN = "VK_ACCESS_TOKEN";
    //private VKAccessToken access_token;
    //private String[] scope=new String[]{VKScope.WALL};
    
    public static final String TAG = "vga";


    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {return;}
        LeakCanary.install(this);

        //access_token = VKAccessToken.tokenFromSharedPreferences(this, VK_ACCESS_TOKEN);

        vkAccessTokenTracker.startTracking();

        VKSdk.initialize(this);

        //access_token.saveTokenToSharedPreferences(this,VK_ACCESS_TOKEN);

    }

    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {

        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            Log.d(TAG,"onVKAccessTokenChanged");

            TokenLog(oldToken,newToken);

            if (newToken==null){
                Log.d(TAG,"New token is null, VKAccessToken is invalid");
            }else Log.d(TAG,"VKAccessToken is valid");

        }
    };

    private void TokenLog(VKAccessToken oldToken, VKAccessToken newToken){

        if (oldToken!=null) {
            Log.d(TAG,"Old Token");
            TokenLogcat(oldToken);
        }

        if (newToken!=null) {
            Log.d(TAG,"New Token");
            TokenLogcat(newToken);
        }
    }

    public void TokenLogcat(VKAccessToken Token){

        Log.d(TAG," TOKEN: "+Token.accessToken);
        Log.d(TAG," TOKEN email: "+Token.email
                +", TOKEN secret: "+Token.secret
                +", TOKEN userId: "+Token.userId
                +", TOKEN created: "+Token.created
                +", TOKEN isExpired: "+Token.isExpired()
                +", TOKEN expiresIn: "+Token.expiresIn);
    }
}
