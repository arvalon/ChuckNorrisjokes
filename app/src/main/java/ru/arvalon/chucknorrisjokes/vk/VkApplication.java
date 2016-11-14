package ru.arvalon.chucknorrisjokes.vk;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

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
 */

public class VkApplication extends Application {

    //public static final String VK_ACCESS_TOKEN = "VK_ACCESS_TOKEN";

    //private VKAccessToken access_token;

    //private String[] scope=new String[]{VKScope.WALL};


    @Override
    public void onCreate() {

        //access_token = VKAccessToken.tokenFromSharedPreferences(this, VK_ACCESS_TOKEN);
        //Log.d("happy","VkApplication - onCreate");

        vkAccessTokenTracker.startTracking();

        VKSdk.initialize(this);

        //access_token.saveTokenToSharedPreferences(this,VK_ACCESS_TOKEN);

    }

    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {

        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            Log.d("happy","onVKAccessTokenChanged");

            TokenLog(oldToken,newToken);

            if (newToken==null){
                Log.d("happy","New token is null, VKAccessToken is invalid");
            }else Log.d("happy","VKAccessToken is valid");

        }


    };

    private void TokenLog(VKAccessToken oldToken, VKAccessToken newToken){

        if (oldToken!=null) {
            Log.d("happy","Old Token");
            TokenLogcat(oldToken);
        }

        if (newToken!=null) {
            Log.d("happy","New Token");
            TokenLogcat(newToken);
        }
    }

    public void TokenLogcat(VKAccessToken Token){

        Log.d("happy"," TOKEN: "+Token.accessToken);
        Log.d("happy"," TOKEN email: "+Token.email
                +", TOKEN secret: "+Token.secret
                +", TOKEN userId: "+Token.userId
                +", TOKEN created: "+Token.created
                +", TOKEN isExpired: "+Token.isExpired()
                +", TOKEN expiresIn: "+Token.expiresIn);
    }
}
